/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.data;

import com.sky.detector.data.LoginAttempt;
import com.sky.detector.data.LoglineAsLoginAttempt;
import com.sky.detector.exceptions.InvalidInputStringFormatException;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class LoglineAsLoginAttemptTest {
    public static final String SOME_STRING = "somestring";
    
    @Test(expected = InvalidInputStringFormatException.class)
    public void throw_invalid_syntax_if_logline_does_not_have_4_tokens() {
        asLoginAttempt(SOME_STRING);
    }
    
    @Test
    public void first_token_is_login_attempt_ip() {
        String firstToken = SOME_STRING;
        String logLine = loglineWithPresetToken(0, firstToken);
        
        LoginAttempt loginAttempt = asLoginAttempt(logLine);
        
        assertThat(loginAttempt.getIp(), is(firstToken));
    }
    
    @Test
    public void second_token_is_login_attempt_time() {
        String secondToken = SOME_STRING;
        String logLine = loglineWithPresetToken(1, secondToken);
        
        LoginAttempt loginAttempt = asLoginAttempt(logLine);
        
        assertThat(loginAttempt.getTime(), is(secondToken));
    }
    
    @Test
    public void third_token_is_login_attempt_action() {
        String thirdToken = SOME_STRING;
        String logLine = loglineWithPresetToken(2, thirdToken);
        
        LoginAttempt loginAttempt = asLoginAttempt(logLine);
        
        assertThat(loginAttempt.getAction(), is(thirdToken));
    }
    
    @Test
    public void fourth_token_is_login_attempt_username() {
        String fourthToken = SOME_STRING;
        String logLine = loglineWithPresetToken(3, fourthToken);
        
        LoginAttempt loginAttempt = asLoginAttempt(logLine);
        
        assertThat(loginAttempt.getUsername(), is(fourthToken));
    }
    

    //~~~~~~~ Test helpers
    
    private LoginAttempt asLoginAttempt(String logLine) {
        return new LoglineAsLoginAttempt().convert(logLine);
    }    
    
    
    private String loglineWithPresetToken(int tokenPosition, String token) {
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < LoglineAsLoginAttempt.NUMBER_OF_TOKENS; i++) {
            if (i == tokenPosition) {
                st.append(token);
            } else {
                st.append("ole");
            }
            st.append(LoglineAsLoginAttempt.TOKEN_SEPARATOR);
        }
        
        return st.toString();
    }


}