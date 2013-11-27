/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector;

import com.sky.detector.strategy.DetectionStrategy;
import com.sky.detector.data.LoginAttempt;
import com.sky.detector.logline.LoglineAsLoginAttempt;
import com.sky.detector.logline.LoglineInterpreter;
import com.sky.detector.strategy.burst.BurstLoginDetectionStrategy;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class StrategicHackerDetector implements HackerDetector {
    LoglineInterpreter loglineInterpreter;
    DetectionStrategy detectionStrategy;

    public StrategicHackerDetector() {
        this(new LoglineAsLoginAttempt(), new BurstLoginDetectionStrategy());
    }
    
    public StrategicHackerDetector(LoglineInterpreter loglineInterpreter, 
            DetectionStrategy detectionStrategy) {
        this.loglineInterpreter = loglineInterpreter;
        this.detectionStrategy = detectionStrategy;
    }

    public String parseLine(String line) {
        LoginAttempt loginAttempt = loglineInterpreter.convert(line);
        if (detectionStrategy.isLoginOffensive(loginAttempt)) {
            return loginAttempt.getIp();
        } else {
            return "";
        }
    }
    
}
