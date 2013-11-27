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
        LoginAttempt loginAttempt = new LoginAttempt(
                ip, 
                new Date(133612947), 
                Action.SIGNIN_FAILURE, 
                "Andy.Branning");
        return loginAttempt;
    }
}
