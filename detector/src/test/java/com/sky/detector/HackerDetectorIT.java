/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class HackerDetectorIT {
    public static final String SOME_IP_TOKEN = "80.238.9.179";
    
    @Test
    public void a_single_login_attempt_is_not_considered_offensive() {
        HackerDetector hackerDetector = new StrategicHackerDetector();
        String logline = createSampleLogline(SOME_IP_TOKEN);
        
        String response = hackerDetector.parseLine(logline);
        
        assertThat(response, is(""));
    }
    
    @Test
    public void the_fifth_consecutive_login_attempt_is_considered_offensive() {
        HackerDetector hackerDetector = new StrategicHackerDetector();
        String ip = SOME_IP_TOKEN;
        String logline = createSampleLogline(ip);
        
        String response = "";
        for (int i = 0; i < 5; i++) {
            response = hackerDetector.parseLine(logline);
        }
        
        assertThat(response, is(ip));
    }
    
    //~~~~~~~ Test data

    protected String createSampleLogline(String ip) {
        return ip+",133612947,SIGNIN_FAILURE,Dave.Branning";
    }
}