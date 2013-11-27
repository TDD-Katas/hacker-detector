/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.data;

import com.sky.detector.exceptions.InvalidInputStringFormatException;
import java.util.Date;
import sun.net.util.IPAddressUtil;

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
            String ip = getValidIp(tokens[0]);
            LoginDate date = getValidDate(tokens[1]);
            Action action = getValidAction(tokens[2]);
            
            return new LoginAttempt(ip, date, action, tokens[3]);
        }
    }

    protected String getValidIp(String ipToken) throws InvalidInputStringFormatException {
        if (!IPAddressUtil.isIPv4LiteralAddress(ipToken)) {
            throw new InvalidInputStringFormatException(
                    "The Ip has incorrect format");
        }
        
        return ipToken;
    }
    
    protected LoginDate getValidDate(String dateToken) throws InvalidInputStringFormatException {
        try {
            long timeFromEpoch = Long.parseLong(dateToken);
            return new LoginDate(timeFromEpoch);
        } catch (NumberFormatException ex) {
            throw new InvalidInputStringFormatException(
                    "The Date has incorrect format ", ex);
        }
    }
    
    protected Action getValidAction(String dateToken) throws InvalidInputStringFormatException {
        try {
            Action action = Action.valueOf(dateToken);
            return action;
        } catch (IllegalArgumentException ex) {
            throw new InvalidInputStringFormatException(
                    "The Action has incorrect format ", ex);
        }
    }
    
}
