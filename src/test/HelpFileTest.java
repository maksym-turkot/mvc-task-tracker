import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class HelpFileTest.
 *
 * @author  Maksym Turkot
 * @version 04/24/2021
 */
public class HelpFileTest {
    /**
     * Default constructor for test class HelpFileTest
     */
    public HelpFileTest() {
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
     * Tests if readHelpFile() Correctly displays file info in the terminal.
     *
     * Called after every test case method.
     */
    @Test
    public void readHelpFileTest() {
        HelpFile.readHelpFile("msg");
        HelpFile.readHelpFile("atk");
        HelpFile.readHelpFile("st");
        HelpFile.readHelpFile("ltk");
    }
}
