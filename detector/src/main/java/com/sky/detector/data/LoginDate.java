/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.data;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class LoginDate {
    private long time;

    public LoginDate(long timeFromEpoch) {
        this.time = timeFromEpoch;
    }

    public long getTime() {
        return time;
    }
    
    public LoginDate addMinutes(int nrOfMinutes) {
        return new LoginDate(time+nrOfMinutes*DateConstants.MINUTE);
    };
}
