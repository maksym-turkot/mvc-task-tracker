import java.util.*;
import java.time.*;
import java.time.format.*;

/**
 * This class creates interface in the program terminal,
 * displays information and reads user's inputs.
 *
 * @author Maksym Turkot
 * @version 04/26/2021
 */
class Interface {
    
    /**
     * Main method that runs the program.
     * 
     * @param args - defaut parameter
     */
    static public void main(String[] args) { 
        Scanner input   = new Scanner(System.in);
        String  command = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        // Run a cycle of command reading
        while (true) {
            System.out.print("> ");
            command = input.nextLine();

            // Check what command user inputted
            if(command.equals("exit") || command.equals("e")) {
                // Run a cycle of command reading
                while (true) {
                    System.out.print("Would you like to save your tasks? (Y/N): ");
                    command = input.nextLine();
                    String info = Controller.runExitCommand(command);

                    // Check if controller allows exit
                    if (info.equals("")) {
                        break;
                    } else if (info.equals("save")) {
                        // Run a cycle of command reading
                        while (true) {
                            System.out.print("File name: ");
                            String filename = input.nextLine();

                            // Check if user wants to exit
                            if (filename.equals("")) {
                                break;
                            } 

                            String response = Controller.runSaveTasksCommand(filename);

                            // Check if saving was successful
                            if (response.equals("ok")) {
                                break;
                            } 
                            System.out.println(response);
                        }
                        break;
                    } else {
                        System.out.println("Unknown command: " + command);
                    }
                }
                System.out.println("Exiting program");
                break;
            } 
            else if (command.equals("help") || command.equals("h")) {
                System.out.println(Controller.runHelpCommand("msg"));

                // Run a cycle of command reading
                while (true) {
                    System.out.print("Command help: ");
                    command = input.nextLine();
                    String info = Controller.runHelpCommand(command);

                    // Check if operation succeeded
                    if (info.equals("")) {
                        break;
                    }
                    System.out.println(info);
                }
            }
            else if (command.equals("add-tag") || command.equals("atg")) {
                // Run a cycle of command reading
                while (true) {
                    System.out.print("Tag: ");
                    String tag = input.nextLine();

                    // Check if user wants to exit
                    if (tag.equals("")) {
                        break;
                    } 

                    String response = Controller.runAddTagCommand(tag);

                    // Check if operation failed
                    if (!response.equals("success")) {
                        System.out.println(response);
                    }
                }
            }
            else if (command.equals("delete-tag") || command.equals("det")) {
                // Run a cycle of command reading
                while (true) {
                    System.out.print("Delete tag: ");
                    String tag = input.nextLine();

                    // Check if user wants to exit
                    if (tag.equals("")) {
                        break;
                    } 

                    String response = Controller.runDeleteTagCommand(tag);

                    // Check if operation failed
                    if (!response.equals("success")) {
                        System.out.println(response);
                    }
                }
            }
            else if (command.equals("display-tags") || command.equals("dit")) {
                System.out.println(Controller.runDisplayTagsCommand());
            }
            else if (command.equals("add-task") || command.equals("atk")) {
                String task;
                LocalDate dueDate;
                // Run a cycle of command reading
                while (true) {
                    System.out.print("Task: ");
                    task = input.nextLine();
                    // Check if task already exists in the system
                    if (!Model.containsTask(task)) {
                        break;
                    }
                    System.out.println("Task " + task + " already exists.");
                }
                
                // Run a cycle of command reading
                while (true) {
                    System.out.print("Due date: ");
                    String dueDateStr = input.nextLine();
                    try {
                        dueDate = LocalDate.parse(dueDateStr, formatter);
                        break;
                    } catch (DateTimeParseException err) {
                        System.out.println(err);
                        System.out.println("Invalid date format.");
                    }
                }
                
                // Run a cycle of command reading
                while (true) {
                    System.out.print("Tag: ");
                    String tag = input.nextLine();
                    // Check if user wants to exit
                    if (tag.equals("")) {
                        Controller.runAddTaskCommand(task, dueDate, tag);
                        break;
                    } 

                    String response = Controller.runAddTaskCommand(task, dueDate, tag);

                    // Check if operation failed
                    if (!response.equals("success")) {
                        System.out.println(response);
                    }
                }
            }
            else if (command.equals("search-tags") || command.equals("st")) {
                System.out.print("Tags to search: ");
                String tags = input.nextLine();
                Controller.printItemizedList(Controller.runSearchTagsCommand(tags));
            }
            else if (command.equals("search-dates") || command.equals("sd")) {
                System.out.print("Dates to search: ");
                String dates = input.nextLine();
                Controller.printItemizedList(Controller.runSearchDatesCommand(dates));
            }
            else if (command.equals("search-tags-dates") || command.equals("std")) {
                Controller.runSearchTagsDatesCommand();
            }
            else if (command.equals("search-text") || command.equals("stxt")) {
                Controller.runSearchTextCommand();
            }
            else if (command.equals("save-tasks") || command.equals("stk")) {
                // Run a cycle of command reading
                while (true) {
                    System.out.print("File name: ");
                    String filename = input.nextLine();

                    // Check if user wants to exit
                    if (filename.equals("")) {
                        break;
                    } 

                    String response = Controller.runSaveTasksCommand(filename);

                    // Check if operation succeeded
                    if (response.equals("ok")) {
                        break;
                    } 
                    System.out.println(response);
                }
            }
            else if (command.equals("load-tasks") || command.equals("ltk")) {
                // Run a cycle of command reading
                while (true) {
                    System.out.print("File name: ");
                    String filename = input.nextLine();

                    // Check if user wants to exit
                    if (filename.equals("")) {
                        break;
                    } 

                    String response = Controller.runLoadTasksCommand(filename);

                    // Check if operation succeeded
                    if (response.equals("ok")) {
                        break;
                    } 
                    System.out.println(response);
                }
            }
            else {
                // default condition
                System.out.println("Unknown command: " + command);
            }
        }
        input.close();
    }

}