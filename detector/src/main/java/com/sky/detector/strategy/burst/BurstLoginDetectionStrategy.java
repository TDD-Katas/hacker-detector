/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.strategy.burst;

import com.sky.detector.data.LoginAttempt;
import com.sky.detector.strategy.DetectionStrategy;

public class BurstLoginDetectionStrategy implements DetectionStrategy {
    public static final int MAXIMUM_NUMBER_OF_FAILED_LOGINS = 5;
    FiveMinutesCache lastFiveMinutes;

    public BurstLoginDetectionStrategy() {
        this(new FiveMinutesCache());
    }
    
    public BurstLoginDetectionStrategy(FiveMinutesCache lastFiveMinutes) {
        this.lastFiveMinutes = lastFiveMinutes;
    }

    public boolean isLoginOffensive(LoginAttempt loginAttempt) {
        lastFiveMinutes.store(loginAttempt);
        if (lastFiveMinutes.getNumberOfFailedLogins(loginAttempt.getIp()) >=
                MAXIMUM_NUMBER_OF_FAILED_LOGINS) {
            return true;
        } else {
            return false;
        }
    }
}
