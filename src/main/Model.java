import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class stores references to tasks and tags stored in the 
 * system, and grants other parts of the program access to this data.
 *
 * @author Maksym Turkot
 * @version 04/26/2021
 */
public class Model {  
    private static List<Task> tasks = new List<Task>();
    private static List<String> tags = new List<String>();

    /**
     * Returns list of tags.
     * 
     * @return list of tags
     */
    public static List<String> getTags() {
        return tags;
    }

    /**
     * Adds a tag to the tags list.
     * 
     * @param tag - tag to be added to tags
     */
    public static void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Removes tag from the list.
     * 
     * @param tag - tag to be removed from the list
     * @return false if tag doesn't exist, true otherwise
     */
    public static boolean removeTag(String tag) {
        
        // Check if tag exists in a system
        if (tags.remove(tag)) {
            
            // Go through tasks in a list
            for (int i = 0; i < tasks.size(); i++) {
                
                // Go through tags of a task
                for (int j = 0; j < tasks.get(i).getTags().size(); j++) {
                    
                    // Look for specified tag in a list
                    if(tasks.get(i).getTags().get(j).equals(tag)) {
                        tasks.get(i).getTags().remove(j);
                        
                        // Check if no tags are left for this task
                        if (tasks.get(i).getTags().size() == 0) {
                            tasks.get(i).getTags().add("untagged");
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Removes all tags from the system
     */
    public static void clearTags() {
        tags.clear();
        
        // Go through tasks in a list
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).getTags().clear();
            tasks.get(i).getTags().add("untagged");
        }
    }

    /**
     * Returns list of tasks.
     * 
     * @return list of tasks
     */
    public static List<Task> getTasks() {
        return tasks;
    }

    /**
     * Checks if list of tasks contains a task with specified tag.
     * 
     * @return true if list contains a task with a specified tag, false otherwise
     */
    public static boolean containsTask(String task) {
        
        // Go through tasks in the system
        for (int taskCnt = 0; taskCnt < tasks.size(); taskCnt++) {
            
            // Check if task matches
            if (tasks.get(taskCnt).getTask().equals(task)) {
                return true;
            } 
        }
        return false;
    }

    /**
     * Adds a new task to the system.
     * 
     * @return list of tasks
     */
    public static void addTask(String task, LocalDate dueDate, List<String> tempTags) {
        List<String> taskTags = tempTags.subList(0, tempTags.size());
        Task newTask = new Task(task, dueDate, taskTags);
        tasks.add(newTask);
    }

    /**
     * Removes a passed task from the system.
     * 
     * @return list of tasks
     */
    public static boolean removeTask(String task) {
        
        // Go through tasks in the system
        for (int taskCnt = 0; taskCnt < tasks.size(); taskCnt++) {
            
            // Check if task matches
            if (tasks.get(taskCnt).getTask().equals(task)) {
                tasks.remove(taskCnt);
                return true;
            } 
        }
        return false;
    }

    /**
     * Removes all tasks from the system.
     */
    public static void clearTasks() {
        tasks.clear();
    }

    /**
     * Searches Model for tasks with specified tags.
     * 
     * @param tags - tags to be searched for
     * @return list of found tasks
     */
    public static List<Task> searchTags(String tags) {
        List<Task> resultTasks = new List<Task>();
        String[] tagsArr = tags.split(" ");
        
        // Check if there was an operator used
        if (tagsArr[0].equals("AND")) {
            
            // Go through tasks in the system
            for (int task = 0; task < tasks.size(); task++) {
                boolean containsAll = true;
                
                // Go through passed tags
                for (int tagCnt = 1; tagCnt < tagsArr.length; tagCnt++) {
                    
                    // Check if task contains given tag
                    if (!tasks.get(task).getTags().contains(tagsArr[tagCnt])) {
                        containsAll = false;
                    } 
                }

                // Check if given task contained all passed tags
                if(containsAll) {
                    resultTasks.add(tasks.get(task));
                }
            }
        } else if (tagsArr[0].equals("OR")) {
            
            // Go through tasks in the system
            for (int task = 0; task < tasks.size(); task++) {
                
                // Go through passed tags
                for (int tagCnt = 1; tagCnt < tagsArr.length; tagCnt++) {
                    
                    // Check if task contains given tag
                    if (tasks.get(task).getTags().contains(tagsArr[tagCnt])) {
                        
                        // Check if this task was already added
                        if (!resultTasks.contains(tasks.get(task))) {
                            resultTasks.add(tasks.get(task));
                        }
                    } 
                }
            }
        } else {
            
            // Go through tasks in the system
            for (int task = 0; task < tasks.size(); task++) {
                
                // Check if task contains given tag
                if (tasks.get(task).getTags().contains(tagsArr[0])) {
                    resultTasks.add(tasks.get(task));
                }
            }
        }
        return resultTasks;
    }

    /**
     * Searches Model for tags with specified date or date range.
     * 
     * @param dates - dates to be searched
     * @return list of found tasks
     */
    public static List<Task> searchDates(String dates) {
        List<Task> resultTasks = new List<Task>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dueDate = LocalDate.parse(dates, formatter);
        
        // Go through tasks in the system
        for (int task = 0; task < tasks.size(); task++) {
            
            // Check if this task has a given due date
            if (tasks.get(task).getDueDate().equals(dueDate)) {
                
                // Check if this task was already added
                if (!resultTasks.contains(tasks.get(task))) {
                    resultTasks.add(tasks.get(task));
                }
            } 
        }
        return resultTasks;
    }

    /**
     * Calls ModelFile to load specified model file.
     */
    public static String saveModel(String filename) {
        String response = SaveModelFile.writeFile(filename);
        
        // Check if readTags returned error
        if (!response.equals("ok")) {
            return response;
        }
        return "ok";
    }

    /**
     * Calls ModelFile to load specified model file.
     */
    public static String loadModel(String filename) {
        List<String> tempTags = LoadModelFile.readTags(filename);
        List<Task> tempTasks = LoadModelFile.readTasks(filename);
        
        // Check if readTags returned error
        if (tempTags == null) {
            return "Error";
        }
        
        // Check if readTasks returned error
        if(tempTasks == null) {
            return "Error";
        }
        tags = tempTags;
        tasks = tempTasks;
        return "ok";
    }
}
