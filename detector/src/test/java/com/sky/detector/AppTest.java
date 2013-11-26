/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class AppTest {
    
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
    
    //~~~~~~ What is an Ip
    @Test
    public void the_ip_is_the_first_token_of_a_line() {
        String firstToken = "ip";
        
        String ip = firstToken;
        
        assertThat(ip, is(firstToken));
    }
    
}