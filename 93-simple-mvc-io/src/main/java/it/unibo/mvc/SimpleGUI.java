package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("My first Java graphical interface");
    private static final int PROPORTION = 3;

    public SimpleGUI(final Controller controller) {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextArea textArea = new JTextArea();
        canvas.add(textArea, BorderLayout.CENTER);
        final JButton btnSave = new JButton("Save");
        canvas.add(btnSave, BorderLayout.SOUTH);

        btnSave.addActionListener(al -> {
            try {
                controller.saveContent(textArea.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int)screenSize.getWidth();
        final int sh = (int)screenSize.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleGUI(new Controller());
    }
}
