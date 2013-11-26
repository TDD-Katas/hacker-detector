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
    public void the_ip_is_the_first_token_of_a_line() {
        String firstToken = "ip";
        Line line = new Line("ip"+TOKEN_SEPARATOR+"ole");
        
        String ip = line.getIp();
        
        assertThat(ip, is(firstToken));
    }
    
    @Test
    public void the_time_is_the_second_token_of_a_line() {
        String secondToken = "time";
        String line = "ole"+TOKEN_SEPARATOR+secondToken;
        
        String time = getTimeFromLine(line);
        
        assertThat(time, is(secondToken));
    }
    
    
    //~~~~~~ What is an token ?
    
    @Test
    public void a_line_is_split_into_tokens() {
        String token1 = "token1";
        String token2 = "token2";
        Line line = new Line(token1+TOKEN_SEPARATOR+token2);
        
        String[] tokens = line.split();
        
        assertThat(tokens, equalTo(new String[]{token1, token2}));
    }

    
    
    protected String[] splitLine(String line) {
        return line.split(TOKEN_SEPARATOR);
    }

    protected String getIpFromLine(String line) {
        return splitLine(line)[0];
    }

    protected String getTimeFromLine(String line) {
        return splitLine(line)[1];
    }
    
    class Line {
        private String line;
        
        public Line(String line) {
            this.line = line;
        }
        
        protected String[] split() {
            return line.split(TOKEN_SEPARATOR);
        }        
        
        protected String getIp() {
            return split()[0];
        }
        
    }
    
    
}