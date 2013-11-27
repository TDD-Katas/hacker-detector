/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.strategy;

import com.sky.detector.data.LoginAttempt;
import org.junit.Test;
import static com.sky.detector.testhelpers.LoginAttemptTestHelper.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FiveMinutesFailedLoginsCacheTest {
    FiveMinutesFailedLoginsCache cache;
    
    @Before
    public void setUp() {
        cache = createEmptyCache();
    }
    
    @Test
    public void cache_should_not_store_a_successful_login_attempt() {
        String ip = SOME_IP;
        LoginAttempt loginAttempt = createSuccesfulLoginAttemptFor(ip);
        
        cache.store(loginAttempt);
        
        assertThat(failedLoginsFor(ip), is(0));
    }
    
    @Test
    public void cache_should_store_a_failed_login_attempt() {
        String ip = SOME_IP;
        LoginAttempt loginAttempt = createFailedLoginAttemptFor(ip);
        
        cache.store(loginAttempt);
        
        assertThat(failedLoginsFor(ip), is(1));
    }
    
    //~~~~~~~~ Test helpers
    
    protected int failedLoginsFor(String ip) {
        return cache.getNumberOfFailedLogins(ip);
    }

    protected FiveMinutesFailedLoginsCache createEmptyCache() {
        return new FiveMinutesFailedLoginsCache();
    }

}