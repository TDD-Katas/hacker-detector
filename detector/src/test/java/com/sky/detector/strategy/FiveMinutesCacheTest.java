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
import org.junit.Ignore;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FiveMinutesCacheTest {
    
    @Ignore
    @Test
    public void a_cache_with_one_failed_login_attempts_for_ip_will_return_1() {
        String ip = SOME_IP;
        LoginAttempt loginAttempt = createFailedLoginAttemptFor(ip);
        FiveMinutesCache cache = new FiveMinutesCache();
        cache.store(loginAttempt);
        
        int numberOfFailedLogins = cache.getNumberOfFailedLogins(loginAttempt.getIp());
        
        assertThat(numberOfFailedLogins, is(1));
    }
}