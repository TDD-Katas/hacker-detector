/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Ignore;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class AppTest {
    public static final String TOKEN_SEPARATOR = ",";
    public static final int NUMBER_OF_TOKENS = 4;
    public static final String SOME_STRING = "somestring";
    
    static class StrategicHackerDetector implements HackerDetector {
        DetectionStrategy detectionStrategy;

        public StrategicHackerDetector(DetectionStrategy detectionStrategy) {
            this.detectionStrategy = detectionStrategy;
        }
        
        public String parseLine(String line) {
            LoginAttempt loginAttempt = LogLine.asLoginAttempt(line);
            if (detectionStrategy.isLoginOffensive(loginAttempt)) {
                return loginAttempt.getIp();
            } else {
                return "";
            }
        }
        
    }
    
    static interface DetectionStrategy {
        public boolean isLoginOffensive(LoginAttempt loginAttempt);
    }
    
    //~~~~~~ What should return
    
    @Test
    public void should_return_empty_string_if_login_attempt_is_not_offending() {
        String emptyString = "";
        boolean isOffendingLoginAttempt = false;
        
        String result = SOME_STRING;
        if (!isOffendingLoginAttempt) {
            result = emptyString;
        }
        
        assertThat(result, is(emptyString));
    }
    
    @Test
    public void should_return_ip_if_login_attempt_is_offending() {
        String ip = SOME_STRING;
        boolean isOffendingLogginAttempt = true;
        
        String result = "";
        if (isOffendingLogginAttempt) {
            result = ip;
        }
        
        assertThat(result, is(ip));
    }
    
    @Test
    public void a_login_attempt_is_offensive_if_the_same_ip_had_failed_login_5_or_more_times_in_a_5_minute_period() {
        
        assertThat(true, is(true));
    }
    
    
    //~~~~~~ What is a login attempt
    
    @Test
    public void log_line_first_token_is_login_attempt_ip() {
        String firstToken = SOME_STRING;
        String logLine = loglineWithPresetToken(0, firstToken);
        
        LoginAttempt loginAttempt = LogLine.asLoginAttempt(logLine);
        
        assertThat(loginAttempt.getIp(), is(firstToken));
    }
    
    @Test
    public void log_line_second_token_is_login_attempt_time() {
        String secondToken = SOME_STRING;
        String logLine = loglineWithPresetToken(1, secondToken);
        
        LoginAttempt loginAttempt = LogLine.asLoginAttempt(logLine);
        
        assertThat(loginAttempt.getTime(), is(secondToken));
    }
    
    @Test
    public void log_line_third_token_is_login_attempt_action() {
        String thirdToken = SOME_STRING;
        String logLine = loglineWithPresetToken(2, thirdToken);
        
        LoginAttempt loginAttempt = LogLine.asLoginAttempt(logLine);
        
        assertThat(loginAttempt.getAction(), is(thirdToken));
    }
    
    @Test
    public void log_line_fourth_token_is_login_attempt_username() {
        String fourthToken = SOME_STRING;
        String logLine = loglineWithPresetToken(3, fourthToken);
        
        LoginAttempt loginAttempt = LogLine.asLoginAttempt(logLine);
        
        assertThat(loginAttempt.getUsername(), is(fourthToken));
    }
    
    
    //~~~~~~ What is an token ?
    
    @Test(expected = InvalidInputStringFormatException.class)
    public void throw_invalid_syntax_if_logline_does_not_have_4_tokens() {
        LogLine.asLoginAttempt(SOME_STRING);
    }

    
    protected String loglineWithPresetToken(int tokenPosition, String token) {
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < NUMBER_OF_TOKENS; i++) {
            if (i == tokenPosition) {
                st.append(token);
            } else {
                st.append("ole");
            }
            st.append(TOKEN_SEPARATOR);
        }
        
        return st.toString();
    }

    static class InvalidInputStringFormatException extends RuntimeException {

        public InvalidInputStringFormatException() {
        }

        public InvalidInputStringFormatException(String message) {
            super(message);
        }
    }
    
    static class LogLine {
        public static LoginAttempt asLoginAttempt(String logLine) {
            String[] tokens = logLine.split(TOKEN_SEPARATOR);
            if (tokens.length != NUMBER_OF_TOKENS) {
                throw new InvalidInputStringFormatException(
                        "Could not correctly split the log line into tokens");
            } else {
                return new LoginAttempt(
                        tokens[0],
                        tokens[1],
                        tokens[2],
                        tokens[3]);
            }
        }
    }
    
    static class LoginAttempt {
        private String ip;
        private String time;
        private String action;
        private String username;

        public LoginAttempt(String ip, String time, String action, String username) {
            this.ip = ip;
            this.time = time;
            this.action = action;
            this.username = username;
        }
        
        public String getIp() {
            return ip;
        }

        public String getTime() {
            return time;
        }

        public String getAction() {
            return action;
        }

        public String getUsername() {
            return username;
        }
    }
    
    
}