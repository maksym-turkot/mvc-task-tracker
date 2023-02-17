import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class writes current model state to 
 * file that can later be read by the ModelFileReader.
 *
 * @author Maksym Turkot
 * @version 04/25/2021
 */
public class LoadModelFile {
    /**
     * Constructor for objects of class FileManager
     */
    public LoadModelFile(){
    }

    /**
     * Reads from model file and returns list of strings.
     * 
     * @param filename - name of a file to be read
     * @return list of tags read
     */
    public static List<String> readTags(String filename) {
        List<String> tags = new List<String>();
        try {
            Scanner sc = new Scanner(new File("data/" + filename));
            String[] tagsArr = {};

            // Search for tags in the file
            while(sc.hasNextLine()) {
                // Check if we found tags
                if (sc.nextLine().equals("tags")) {
                    // Store tags
                    while(true) {
                        String line = sc.nextLine();
                        // Check if all info was stored
                        if (line.equals("")) {
                            break;
                        }
                        tagsArr = line.split(",");
                    }
                    
                    // Store tags
                    for (String tag : tagsArr) {
                        tags.add(tag);
                    }
                    break;
                }
            }
            return tags;
        } catch (IOException err) {
            System.out.println(err);
            return null;
        }
    }

    /**
     * Reads from model file and returns list of strings.
     * 
     * @param filename - name of a file to be read
     * @return list of tasks read
     */
    public static List<Task> readTasks(String filename) {
        try {
            Scanner sc = new Scanner(new File("data/" + filename));
            List<Task> tasks = new List<Task>();
            String[] taskItems = {};

            // Search for tasks in the file
            while(sc.hasNextLine()) {
                // Check if we found tsks
                if (sc.nextLine().equals("tasks")) {
                    // Store tasks
                    while(sc.hasNextLine()) {
                        String line = sc.nextLine();
                        String taskText;
                        LocalDate dueDate;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                        List<String> tags = new List<String>();
                        
                        // Check if all info was stored
                        if (line.equals("")) {
                            break;
                        }
                        
                        taskItems = line.split(",");
                        
                        taskText = taskItems[0];
                        dueDate = LocalDate.parse(taskItems[1], formatter);
                        
                        // Store task tags
                        for (int i = 2; i < taskItems.length; i++) {
                            tags.add(taskItems[i]);
                        }
                        
                        Task newTask = new Task(taskText, dueDate, tags);
                        tasks.add(newTask);
                    }
                    break;
                }
            }
            return tasks;
        } catch (IOException err) {
            System.out.println(err);
            return null;
        } catch (DateTimeParseException err) {
            System.out.println(err);
            return null;
        }
    }
}
