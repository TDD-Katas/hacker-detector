/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.strategy.burst;

import com.sky.detector.data.Action;
import com.sky.detector.data.DateConstants;
import com.sky.detector.data.IPAddress;
import com.sky.detector.data.LoginAttempt;
import com.sky.detector.data.LoginDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FiveMinutesCache {
    public static final long FIVE_MINUTES = 5*DateConstants.MINUTE;
    public static final long TIME_TO_KEEP_PAST_LOGINS = FIVE_MINUTES;
    Map<IPAddress, Counter> numberOfFailedLoginsForIp;
    Queue<LoginAttempt> lastFailedLoginsQueue;

    public FiveMinutesCache() {
        numberOfFailedLoginsForIp = new HashMap<IPAddress, Counter>();
        lastFailedLoginsQueue = new LinkedList<LoginAttempt>();
    }
    
    public void store(LoginAttempt loginAttempt) {
        if (loginAttempt.getAction() == Action.SIGNIN_FAILURE) {
            addFailedLogin(loginAttempt);
            deleteOldLogins(loginAttempt);
        }
    }

    public int getNumberOfFailedLogins(IPAddress ip) {
        int numberOfFailedLogins = 0;
        if (numberOfFailedLoginsForIp.containsKey(ip)) {
            numberOfFailedLogins = numberOfFailedLoginsForIp.get(ip).getValue();
        } 
        
        return numberOfFailedLogins;
    }

    //~~~~~ Private helpers
    
    private void ensurePastLoginDatesInitialized(IPAddress ip) {
        if (!numberOfFailedLoginsForIp.containsKey(ip)) {
            numberOfFailedLoginsForIp.put(ip, new Counter());
        }
    }

    protected void addFailedLogin(LoginAttempt loginAttempt) {
        //Increment counter
        IPAddress ip = loginAttempt.getIp();
        ensurePastLoginDatesInitialized(ip);
        lastFailedLoginsQueue.add(loginAttempt);
        numberOfFailedLoginsForIp.get(ip).increment();
    }

    protected void deleteOldLogins(LoginAttempt loginAttempt) {
        LoginDate currentDate = loginAttempt.getDate();
        LoginDate leastAcceptableDate = currentDate.substractTime(TIME_TO_KEEP_PAST_LOGINS);
        deleteLoginsOlderThan(leastAcceptableDate);
    }
    
    private void deleteLoginsOlderThan(LoginDate leastAcceptableDate) {
        boolean continueRemoving = true;
        while (continueRemoving) {            
            LoginAttempt loginAttempt = lastFailedLoginsQueue.peek();
            if (loginAttempt.getDate().isBefore(leastAcceptableDate)) {
                lastFailedLoginsQueue.remove();
                numberOfFailedLoginsForIp.get(loginAttempt.getIp()).decrement();
            } else {
                continueRemoving = false;
            }
        }
    }
    
    //~~~~~~~~ 
    
    static class Counter {
        private int value;

        public Counter() {
            value = 0;
        }
        
        public int getValue() {
            return value;
        }
        
        public void increment() {
            value++;
        }
        
        public void decrement() {
            value--;
        }
    }
}
