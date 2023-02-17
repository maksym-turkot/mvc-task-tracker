import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Write a description of class Controller here.
 *
 * @author Maksym Turkot
 * @version 04/26/2021
 */
public class Controller {
    private static List<String> taskTags = new List<String>();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static Scanner input = new Scanner(System.in);
    
    /**
     * Constructor for objects of class Controller
     */
    public Controller(){
    }

    /**
     * This method determines if user wants to save tasks.
     * 
     * @param command - command on which help was requested
     * @return info to print out
     */
    public static String runExitCommand(String command) {
        // Check if user wants to save tasks
        if(command.equals("Y") || command.equals("y")) { 
            return "save";
        } else if (command.equals("N") || command.equals("n")) {
            return "";
        } else {
            return "Unknown command: " + command;
        }
    }

    /**
     * This method calls HelpFile to return a string
     * with info from the help file.
     * 
     * @param command - command on which help was requested
     * @return info to print out
     */
    public static String runHelpCommand(String command) {
        // Check which command user wants info about
        switch(command) {
            case "":
                return "";
            case "msg":
                return HelpFile.readHelpFile("msg");
            case "exit":
            case "e":
                return HelpFile.readHelpFile("e");
            case "help":
            case "h":
                return HelpFile.readHelpFile("h");
            case "add-tag":
            case "atg":
                return HelpFile.readHelpFile("atk");
            case "delete-tag":
            case "det":
                return HelpFile.readHelpFile("det");
            case "display-tags":
            case "dit":
                return HelpFile.readHelpFile("dit");
            case "add-task":
            case "atk":
                return HelpFile.readHelpFile("atk");
            case "search-tags":
            case "st":
                return HelpFile.readHelpFile("st");
            case "search-dates":
            case "sd":
                return HelpFile.readHelpFile("sd");
            case "search-tags-dates":
            case "std":
                return HelpFile.readHelpFile("std");
            case "search-text":
            case "stxt":
                return HelpFile.readHelpFile("stxt");
            case "save-tasks":
            case "stk":
                return HelpFile.readHelpFile("stk");
            case "load-tasks":
            case "ltk":
                return HelpFile.readHelpFile("ltk");
            default:
                return "Unknown command: " + command;
        }
    }

    /**
     * This method adds new tag to the system
     * 
     * @param tag - a new tag to be added
     * @return status of method
     */
    public static String runAddTagCommand(String tag) {
        // Check if entered tag already exists
        if (Model.getTags().contains(tag)) {
            return "Tag " + tag + " already exists";
        } else {
            Model.addTag(tag);
            return "success";
        }
    }

    /**
     * This method deletes a tag from the system.
     * 
     * @param tag - tag to be deleted from the system
     * @return status of method
     */
    public static String runDeleteTagCommand(String tag) {
        // Check if tag exists in the system
        if (Model.removeTag(tag)) {
            return "success";
        } else {
            return "Tag " + tag + " doesn't exist";
        }
    }

    /**
     * This method displays tags in the system.
     * 
     * @return string of tags
     */
    public static String runDisplayTagsCommand() {
        return Model.getTags().toString();
    }

    /**
     * This method controls addition of tag to the syatem.
     * 
     * @param task - text of task
     * @param dueStr - string of a due date
     * @param tag - tag to be added to the task
     * @return status of method
     */
    public static String runAddTaskCommand(String task, LocalDate dueDate, String tag) {
        // Check if tag exists in the system
        if (Model.getTags().contains(tag)) {
            // Chek if user already assigned this tag
            if (!taskTags.contains(tag)) {
                taskTags.add(tag);
                return "success";
            } else {
                return "Tag " + tag + " already assigned.";
            }
        } else if (tag.equals("")) {
            // Check if no tags were assigned
            if (taskTags.isEmpty()) {
                taskTags.add("untagged");
            }
            Model.addTask(task, dueDate, taskTags);
            taskTags.clear();
            return "success";
        } else {
            return "Tag " + tag + " does not exist.";
        }
    }

    /**
     * Controls search of tasks based on tags provided.
     * 
     * @param tags - string of tags to be searched
     * @return list of searched tags
     */
    public static List<Task> runSearchTagsCommand(String tags) {
        return Model.searchTags(tags);
    }

    /**
     * Controls search of tasks based on dates provided.
     * 
     * @param dates - string of dates to be searched
     * @return list of searched tags
     */
    public static List<Task> runSearchDatesCommand(String dates) {
        return Model.searchDates(dates);
    }

    /**
     * Controls search of tasks based on a combination of tags and dates provided.
     */
    public static void runSearchTagsDatesCommand() {
        System.out.println("In runSearchTagsDates");
    }

    /**
     * Controls search of tasks based on task text provided.
     */
    public static void runSearchTextCommand() {
        System.out.println("In runSearchText");
    }

    /**
     * Calls Model to save tasks currently in the system.
     * 
     * @param filename - name of a file to have tasks saved to.
     * @return status of the method
     */
    public static String runSaveTasksCommand(String filename) {
        String response = Model.saveModel(filename);
        // Check if model was saved successfully
        if (response.equals("ok")) {
            return response;
        }
        return "Error reading " + filename;
    }

