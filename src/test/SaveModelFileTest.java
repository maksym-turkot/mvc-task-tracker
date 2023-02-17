import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The test class SaveModelFileTest.
 *
 * @author  Maksym Turkot
 * @version 04/25/2021
 */
public class SaveModelFileTest {
    /**
     * Default constructor for test class SaveModelFileTest
     */
    public SaveModelFileTest() {
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
     * Tests if writeFile correctly writes model to file.
     */
    @Test
    public void fileWriteTest() {
        SaveModelFile.writeFile("writeFileTest.txt");
    }

    /**
     * Tests if writeTags correctly writes tags to the file.
     */
    @Test
    public void writeTagsTest() {
        assertEquals("tags" + "\n" + "tag1,tag2,tag3,tag4," + "\n\n", SaveModelFile.writeTags());
    }

    /**
     * Tests if writeTasks correctly writes tasks to the file.
     */
    @Test
    public void writeTasksTest() {
        assertEquals("tasks" + "\n" + "task1,01/01/2021,tag1,tag2,tag3," + "\n" + 
            "task2,01/01/2021,tag2," + "\n", SaveModelFile.writeTasks());
    }
}
