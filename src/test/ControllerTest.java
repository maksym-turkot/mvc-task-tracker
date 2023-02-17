import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The test class ControllerTest.
 *
 * @author  Maksym Turkot
 * @version 04/26/2021
 */
public class ControllerTest {
    /**
     * Default constructor for test class ControllerTest
     */
    public ControllerTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {
        Controller.runAddTagCommand("tag1");
        Controller.runAddTagCommand("tag2");
        Controller.runAddTagCommand("tag3");
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
     * Tests if controller correctly adds a tag to the system.
     */
    @Test
    public void runAddTagCommandTest() {
        Controller.runAddTagCommand("tag4");
        Controller.runAddTagCommand("tag5");
        Controller.runAddTagCommand("tag6");

        assertEquals("tag1,tag2,tag3,tag4,tag5,tag6,", Model.getTags().toString());
    }

    /**
     * Tests if controller correctly removes a tag from the system.
     */
    @Test
    public void runDeleteTagCommandTest() {
        Controller.runDeleteTagCommand("tag2");
        Controller.runDeleteTagCommand("tag3");

        assertEquals("tag1,", Model.getTags().toString());
    }

    /**
     * Tests if controller correctly adds a tag to the system.
     */
    @Test
    public void runDisplayTagsCommandTest() {
        assertEquals("tag1,tag2,tag3,", Controller.runDisplayTagsCommand());
    }

    /**
     * Tests if controller correctly adds a task to the system.
     */
    @Test
    public void runAddTaskCommandTest() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate dueDate1 = LocalDate.parse("04/04/2021", formatter);
            LocalDate dueDate2 = LocalDate.parse("04/05/2021", formatter);
            LocalDate dueDate3 = LocalDate.parse("04/06/2021", formatter);

            Controller.runAddTaskCommand("task1", dueDate1, "tag1");
            Controller.runAddTaskCommand("task1", dueDate1, "tag2");
            Controller.runAddTaskCommand("task1", dueDate1, "");

            Controller.runAddTaskCommand("task2", dueDate2, "tag2");
            Controller.runAddTaskCommand("task2", dueDate2, "tag3");
            Controller.runAddTaskCommand("task2", dueDate2, "");

            Controller.runAddTaskCommand("task3", dueDate3, "");

            String tasks = "";
            String dueDates = "";
            String tags = "";
            
            // Go through tasks in the model
            for (int task = 0; task < Model.getTasks().size(); task++) {
                tasks += Model.getTasks().get(task).getTask() + ";";
                dueDates += Model.getTasks().get(task).getDueDate() + ";";
                tags += Model.getTasks().get(task).getTags().toString() + ";";
            }
            assertEquals("task1;task2;task3;", tasks);
            assertEquals("2021-04-04;2021-04-05;2021-04-06;", dueDates);
            assertEquals("tag1,tag2,;tag2,tag3,;untagged,;", tags);
        } catch (DateTimeParseException err) {
            System.out.println(err);
            System.out.println("Invalid date format.");
        }
    }

    /**
     * Tests if controller correctly triggers saving of tasks.
     */
    @Test
    public void runSaveTasksCommandTest() {
        Controller.runSaveTasksCommand("runSaveTasksCommandTest.txt");
    }

    /**
     * Tests if controller correctly triggers loading of tasks from a file.
     */
    @Test
    public void runLoadTasksCommandTest() {
        assertEquals("tag1,tag2,tag3,", Model.getTags().toString());

        String oldTasks = "";
        String oldDueDates = "";
        String oldTags = "";
        
        // Go through tasks in the model
        for (int task = 0; task < Model.getTasks().size(); task++) {
            oldTasks += Model.getTasks().get(task).getTask() + ";";
            oldDueDates += Model.getTasks().get(task).getDueDate() + ";";
            oldTags += Model.getTasks().get(task).getTags().toString() + ";";
        }
        assertEquals("", oldTasks);
        assertEquals("", oldDueDates);
        assertEquals("", oldTags);

        //---------//
        Controller.runLoadTasksCommand("model1.txt");
        //---------//

        assertEquals("tagA,tagB,tagC,tagD,", Model.getTags().toString());
        String tasks = "";
        String dueDates = "";
        String tags = "";
        
        // Go through tasks in the model
        for (int task = 0; task < Model.getTasks().size(); task++) {
            tasks += Model.getTasks().get(task).getTask() + ";";
            dueDates += Model.getTasks().get(task).getDueDate() + ";";
            tags += Model.getTasks().get(task).getTags().toString() + ";";
        }
        assertEquals("task0;task1;task2;task3;", tasks);
        assertEquals("2021-04-25;2021-04-26;2021-04-25;2025-05-05;", dueDates);
        assertEquals("tagA,tagB,tagC,;tagA,tagC,;tagC,;untagged,;", tags);
    }
}
