package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private static final String HOME = System.getProperty("user.home");
    private static final String DEFAULT_CURRENT_FILE = "output.txt";
    private File currentFile;

    /**
     * Constructor with no parameters, it sets the current file as "output.txt" inside the user home folder
     */
    public Controller() {
        this.currentFile = new File(HOME + File.separator + DEFAULT_CURRENT_FILE);
    }

    /**
     * @return the current file
     */
    public File getCurrentFile() {
        return this.currentFile;
    }

    /**
     * Sets the file passed as parameter as the current file
     * @param newFile the file that will become the current file
     */
    public void setCurrentFile(final File newFile) {
        if (!Objects.requireNonNull(newFile).exists()) {
            throw new IllegalArgumentException("The passed file does not exist");
        }
        this.currentFile = newFile;
    }

    /**
     * @return the path of the current file in form of String
     */
    public String getPathString() {
        return this.currentFile.getPath();
    }

    /**
     * Saves a string in the current file
     * @param toSave string to be saved in the current file
     * @throws IOException if there are some problems with the access to the current file
     */
    public void saveContent(final String toSave) throws IOException {
        if (Objects.requireNonNull(toSave).isBlank()) {
            throw new IllegalArgumentException("The string to save must contains some carachters");
        }
        try (PrintStream ps = new PrintStream(this.getPathString(), StandardCharsets.UTF_8)) {
            ps.println(toSave);
        }
    }
}
