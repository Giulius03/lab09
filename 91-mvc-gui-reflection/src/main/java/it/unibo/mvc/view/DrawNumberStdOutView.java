package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

/**
 * Graphical {@link DrawNumberView} implementation.
 */
public final class DrawNumberStdOutView implements DrawNumberView {

    @Override
    public void setController(final DrawNumberController observer) {
        //mah
    }

    @Override
    public void start() {
        //mah
    }

    @Override
    public void result(final DrawResult res) {
        System.out.println(res.getDescription());
    }
    
}