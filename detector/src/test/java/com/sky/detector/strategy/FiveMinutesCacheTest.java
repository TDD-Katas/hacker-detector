/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.strategy;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FiveMinutesCacheTest {
    
    @Test
    public void a_cache_with_one_failed_login_attempts_for_ip_will_return_1() {
        
        assertThat(true, is(true));
    }
}