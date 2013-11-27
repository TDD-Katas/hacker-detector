/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.strategy.burst;

import com.sky.detector.data.Action;
import com.sky.detector.data.DateConstants;
import com.sky.detector.data.LoginAttempt;
import com.sky.detector.data.LoginDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FiveMinutesCache {
    public static final long TIME_TO_KEEP_PAST_LOGINS = 5*DateConstants.MINUTE;
    Map<String, PastLoginDates> knownIps;

    public FiveMinutesCache() {
        knownIps = new HashMap<String, PastLoginDates>();
    }
    
    public void store(LoginAttempt loginAttempt) {
        if (loginAttempt.getAction() == Action.SIGNIN_FAILURE) {
            String ip = loginAttempt.getIp();
            ensurePastLoginDatesInitialized(ip);
            
            PastLoginDates pastLoginDates = knownIps.get(ip);
            pastLoginDates.add(loginAttempt.getDate());
        }
    }

    public int getNumberOfFailedLogins(String ip) {
        int numberOfFailedLogins = 0;
        if (knownIps.containsKey(ip)) {
            PastLoginDates pastLoginDates = knownIps.get(ip);
            numberOfFailedLogins = pastLoginDates.size();
        } 
        
        return numberOfFailedLogins;
    }

    private void ensurePastLoginDatesInitialized(String ip) {
        if (!knownIps.containsKey(ip)) {
            knownIps.put(ip, new PastLoginDates(TIME_TO_KEEP_PAST_LOGINS));
        }
    }
    
    //~~~~~~~~ 
    
    static class PastLoginDates extends ArrayList<LoginDate> {
        private long timeToKeepEntries;

        public PastLoginDates(long timeToKeepEntries) {
            this.timeToKeepEntries = timeToKeepEntries;
        }

        @Override
        public boolean add(LoginDate e) {
            deleteOldEntries(e);
            return super.add(e);
        }

        private void deleteOldEntries(LoginDate e) {
            //Delete old entries
            LoginDate leastAcceptableDate = e.addTime(-timeToKeepEntries);
            Iterator<LoginDate> iterator = this.iterator();
            while (iterator.hasNext()) {
                LoginDate existingDate = iterator.next();
                if (existingDate.isBefore(leastAcceptableDate)) {
                    iterator.remove();
                }
            }
        }
    }
}
