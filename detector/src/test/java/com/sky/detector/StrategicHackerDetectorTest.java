/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector;

import com.sky.detector.strategy.DetectionStrategy;
import com.sky.detector.data.LoginAttempt;
import com.sky.detector.logline.LoglineInterpreter;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static com.sky.detector.testhelpers.LoginAttemptTestHelper.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class StrategicHackerDetectorTest {
    public static final String SOME_LINE = "someline";
    public static final String EMPTY_STRING = "";
    
    @Test
    public void should_return_empty_string_if_login_attempt_is_not_offending() {
        boolean isLoginOffending = false;
        HackerDetector detector = createDetector(isLoginOffending);
        
        String result = detector.parseLine(SOME_LINE);
        
        assertThat(result, is(EMPTY_STRING));
    }
    
    @Test
    public void should_return_ip_if_login_attempt_is_offending() {
        LoginAttempt loginAttempt = createSomeLoginAttempt();
        boolean isLoginOffending = true;
        HackerDetector detector = createDetectorWithPreparedLogin(loginAttempt, isLoginOffending);
        
        String result = detector.parseLine(SOME_LINE);
        
        assertThat(result, is(loginAttempt.getIp().getRepresentation()));
    }

    //~~~~~ Test helpers
    
    protected HackerDetector createDetector(boolean isLoginOffending) {
        return createDetectorWithPreparedLogin(createSomeLoginAttempt(), isLoginOffending);
    }

    protected HackerDetector createDetectorWithPreparedLogin(LoginAttempt loginAttempt, boolean isLoginOffending) {
        LoglineInterpreter loglineInterpreter = mock(LoglineInterpreter.class);
        when(loglineInterpreter.convert(any(String.class))).thenReturn(loginAttempt);
        DetectionStrategy passAllDetectionStrategy = mock(DetectionStrategy.class);
        when(passAllDetectionStrategy.isLoginOffensive(any(LoginAttempt.class))).thenReturn(isLoginOffending);
        HackerDetector detector = new StrategicHackerDetector(loglineInterpreter, passAllDetectionStrategy);
        return detector;
    }
}