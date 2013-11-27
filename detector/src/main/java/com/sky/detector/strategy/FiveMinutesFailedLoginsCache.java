/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.strategy;

import com.sky.detector.data.Action;
import com.sky.detector.data.LoginAttempt;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FiveMinutesFailedLoginsCache {
    Set<String> failedLogins;

    public FiveMinutesFailedLoginsCache() {
        failedLogins = new HashSet<String>();
    }
    
    public void store(LoginAttempt loginAttempt) {
        if (loginAttempt.getAction() == Action.SIGNIN_FAILURE) {
            failedLogins.add(loginAttempt.getIp());
        }
    }

    public int getNumberOfFailedLogins(String ip) {
        if (failedLogins.contains(ip)) {
            return 1;
        } else {
            return 0;
        }
    }
    
}
