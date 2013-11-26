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
    public void a_login_attempt_is_offensive_if_the_same_ip_had_failed_login_5_or_more_times_in_a_5_minute_period() {
        
        assertThat(true, is(true));
    }
}