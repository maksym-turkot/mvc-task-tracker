import java.time.*;

/**
 * This class constructs objects of Tasks.
 *
 * @author Maksym Turkot
 * @version 04/25/2021
 */
public class Task {
    private static int taskCnt = 1;
    private List<String> tags;
    private String task;
    private LocalDate dueDate;
    private int id;
    
    /**
     * Constructor for objects of class Task with no parameters.
     */
    public Task() {
        this.task = "noTask" + id;
        this.dueDate = null;
        this.tags.add("untagged");
        this.id = taskCnt++;
    }
    
    /**
     * Constructor for objects of class Task with text, 
     * dueDate, and without tags.
     * 
     * @param text - description of the task
     * @param dueDate - the date task is due
     */
    public Task(String text, LocalDate dueDate) {
        this.dueDate = dueDate;
        this.tags.add("untagged");
        this.id = taskCnt++;
    }
    
    /**
     * Constructor for objects of class Task with text, 
     * dueDate, and tags.
     * 
     * @param text - description of the task
     * @param dueDate - the date task is due
     * @param tags - list of tags
     */
    public Task(String task, LocalDate dueDate, List<String> tags) {
        this.task = task;
        this.dueDate = dueDate;
        this.tags = tags;
        this.id = taskCnt++;
    }
    
    /**
     * This method returns list of tasks.
     * 
     * @return list of tasks
     */
    public List<String> getTags() {
        return this.tags;
    }
    
    /**
     * This method returns the task itself.
     * 
     * @return task itself
     */
    public String getTask() {
        return this.task;
    }
    
    /**
     * This method sets task of this task.
     * 
     * @param task - task to be set
     */
    public void setTask(String task) {
        this.task = task;
    }
    
    /**
     * This method returns the due date of a task.
     * 
     * @return due date of a task
     */
    public LocalDate getDueDate() {
        return this.dueDate;
    }
    
    /**
     * This method returns the due date of a task.
     * 
     * @return due date of a task
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}