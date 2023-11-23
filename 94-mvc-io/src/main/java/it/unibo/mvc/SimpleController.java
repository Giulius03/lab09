package it.unibo.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A simple controller that prints the current string and saves all the printed ones
 */
public final class SimpleController implements Controller {

    private String nextToPrint;
    private final List<String> printedStrings = new ArrayList<>();

    @Override
    public void setNextToPrint(final String next) throws NullPointerException {
        this.nextToPrint = Objects.requireNonNull(next, "It cannot be set a null string");
    }

    @Override
    public String getNextToPrint() {
        if (this.nextToPrint == null) {
            throw new IllegalStateException("The next string to print is unset");
        }
        return this.nextToPrint;
    }

    @Override
    public List<String> getAllPrintedStrings() {
        if (this.printedStrings.isEmpty()) {
            throw new IllegalStateException("There are no printed strings");
        }
        return Collections.unmodifiableList(this.printedStrings);
    }

    @Override
    public void printCurrentString() throws IllegalStateException {
        if (this.nextToPrint == null) {
            throw new IllegalStateException("The string to print is unset");
        }
        this.printedStrings.add(this.nextToPrint);
        System.out.println(this.nextToPrint);   
    }
}
