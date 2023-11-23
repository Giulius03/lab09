package it.unibo.mvc;

import java.util.List;

/**
 * Simple controller of I/O access
 */
public interface Controller {
    /**
     * Sets the next string to print
     * @param next The next string to print 
     * @throws NullPointerException If the string to print is null
     */
    void setNextToPrint(String next) throws NullPointerException;

    /**
     * Gets the next string to print
     * @return The next string to print
     */
    String getNextToPrint();

    /**
     * Gets the history of the printed strings
     * @return A list of strings in which there are all the printed strings
     */
    List<String> getAllPrintedStrings();

    /**
     * Prints the current string
     * @throws IllegalStateException If the current string is unset
     */
    void printCurrentString() throws IllegalStateException;
}
