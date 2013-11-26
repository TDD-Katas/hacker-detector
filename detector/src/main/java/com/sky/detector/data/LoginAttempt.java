/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.data;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class LoginAttempt {
    private String ip;
    private String time;
    private String action;
    private String username;

    public LoginAttempt(String ip, String time, String action, String username) {
        this.ip = ip;
        this.time = time;
        this.action = action;
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public String getTime() {
        return time;
    }

    public String getAction() {
        return action;
    }

    public String getUsername() {
        return username;
    }
    
}
