/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.strategy.burst;

import com.sky.detector.data.LoginAttempt;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static com.sky.detector.testhelpers.LoginAttemptTestHelper.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class BurstLoginDetectionStrategyTest {
    
    @Test
    public void a_login_attempt_is_offensive_if_the_same_ip_had_failed_login_5_or_more_times_in_a_5_minute_period() {
        LoginAttempt loginAttempt = createSomeLoginAttempt();
        BurstLoginDetectionStrategy detectionStrategy = 
                createStrategyThatHasSeenFiveFailedLoginsFromIp(loginAttempt);
        
        boolean isOffensive = detectionStrategy.isLoginOffensive(loginAttempt);
        
        assertThat(isOffensive, is(true));
    }
    

    @Test
    public void all_login_attempts_are_retained_in_a_five_minutes_cache() {
        LoginAttempt loginAttempt = createSomeLoginAttempt();
        FiveMinutesCache fiveMinutesCache = mock(FiveMinutesCache.class);
        BurstLoginDetectionStrategy detectionStrategy = 
                createStrategyWithGivenCache(fiveMinutesCache);
        
        detectionStrategy.isLoginOffensive(loginAttempt);
        
        verify(fiveMinutesCache).store(loginAttempt);
    }
    
    //~~~~ Test helpers

    protected BurstLoginDetectionStrategy createStrategyThatHasSeenFiveFailedLoginsFromIp(
            LoginAttempt loginAttempt) {
        FiveMinutesCache fiveMinutesCache = mock(FiveMinutesCache.class);
        when(fiveMinutesCache.getNumberOfFailedLogins(loginAttempt.getIp())).thenReturn(5);
        BurstLoginDetectionStrategy detectionStrategy = 
                createStrategyWithGivenCache(fiveMinutesCache);
        return detectionStrategy;
    }

    protected BurstLoginDetectionStrategy createStrategyWithGivenCache(
            FiveMinutesCache fiveMinutesCache) {
        BurstLoginDetectionStrategy detectionStrategy = 
                new BurstLoginDetectionStrategy(fiveMinutesCache);
        return detectionStrategy;
    }
}