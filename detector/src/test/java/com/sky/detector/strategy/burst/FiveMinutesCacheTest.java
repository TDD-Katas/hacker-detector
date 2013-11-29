/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.strategy.burst;

import com.sky.detector.data.DateConstants;
import com.sky.detector.data.IPAddress;
import com.sky.detector.data.LoginAttempt;
import com.sky.detector.data.LoginDate;
import org.junit.Test;
import static com.sky.detector.testhelpers.LoginAttemptTestHelper.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FiveMinutesCacheTest {
    FiveMinutesCache cache;
    
    @Before
    public void setUp() {
        cache = createEmptyCache();
    }
    
    @Test
    public void should_not_store_a_successful_login_attempt() {
        IPAddress ip = SOME_IP;
        LoginAttempt loginAttempt = createSuccesfulLoginAttemptFor(ip);
        
        cache.store(loginAttempt);
        
        assertThat(failedLoginsFor(ip), is(0));
    }
    
    @Test
    public void should_store_a_failed_login_attempt() {
        IPAddress ip = SOME_IP;
        LoginAttempt loginAttempt = createFailedLoginAttemptFor(ip);
        
        cache.store(loginAttempt);
        
        assertThat(failedLoginsFor(ip), is(1));
    }
    
    @Test
    public void should_store_logins_from_different_ip() {
        IPAddress ip = SOME_IP;
        IPAddress otherIp = SOME_OTHER_IP;
        
        cache.store(createFailedLoginAttemptFor(ip));
        cache.store(createFailedLoginAttemptFor(otherIp));
        
        assertThat(failedLoginsFor(ip), is(1));
        assertThat(failedLoginsFor(otherIp), is(1));
    }
    
    @Test
    public void when_storing_a_failed_login_should_delete_logins_older_then_five_minutes() {
        IPAddress ip = SOME_IP;
        IPAddress otherIp = SOME_OTHER_IP;
        LoginDate date = SOME_DATE;
        
        cache.store(createFailedLoginAttemptFor(ip, date));
        cache.store(createFailedLoginAttemptFor(otherIp, date));
        cache.store(createFailedLoginAttemptFor(ip, date.addTime(6* DateConstants.MINUTE)));
        
        assertThat(failedLoginsFor(ip), is(1));
        assertThat(failedLoginsFor(otherIp), is(1));
    }
    
    
    @Test
    public void can_store_multiple_logins_for_same_ip_if_not_older_than_five_minutes() {
        IPAddress ip = SOME_IP;
        LoginDate date = SOME_DATE;
        
        cache.store(createFailedLoginAttemptFor(ip, date));
        cache.store(createFailedLoginAttemptFor(ip, date.addTime(DateConstants.MINUTE)));
        
        assertThat(failedLoginsFor(ip), is(2));
    }
    
    
    
    //~~~~~~~~ Test helpers
    
    protected int failedLoginsFor(IPAddress ip) {
        return cache.getNumberOfFailedLogins(ip);
    }

    protected FiveMinutesCache createEmptyCache() {
        return new FiveMinutesCache();
    }

}