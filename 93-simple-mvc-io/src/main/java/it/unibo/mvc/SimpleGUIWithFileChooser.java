package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame("My second Java graphical interface");
    private static final int PROPORTION = 3;

    public SimpleGUIWithFileChooser(Controller controller) {
        final JPanel mainCanvas = new JPanel(new BorderLayout());
        final JPanel canvas = new JPanel(new BorderLayout());
        mainCanvas.add(canvas, BorderLayout.NORTH);
        final JTextField textField = new JTextField(controller.getPathString());
        textField.setEditable(false);
        canvas.add(textField, BorderLayout.CENTER);
        final JButton btnBrowse = new JButton("Browse...");
        canvas.add(btnBrowse, BorderLayout.LINE_END);
        final JTextArea textArea = new JTextArea();
        mainCanvas.add(textArea, BorderLayout.CENTER);
        final JButton btnSave = new JButton("Save");
        mainCanvas.add(btnSave, BorderLayout.SOUTH);

        btnBrowse.addActionListener(al -> {
            JFileChooser jfc = new JFileChooser();
            final var result = jfc.showSaveDialog(frame);
            switch (result) {
                case JFileChooser.APPROVE_OPTION -> {
                    controller.setCurrentFile(jfc.getSelectedFile());
                    textField.setText(controller.getPathString()); 
                }
                case JFileChooser.ERROR_OPTION -> {
                    JOptionPane.showMessageDialog(null, result, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnSave.addActionListener(al -> {
            try {
                controller.saveContent(textArea.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setContentPane(mainCanvas);
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
        new SimpleGUIWithFileChooser(new Controller());
    }
}