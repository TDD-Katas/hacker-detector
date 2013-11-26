/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector;

import com.sky.detector.data.LoginAttempt;
import com.sky.detector.data.LoginAttempt;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public interface DetectionStrategy {

    boolean isLoginOffensive(LoginAttempt loginAttempt);
    
}
