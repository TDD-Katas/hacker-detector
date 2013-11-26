/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.data;

import com.sky.detector.exceptions.InvalidInputStringFormatException;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class LoglineAsLoginAttempt implements LoglineInterpreter {
    public static final String TOKEN_SEPARATOR = ",";
    public static final int NUMBER_OF_TOKENS = 4;

    @Override
    public  LoginAttempt convert(String logLine) {
        String[] tokens = logLine.split(TOKEN_SEPARATOR);
        if (tokens.length != NUMBER_OF_TOKENS) {
            throw new InvalidInputStringFormatException(
                    "Could not correctly split the log line into tokens");
        } else {
            return new LoginAttempt(tokens[0], tokens[1], tokens[2], tokens[3]);
        }
    }
    
}
