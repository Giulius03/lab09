package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private static final int PROPORTION = 3;

    /**
     * @param controller
     */
    public SimpleGUI(Controller controller) {
        final JPanel canvas = new JPanel(new BorderLayout());
        final JTextField textField = new JTextField();
        canvas.add(textField, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        canvas.add(textArea, BorderLayout.CENTER);
        final JButton btnPrint = new JButton("Print");
        final JButton btnShowHistory = new JButton("Show history");
        final JPanel buttons = new JPanel(new BorderLayout());
        buttons.add(btnPrint, BorderLayout.CENTER);
        buttons.add(btnShowHistory, BorderLayout.LINE_END);
        canvas.add(buttons, BorderLayout.SOUTH);

        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnPrint.addActionListener(al -> {
            if (!textField.getText().isBlank()) {
                try {
                    controller.setNextToPrint(textField.getText());
                    controller.printCurrentString();
                } catch (final Exception e) {
                    JOptionPane.showMessageDialog(frame, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "You have to write something in the text field", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnShowHistory.addActionListener(al -> {
            textArea.setText("");
            try {
                final List<String> history = controller.getAllPrintedStrings();
                history.forEach(c -> textArea.append(c + "\n"));
            } catch (final Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);

        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleGUI(new SimpleController()).display();
    }
}