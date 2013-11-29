/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.data;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class IPAddress {
    String representation;

    public IPAddress(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

    //~~~~~~ Collection storage
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.representation != null ? this.representation.hashCode() : 0);
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
        final IPAddress other = (IPAddress) obj;
        if ((this.representation == null) ? (other.representation != null) : !this.representation.equals(other.representation)) {
            return false;
        }
        return true;
    }
}