    /**
     * Calls Model to load tasks from a given file.
     * 
     * @param filename - name of a file to have tasks loaded from
     * @return statust of the method
     */
    public static String runLoadTasksCommand(String filename) {
        // Check if model was loded successfully
        if (Model.loadModel(filename).equals("ok")) {
            return "ok";
        }
        return "Error reading " + filename;
    }

    /**
     * A helper method used for date parsing.
     * 
     * @param date - dstring to be parsed
     * @return parsed local date
     */
    public static LocalDate parseDate(String date) {
        try {
            LocalDate dueDate = LocalDate.parse(date, formatter);
            return dueDate;
        } catch (DateTimeParseException err) {
            System.out.println(err);
            return null;
        }
    }

    /**
     * Prints out an itemized list and performs corresponding actions.
     * 
     * @param resultTasks - lists of tasks found by the Model
     */
    public static void printItemizedList(List<Task> resultTasks) {
        
        // Check if list is empty
        if (resultTasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            String list = "";
            
            // Store each tiem in a list
            for (int i = 0; i < resultTasks.size(); i++) {
                list += (i + 1) + ": " + resultTasks.get(i).getTask() + " (" + resultTasks.get(i).getDueDate().format(formatter) + ")" + "\n";
            }
            System.out.println(list);
            boolean exitList = false;
            
            // Itemized list cycle
            while (!exitList) {
                System.out.println("a. add-tag b. edit c. delete d. save");
                System.out.println("Action: ");
                String action = input.nextLine();
                
                // Perform different actions based ou user's input
                switch(action) {
                    case "a":
                    
                        // Task number reading cycle
                        while (true) { 
                            System.out.print("Task number: ");
                            String taskNum = input.nextLine();
                            
                            // Check if valid task number was procided
                            if (Integer.parseInt(taskNum) > resultTasks.size() || Integer.parseInt(taskNum) < 1) {
                                System.out.println("No such task number.");
                            } else {
                                
                                // Tag reading cycle
                                while (true) {
                                    System.out.print("Tag: ");
                                    String tag = input.nextLine();
                                    
                                    // Check if model contains the tag
                                    if (tag.equals("")){
                                        break;
                                    } else if (!Model.getTags().contains(tag)) {
                                        System.out.println("Tag " + tag + " doesn't exist.");
                                    } else if(resultTasks.get(Integer.parseInt(taskNum) - 1).getTags().contains(tag)) {
                                        System.out.println("This task already has this tag.");
                                    } else {
                                        resultTasks.get(Integer.parseInt(taskNum) - 1).getTags().add(tag);
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    case "b":
                    
                        // Task number reading cycle
                        while (true) { 
                            System.out.print("Task number: ");
                            String taskNum = input.nextLine();
                            
                            // Check if valid task number was procided
                            if (Integer.parseInt(taskNum) > resultTasks.size() || Integer.parseInt(taskNum) < 1) {
                                System.out.println("No such task number.");
                            } else {
                                boolean exitEdit = false;
                                
                                // Edit command reqding cycle
                                while (!exitEdit) {
                                    System.out.println("a. task b. date");
                                    System.out.print("Action: ");
                                    String edit = input.nextLine();
                                    
                                    // Perform different actions based on user's input
                                    switch (edit) {
                                        case "a":
                                        
                                            // Task reading cycle
                                            while (true) {
                                                System.out.print("Task: ");
                                                String task = input.nextLine();
                                                
                                                // Check if Model already contains specified task
                                                if (Model.containsTask(task)) {
                                                    System.out.println("Task " + task + " already exists.");
                                                } else {
                                                    resultTasks.get(Integer.parseInt(taskNum) - 1).setTask(task);
                                                    break;
                                                }
                                            }
                                            break;
                                        case "b":
                                        
                                            // Date reading cycle
                                            while (true) {
                                                System.out.print("Due date: ");
                                                String dueDateStr = input.nextLine();
                                                LocalDate dueDate = Controller.parseDate(dueDateStr);
                                                
                                                // Check if parsing failed
                                                if (dueDate == null) {
                                                    System.out.println("Invalid date format");
                                                } else {
                                                    resultTasks.get(Integer.parseInt(taskNum) - 1).setDueDate(dueDate);
                                                    break;
                                                }
                                            }
                                            break;
                                        case "":
                                            exitEdit = true;
                                            break;
                                        default:
                                            System.out.println("Unknown action: " + action);
                                            break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    case "c":
                    
                        // Task number reading cycle
                        while (true) { 
                            System.out.print("Task number: ");
                            String taskNum = input.nextLine();
                            
                            // Check if valid task number was procided
                            if (Integer.parseInt(taskNum) > resultTasks.size() || Integer.parseInt(taskNum) < 1) {
                                System.out.println("No such task number.");
                            } else {
                                Model.removeTask(resultTasks.get(Integer.parseInt(taskNum) - 1).getTask());
                                break;
                            }
                        }
                        break;
                    case "d":
                    
                        // File name reading cycle
                        while (true) {
                            System.out.print("File name: ");
                            String filename = input.nextLine();
                            
                            // Check if tasks could be written to a file
                            if (TaskFile.writeTasks(list, filename)) {
                                break;
                            }
                            System.out.println("Could not write tasks to " + filename);
                        }
                    case "":
                        exitList = true;
                        break;
                    default: 
                        System.out.println("Unknown action: " + action);
                        break;
                }
            }
        }
    }
}
