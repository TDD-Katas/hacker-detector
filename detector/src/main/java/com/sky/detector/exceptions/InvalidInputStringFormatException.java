/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.detector.exceptions;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class InvalidInputStringFormatException extends RuntimeException {

    public InvalidInputStringFormatException() {
    }

    public InvalidInputStringFormatException(String message) {
        super(message);
    }

    public InvalidInputStringFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
