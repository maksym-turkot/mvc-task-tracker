import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

/**
 * WThis class writes list of tasks to a file.
 *
 * @author Maksym Turkot
 * @version 04/26/2021
 */
public class TaskFile {
    /**
     * Constructor for objects of class FileManager
     * 
     * @param list - list of tasks to be printed
     * @param filename - name of a file to be written to
     */
    public static boolean writeTasks(String list, String filename) {
        try {
            FileWriter fileWriter;
            File outputFile = new File("data/" + filename);
            
            // Check if specified file already exisists
            if (outputFile.exists()) {
                fileWriter = new FileWriter("data/" + filename, true);
                fileWriter.write(list);
                fileWriter.flush();
            } else {
                outputFile.createNewFile();
                fileWriter = new FileWriter(outputFile, true);
                fileWriter.write(list);
                fileWriter.flush();
            }
            
            fileWriter.close();
            return true;
        } catch(IOException e) { 
            System.out.println(e);
            return false;
        } catch (Exception e) { 
            System.out.println(e);
            return false;
        }
    }
}
