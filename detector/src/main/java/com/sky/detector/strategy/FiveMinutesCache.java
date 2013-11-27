/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.strategy;

import com.sky.detector.data.LoginAttempt;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FiveMinutesCache {
    
    
    
    public void store(LoginAttempt loginAttempt) {
    }

    public int getNumberOfFailedLogins(String ip) {
        return 0;
    }
    
}
