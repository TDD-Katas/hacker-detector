/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.strategy;

import com.sky.detector.data.LoginDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
class PastLoginDates extends ArrayList<LoginDate> {
    private long timeToKeepEntries;

    public PastLoginDates(long timeToKeepEntries) {
        this.timeToKeepEntries = timeToKeepEntries;
    }

    @Override
    public boolean add(LoginDate e) {
        deleteOldEntries(e);
        return super.add(e);
    }

    private void deleteOldEntries(LoginDate e) {
        //Delete old entries
        LoginDate leastAcceptableDate = e.addTime(-timeToKeepEntries);
        Iterator<LoginDate> iterator = this.iterator();
        while (iterator.hasNext()) {
            LoginDate existingDate = iterator.next();
            if (existingDate.isBefore(leastAcceptableDate)) {
                iterator.remove();
            }
        }
    }
}
