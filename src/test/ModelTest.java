import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The test class ModelTest.
 *
 * @author  Maksym Turkot
 * @version 04/26/2021
 */
public class ModelTest {
    /**
     * Default constructor for test class ModelTest
     */
    public ModelTest() {
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
     * Tests if getTags() correctly returns tags in the system
     */
    @Test
    public void getTagsTest() {
        assertEquals(Model.getTags().toString(), "tag1,tag2,tag3,tag4,");
    }

    /**
     * Tests if addTag() correctly adds a tag to the system
     */
    @Test
    public void addTagTest() {
        Model.addTag("tag5");
        Model.addTag("tag6");
        Model.addTag("tag7");
        Model.addTag("tag8");
        assertEquals("tag1,tag2,tag3,tag4,tag5,tag6,tag7,tag8,", Model.getTags().toString());
    }

    /**
     * Tests if removeTag() correctly removes a tag from the system
     */
    @Test
    public void removeTagTest() {
        Model.removeTag("tag2");
        Model.removeTag("tag4");
        assertEquals("tag1,tag3,", Model.getTags().toString());
        
        String tasks = "";
        String tags = "";
        
        // Get info about tasks in the model
        for (int task = 0; task < Model.getTasks().size(); task++) {
            tasks += Model.getTasks().get(task).getTask() + ";";
            tags += Model.getTasks().get(task).getTags().toString() + ";";
        }
        assertEquals("task1;task2;", tasks);
        assertEquals("tag1,tag3,;untagged,;", tags);
    }
    
    /**
     * Tests if clearTags() correctly removes all tags from the system
     */
    @Test
    public void clearTagsTest() {
        Model.clearTags();
        assertTrue(Model.getTags().isEmpty());
        
        String tasks = "";
        String tags = "";
        
        // Get info about tasks in the model
        for (int task = 0; task < Model.getTasks().size(); task++) {
            tasks += Model.getTasks().get(task).getTask() + ";";
            tags += Model.getTasks().get(task).getTags().toString() + ";";
        }
        assertEquals("task1;task2;", tasks);
        assertEquals("untagged,;untagged,;", tags);
    }

    /**
     * Tests if getTags() correctly returns tags in the system
     */
    @Test
    public void getTasksTest() {
        String tasks = "";
        String dueDates = "";
        String tags = "";
        
        // Get info about tasks in the model
        for (int task = 0; task < Model.getTasks().size(); task++) {
            tasks += Model.getTasks().get(task).getTask() + ";";
            dueDates += Model.getTasks().get(task).getDueDate() + ";";
            tags += Model.getTasks().get(task).getTags().toString() + ";";
        }
        assertEquals("task1;task2;", tasks);
        assertEquals("2021-01-01;2021-01-01;", dueDates);
        assertEquals("tag1,tag2,tag3,;tag2,;", tags);
    }
    
    /**
     * Tests if containsTask() correctly determines tasks's presence in the system.
     */
    @Test
    public void containsTaskTest() {
        assertTrue(Model.containsTask("task1"));
        assertTrue(Model.containsTask("task2"));
        assertFalse(Model.containsTask("task3"));
        assertFalse(Model.containsTask("task4"));
    }
    
    /**
     * Tests if addTask() correctly adds a task to the system
     */
    @Test
    public void addTaskTest() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            List<String> tags = new List<String>();
            tags.add("tag1");
            LocalDate dueDate = LocalDate.parse("01/02/2021", formatter);
            Model.addTask("task3", dueDate, tags);
        } catch (DateTimeParseException err) {
            System.out.println(err);
        }
        String tasks = "";
        String dueDates = "";
        String tags = "";
        
        // Get info about tasks in the model
        for (int task = 0; task < Model.getTasks().size(); task++) {
            tasks += Model.getTasks().get(task).getTask() + ";";
            dueDates += Model.getTasks().get(task).getDueDate() + ";";
            tags += Model.getTasks().get(task).getTags().toString() + ";";
        }
        assertEquals("task1;task2;task3;", tasks);
        assertEquals("2021-01-01;2021-01-01;2021-01-02;", dueDates);
        assertEquals("tag1,tag2,tag3,;tag2,;tag1,;", tags);
    }
    
    /**
     * Tests if clearTags() correctly removes all tags from the system
     */
    @Test
    public void clearTasksTest() {
        Model.clearTasks();
        assertTrue(Model.getTasks().isEmpty());
    }
    
    /**
     * Tests if removeTask() correctly removes a task from the system
     */
    @Test
    public void removeTaskTest() {
        Model.removeTask("task1");
        Model.removeTask("task2");
        
        String tasks = "";
        String dueDates = "";
        String tags = "";
        
        // Get info about tasks in the model
        for (int task = 0; task < Model.getTasks().size(); task++) {
            tasks += Model.getTasks().get(task).getTask() + ";";
            dueDates += Model.getTasks().get(task).getDueDate() + ";";
            tags += Model.getTasks().get(task).getTags().toString() + ";";
        }
        assertEquals("", tasks);
        assertEquals("", dueDates);
        assertEquals("", tags);
    }
    
    /**
     * Tests if searchTagsTask() correctly searches for tasks based on tags
     */
    @Test
    public void searchTagsTest() {
        assertEquals(1, Model.searchTags("tag1").size());
        assertEquals(2, Model.searchTags("tag2").size());
        assertEquals(0, Model.searchTags("tag4").size());
        assertEquals(2, Model.searchTags("OR tag1 tag2").size());
        assertEquals(1, Model.searchTags("OR tag1 tag4").size());
        assertEquals(1, Model.searchTags("AND tag1 tag2").size());
        assertEquals(0, Model.searchTags("AND tag1 tag4").size());
    }
    
    /**
     * Tests if searchTagsTask() correctly searches for tasks based on tags
     */
    @Test
    public void searchDatesTest() {
        assertEquals(2, Model.searchDates("01/01/2021").size());
        assertEquals(0, Model.searchDates("01/01/2022").size());
    }
    
    /**
     * Tests if loadModel correctly loads tasks to the system
     */
    @Test
    public void saveModelTest() {
        SaveModelFile.writeFile("SaveModelTest.txt");
    }
    
    /**
     * Tests if loadModel correctly loads tasks to the system
     */
    @Test
    public void loadModelTest() {
        assertEquals("tag1,tag2,tag3,tag4,", Model.getTags().toString());
        
        String oldTasks = "";
        String oldDueDates = "";
        String oldTags = "";
        
        // Get info about tasks in the model
        for (int task = 0; task < Model.getTasks().size(); task++) {
            oldTasks += Model.getTasks().get(task).getTask() + ";";
            oldDueDates += Model.getTasks().get(task).getDueDate() + ";";
            oldTags += Model.getTasks().get(task).getTags().toString() + ";";
        }
        assertEquals("task1;task2;", oldTasks);
        assertEquals("2021-01-01;2021-01-01;", oldDueDates);
        assertEquals("tag1,tag2,tag3,;tag2,;", oldTags);
        
        //---------//
        Model.loadModel("../../data/model1.txt");
        //---------//
        
        assertEquals("tagA,tagB,tagC,tagD,", Model.getTags().toString());
        String tasks = "";
        String dueDates = "";
        String tags = "";
        
        // Get info about tasks in the model
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
