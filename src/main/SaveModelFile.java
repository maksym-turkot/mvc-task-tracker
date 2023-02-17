import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * This class writes current model state to 
 * file that can later be read by the ModelFileReader.
 *
 * @author Maksym Turkot
 * @version 04/25/2021
 */
public class SaveModelFile {
    private static PrintWriter fileWriter;
    private static File outputFile;

    /**
     * Constructor for objects of class FileManager
     * 
     * @param filename - name of a file to be created
     * @return status of the method
     */
    public static String writeFile(String filename) {
        try {
            outputFile = new File("data/" + filename);
            outputFile.createNewFile();
            fileWriter = new PrintWriter(outputFile);
            fileWriter.write(writeTags() + writeTasks());
            fileWriter.flush();
            return "ok";
        } catch(IOException e) { 
            System.out.println(e);
            return String.valueOf(e);
        } catch (Exception e) { 
            System.out.println(e);
            return String.valueOf(e);
        }
    }

    /**
     * Reads from model file and returns list of strings.
     * 
     * @return string of tags to be writted
     */
    public static String writeTags() {
        String outputTags = "tags" + "\n";
        outputTags += Model.getTags().toString() + "\n" + "\n";
        return outputTags;
    }

    /**
     * Reads from model file and returns list of strings.
     * 
     * @return string of tasks to be written
     */
    public static String writeTasks() {
        String outputTasks = "tasks" + "\n";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        
        // Write tasks to a string
        for (int i = 0; i < Model.getTasks().size(); i++) {
            outputTasks += Model.getTasks().get(i).getTask() + ",";
            outputTasks += Model.getTasks().get(i).getDueDate().format(formatter) + ",";
            outputTasks += Model.getTasks().get(i).getTags().toString() + "\n";
        }
        return outputTasks;
    }
}