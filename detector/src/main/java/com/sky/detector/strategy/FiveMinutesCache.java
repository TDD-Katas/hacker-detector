/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.strategy;

import com.sky.detector.data.Action;
import com.sky.detector.data.DateConstants;
import com.sky.detector.data.LoginAttempt;
import com.sky.detector.data.LoginDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FiveMinutesCache {
    public static final long TIME_TO_KEEP_LOGS = 5*DateConstants.MINUTE;
    Map<String, PastLoginDates> knownIps;

    public FiveMinutesCache() {
        knownIps = new HashMap<String, PastLoginDates>();
    }
    
    public void store(LoginAttempt loginAttempt) {
        if (loginAttempt.getAction() == Action.SIGNIN_FAILURE) {
            String ip = loginAttempt.getIp();
            if (!knownIps.containsKey(ip)) {
                knownIps.put(loginAttempt.getIp(), new PastLoginDates(TIME_TO_KEEP_LOGS));
            }
            
            PastLoginDates pastLoginDates = knownIps.get(ip);
            pastLoginDates.add(loginAttempt.getDate());
        }
    }

    public int getNumberOfFailedLogins(String ip) {
        int numberOfFailedLogins = 0;
        if (knownIps.containsKey(ip)) {
            PastLoginDates loginDates = knownIps.get(ip);
            numberOfFailedLogins = loginDates.size();
        } 
        
        return numberOfFailedLogins;
    }
    
    
    private static class PastLoginDates extends HashSet<LoginDate> {
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
            for (LoginDate loginDate : this) {
                if (loginDate.isBefore(leastAcceptableDate)) {
                    this.remove(loginDate);
                }
            }
        }
    }
}
