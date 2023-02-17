import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The test class TaskFileTest.
 *
 * @author  Maksym Turkot
 * @version 04/26/2021
 */
public class TaskFileTest
{
    /**
     * Default constructor for test class TaskFileTest
     */
    public TaskFileTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {
        Model.addTag("tag1");
        Model.addTag("tag2");
        Model.addTag("tag3");
        Model.addTag("tag4");

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            List<String> tags1 = new List<String>();
            List<String> tags2 = new List<String>();
            tags1.add("tag1");
            tags1.add("tag2");
            tags1.add("tag3");
            tags2.add("tag2");
            LocalDate dueDate = LocalDate.parse("01/01/2021", formatter);
            Model.addTask("task1", dueDate, tags1);
            Model.addTask("task2", dueDate, tags2);
        } catch (DateTimeParseException err) {
            System.out.println(err);
        }
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown() {
        Model.clearTags();
        Model.clearTasks();
    }
    
    /**
     * Tests if writeTasks() wtites to a new file, and appends to the existing file.
     */
    @Test
    public void writeTaskstest() {
        TaskFile.writeTasks("task1" + "\n" + "task2" + "\n" + "task3" + "\n", "tasksListTest1.txt");
        TaskFile.writeTasks("task1" + "\n" + "task2" + "\n" + "task3" + "\n", "tasksListTest1.txt");
        TaskFile.writeTasks("task1" + "\n" + "task2" + "\n" + "task3" + "\n", "tasksListTest2.txt");
    }
}
