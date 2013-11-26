/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector;

import com.sky.detector.data.LogLine;
import com.sky.detector.data.LoginAttempt;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class StrategicHackerDetector implements HackerDetector {
    DetectionStrategy detectionStrategy;

    public StrategicHackerDetector(DetectionStrategy detectionStrategy) {
        this.detectionStrategy = detectionStrategy;
    }

    public String parseLine(String line) {
        LoginAttempt loginAttempt = LogLine.asLoginAttempt(line);
        if (detectionStrategy.isLoginOffensive(loginAttempt)) {
            return loginAttempt.getIp();
        } else {
            return "";
        }
    }
    
}
