import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class ModelFileTest.
 *
 * @author  Maksym Turkot
 * @version 04/25/2021
 */
public class LoadModelFileTest
{
    /**
     * Default constructor for test class ModelFileTest
     */
    public LoadModelFileTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown() {
    }
    
    /**
     * Tests if readTags correctly reads tags from a file.
     */
    @Test
    public void readTagsTest() {
        assertEquals("tagA,tagB,tagC,tagD,", LoadModelFile.readTags("model1.txt").toString());
    }
    
    /**
     * Tests if readTasks corectly reads tasks from a file.
     */
    @Test
    public void readTasksTest() {
        List<Task> taskList = LoadModelFile.readTasks("model1.txt");
        
        String tasks = "";
        String dueDates = "";
        String tags = "";
        for (int i = 0; i < taskList.size(); i++) {
            tasks += taskList.get(i).getTask() + ";";
            dueDates += taskList.get(i).getDueDate() + ";";
            tags += taskList.get(i).getTags().toString() + ";";
        }
        assertEquals("task0;task1;task2;task3;", tasks);
        assertEquals("2021-04-25;2021-04-26;2021-04-25;2025-05-05;", dueDates);
        assertEquals("tagA,tagB,tagC,;tagA,tagC,;tagC,;untagged,;", tags);
    }
}
