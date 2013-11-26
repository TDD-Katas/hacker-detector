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
        String firstToken = "ip";
        Line line = Line.fromLog(logWithFirstToken(firstToken));
        
        String ip = line.getIp();
        
        assertThat(ip, is(firstToken));
    }
    
    @Test
    public void line_second_token_is_time() {
        String secondToken = "time";
        Line line = Line.fromLog(logLineWithSecondToken(secondToken));
        
        String time = line.getTime();
        
        assertThat(time, is(secondToken));
    }
    
    @Test
    public void line_third_token_is_action() {
        String thirdToken = "action";
        Line line = Line.fromLog(logLineWithThirdToken(thirdToken));
        
        String action = line.getAction();
        
        assertThat(action, is(thirdToken));
    }
    
    @Test
    public void line_fourth_token_is_username() {
        String fourthToken = "username";
        Line line = Line.fromLog(logLineWithFourthToken(fourthToken));
        
        String username = line.getUsername();
        
        assertThat(username, is(fourthToken));
    }
    
    
    //~~~~~~ What is an token ?
    
    @Test(expected = InvalidInputStringFormatException.class)
    public void throw_invalid_syntax_if_logline_does_not_have_4_tokens() {
        Line.fromLog("ole"+TOKEN_SEPARATOR+"ole");
    }

    
    protected String logWithFirstToken(String firstToken) {
        StringBuilder st = new StringBuilder();
        st.append(firstToken);
        st.append(TOKEN_SEPARATOR);
        st.append("ole");
        st.append(TOKEN_SEPARATOR);
        st.append("ole");
        st.append(TOKEN_SEPARATOR);
        st.append("ole");
        
        return st.toString();
    }

    protected String logLineWithSecondToken(String secondToken) {
        return "ole"+TOKEN_SEPARATOR+secondToken+TOKEN_SEPARATOR+"ole"+TOKEN_SEPARATOR+"ole";
    }

    protected String logLineWithThirdToken(String thirdToken) {
        return "ole"+TOKEN_SEPARATOR+"ole"+TOKEN_SEPARATOR+thirdToken+TOKEN_SEPARATOR+"ole";
    }

    protected String logLineWithFourthToken(String fourthToken) {
        return "ole"+TOKEN_SEPARATOR+"ole"+TOKEN_SEPARATOR+"ole"+TOKEN_SEPARATOR+fourthToken;
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
            if (tokens.length != 4) {
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