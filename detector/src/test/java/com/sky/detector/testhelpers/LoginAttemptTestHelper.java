/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.testhelpers;

import com.sky.detector.data.Action;
import com.sky.detector.data.LoginAttempt;
import java.util.Date;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class LoginAttemptTestHelper {
    public static final String SOME_IP = "80.238.9.179";
    
    
    public static LoginAttempt  createSomeLoginAttempt() {
        return createFailedLoginAttemptFor(SOME_IP);
    }
    
    public static LoginAttempt createFailedLoginAttemptFor(String ip) {
        return createLoginAttemptFor(ip, Action.SIGNIN_FAILURE);
    }
    
    public static LoginAttempt createSuccesfulLoginAttemptFor(String ip) {
        return createLoginAttemptFor(ip, Action.SIGNIN_SUCCESS);
    }
    
    public static LoginAttempt createLoginAttemptFor(String ip, Action action) {
        LoginAttempt loginAttempt = new LoginAttempt(
                ip, 
                new Date(133612947), 
                action, 
                "Andy.Branning");
        return loginAttempt;
    }
}
