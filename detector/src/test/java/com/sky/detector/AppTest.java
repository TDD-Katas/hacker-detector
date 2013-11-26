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
        String ip = "ip";
        String line = "ip"+TOKEN_SEPARATOR+"ole";
        
        String firstToken = line.split(TOKEN_SEPARATOR)[0];
        
        assertThat(ip, is(firstToken));
    }
    
    //~~~~~~ What is an token ?
    
    @Test
    public void a_line_is_split_into_tokens() {
        String token1 = "token1";
        String token2 = "token2";
        String line = token1+TOKEN_SEPARATOR+token2;
        
        String[] tokens = line.split(TOKEN_SEPARATOR);
        
        assertThat(tokens, equalTo(new String[]{token1, token2}));
    }
    
}