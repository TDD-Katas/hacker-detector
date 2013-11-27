/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.strategy;

import com.sky.detector.DetectionStrategy;
import com.sky.detector.data.LoginAttempt;

public class BurstLoginDetectionStrategy implements DetectionStrategy {
    public static final int MAXIMUM_NUMBER_OF_FAILED_LOGINS = 5;
    FiveMinutesFailedLoginCache lastFiveMinutes;

    public BurstLoginDetectionStrategy() {
        this(new FiveMinutesFailedLoginCache());
    }
    
    public BurstLoginDetectionStrategy(FiveMinutesFailedLoginCache lastFiveMinutes) {
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
