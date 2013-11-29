/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.testhelpers;

import com.sky.detector.data.Action;
import com.sky.detector.data.IPAddress;
import com.sky.detector.data.LoginAttempt;
import com.sky.detector.data.LoginDate;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class LoginAttemptTestHelper {
    public static final IPAddress SOME_IP = new IPAddress("80.238.9.179");
    public static final IPAddress SOME_OTHER_IP = new IPAddress("192.168.0.1");
    public static final LoginDate SOME_DATE = new LoginDate(133612947);

    public static LoginAttempt createSomeLoginAttempt() {
        return createFailedLoginAttemptFor(SOME_IP);
    }

    public static LoginAttempt createFailedLoginAttemptFor(IPAddress ip) {
        return createLoginAttemptFor(ip, Action.SIGNIN_FAILURE, SOME_DATE);
    }

    public static LoginAttempt createFailedLoginAttemptFor(IPAddress ip, LoginDate date) {
        return createLoginAttemptFor(ip, Action.SIGNIN_FAILURE, date);
    }

    public static LoginAttempt createSuccesfulLoginAttemptFor(IPAddress ip) {
        return createLoginAttemptFor(ip, Action.SIGNIN_SUCCESS, SOME_DATE);
    }

    public static LoginAttempt createLoginAttemptFor(IPAddress ip, Action action, LoginDate date) {
        LoginAttempt loginAttempt = new LoginAttempt(
                ip, date, action, "Andy.Branning");
        return loginAttempt;
    }
}
