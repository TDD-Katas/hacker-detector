/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.testhelpers;

import com.sky.detector.data.LoginAttempt;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class LoginAttemptTestHelper {
    
    
    public static LoginAttempt  createSomeLoginAttempt() {
        return new LoginAttempt("80.238.9.179", "133612947", "SIGNIN_FAILURE", "Andy.Branning");
    }
}
