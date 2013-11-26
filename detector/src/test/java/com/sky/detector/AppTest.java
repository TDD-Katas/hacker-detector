/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class AppTest {
    public static final String TOKEN_SEPARATOR = ",";
    public static final int NUMBER_OF_TOKENS = 4;
    public static final String SOME_STRING = "somestring";
    
    //~~~~~~ What should return
    
    @Test
    public void should_return_empty_string_if_line_is_not_offending() {
        String emptyString = "";
        boolean isOffendingLine = false;
        
        String result = "x";
        if (!isOffendingLine) {
            result = emptyString;
        }
        
        assertThat(result, is(emptyString));
    }
    
    @Test
    public void should_return_ip_if_line_is_offending() {
        String ip = "ip";
        boolean isOffendingLine = true;
        
        String result = "";
        if (isOffendingLine) {
            result = ip;
        }
        
        assertThat(result, is(ip));
    }
    
    //~~~~~~ What is an Ip ?
    
    @Test
    public void line_first_token_is_ip() {
        String firstToken = SOME_STRING;
        Line line = Line.fromLog(loglineWithPresetToken(0, firstToken));
        
        String ip = line.getIp();
        
        assertThat(ip, is(firstToken));
    }
    
    @Test
    public void line_second_token_is_time() {
        String secondToken = SOME_STRING;
        Line line = Line.fromLog(loglineWithPresetToken(1, secondToken));
        
        String time = line.getTime();
        
        assertThat(time, is(secondToken));
    }
    
    @Test
    public void line_third_token_is_action() {
        String thirdToken = SOME_STRING;
        Line line = Line.fromLog(loglineWithPresetToken(2, thirdToken));
        
        String action = line.getAction();
        
        assertThat(action, is(thirdToken));
    }
    
    @Test
    public void line_fourth_token_is_username() {
        String fourthToken = SOME_STRING;
        Line line = Line.fromLog(loglineWithPresetToken(3, fourthToken));
        
        String username = line.getUsername();
        
        assertThat(username, is(fourthToken));
    }
    
    
    //~~~~~~ What is an token ?
    
    @Test(expected = InvalidInputStringFormatException.class)
    public void throw_invalid_syntax_if_logline_does_not_have_4_tokens() {
        Line.fromLog(SOME_STRING);
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
    
    static class Line {
        private String[] tokens;
        
        private Line(String[] tokens) {
            this.tokens = tokens;
        }
        
        public static Line fromLog(String logLine) {
            String[] tokens = logLine.split(TOKEN_SEPARATOR);
            if (tokens.length != NUMBER_OF_TOKENS) {
                throw new InvalidInputStringFormatException(
                        "Could not correctly split the log line into tokens");
            } else {
                return new Line(tokens);
            }
        }
        
        protected String[] getTokens() {
            return tokens;
        }        
        
        protected String getIp() {
            return tokens[0];
        }
        
        protected String getTime() {
            return tokens[1];
        }
        
        protected String getAction() {
            return tokens[2];
        }
        
        protected String getUsername() {
            return tokens[3];
        }
    }
    
    
}