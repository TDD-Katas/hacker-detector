/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.logline;

import com.sky.detector.data.LoginAttempt;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public interface LoglineInterpreter {
    LoginAttempt convert(String logLine);
}
