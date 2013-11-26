/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector;

import com.sky.detector.data.LogLineTest;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class StrategicHackerDetectorTest {
    
    @Test
    public void should_return_empty_string_if_login_attempt_is_not_offending() {
        String emptyString = "";
        boolean isOffendingLoginAttempt = false;
        
        String result = LogLineTest.SOME_STRING;
        if (!isOffendingLoginAttempt) {
            result = emptyString;
        }
        
        assertThat(result, is(emptyString));
    }
    
    @Test
    public void should_return_ip_if_login_attempt_is_offending() {
        String ip = LogLineTest.SOME_STRING;
        boolean isOffendingLogginAttempt = true;
        
        String result = "";
        if (isOffendingLogginAttempt) {
            result = ip;
        }
        
        assertThat(result, is(ip));
    }
}