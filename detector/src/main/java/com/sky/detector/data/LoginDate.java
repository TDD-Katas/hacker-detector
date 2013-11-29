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
    
    public LoginDate addTime(long addedTime) {
        return new LoginDate(time+addedTime);
    }
    
    public LoginDate substractTime(long substractedTime) {
        return new LoginDate(time-substractedTime);
    }
    
    public boolean isBefore(LoginDate otherDate) {
        return this.time < otherDate.time;
    }

    //~~~~~~~ For collection storage
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (int) (this.time ^ (this.time >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LoginDate other = (LoginDate) obj;
        if (this.time != other.time) {
            return false;
        }
        return true;
    }
}
