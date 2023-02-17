import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * This class reads from a help file.
 *
 * @author Maksym Turkot
 * @version 04/24/2021
 */
public class HelpFile {
    /**
     * Constructor for objects of class FileManager
     */
    public HelpFile() {
    }

    /**
     * Loads help info from a help file. Prints this info to the termonal.
     * 
     * @param command - command for which info should be printed.
     * @return string of help information
     */
    public static String readHelpFile(String command) {
        try {
            Scanner sc = new Scanner(new File("data/help.txt"));
            String info = "";
            // Search for command info
            while(sc.hasNextLine()) {
                // Check if header matches command
                if (sc.nextLine().equals(command)) {
                    // Store info
                    while(sc.hasNextLine()) {
                        String line = sc.nextLine();
                        // Check if all info was stored
                        if (line.equals("")) {
                            break;
                        }
                        info += line + "\n";
                    }
                    break;
                }
            }
            return info;
        } catch (IOException err) {
            System.out.println(err);
            return "err";
        }
    }
}
