import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * The test class ListTest.
 *
 * @author  Maksym Turkot
 * @version 04/25/2021
 */
public class ListTest
{
    private List<Integer> list1, list2, listEmpty;

    /**
     * Default constructor for test class ListTest
     */
    public ListTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {
        list1 = new List<>();
        list2 = new List<>();
        listEmpty = new List<>();

        list1.add(-6);
        list1.add(0);
        list1.add(11);
        list1.add(2);
        list1.add(11);
        list1.add(-10);
        list1.add(8);
        list1.add(-28);
        list1.add(11);

        list2.add(0);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(6);
        list2.add(7);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown() {
        list1 = null;
        list2 = null;
        listEmpty = null;
    }

    @Test
    @DisplayName("Test size() method")
    public void sizeTest() {
        assertEquals(9, list1.size());
        assertEquals(8, list2.size());
        assertEquals(0, listEmpty.size());
    }

    @Test
    @DisplayName("Test isEmpty() method")
    public void isEmptyTest() {
        assertFalse(list1.isEmpty());
        assertFalse(list2.isEmpty());
        assertTrue(listEmpty.isEmpty());
    }

    @Test
    @DisplayName("Test contains(E) method")
    public void containsTest() {
        assertTrue(list1.contains(11));
        assertTrue(list1.contains(0));
        assertTrue(list2.contains(7));
        assertTrue(list2.contains(5));

        assertFalse(list1.contains(9));
        assertFalse(list1.contains(-999));
        assertFalse(list2.contains(111));
        assertFalse(list2.contains(-1));
        assertFalse(listEmpty.contains(1));
    }
    
    @Test
    @DisplayName("Test toString() method")
    public void toStringTest() {
        List<Integer> list = new List<>();
        list.add((Integer) 1);
        list.add((Integer) 2);
        list.add((Integer) 3);
        list.add((Integer) 4);
        
        assertEquals("1,2,3,4,", list.toString());
    }

    @Test
    @DisplayName("Test add(E) method")
    public void addTest() {
        assertTrue(list1.add(10));
        assertEquals("-6,0,11,2,11,-10,8,-28,11,10,", list1.toString());

        assertTrue(list1.add(99999));
        assertEquals("-6,0,11,2,11,-10,8,-28,11,10,99999,", list1.toString());

        assertTrue(list2.add(-2));
        assertEquals("0,1,2,3,4,5,6,7,-2,", list2.toString());

        assertTrue(list2.add(0));
        assertEquals("0,1,2,3,4,5,6,7,-2,0,", list2.toString());

        assertTrue(listEmpty.add(1));
        assertEquals("1,", listEmpty.toString());

        assertTrue(listEmpty.add(2));
        assertEquals("1,2,", listEmpty.toString());
    }

    @Test
    @DisplayName("Test remove(E) method")
    public void removeTest() {
        assertTrue(list1.remove((Integer) 11));
        assertEquals("-6,0,2,11,-10,8,-28,11,", list1.toString());

        assertTrue(list1.remove((Integer) 8));
        assertEquals("-6,0,2,11,-10,-28,11,", list1.toString());

        assertTrue(list1.remove((Integer) 11));
        assertEquals("-6,0,2,-10,-28,11,", list1.toString());

        assertFalse(list1.remove((Integer) 5));
        assertEquals("-6,0,2,-10,-28,11,", list1.toString());

        assertTrue(list1.remove((Integer) 11));
        assertEquals("-6,0,2,-10,-28,", list1.toString());

        assertTrue(list2.remove((Integer) 0));
        assertEquals("1,2,3,4,5,6,7,", list2.toString());

        assertFalse(listEmpty.remove((Integer) 7));
        assertEquals("0", listEmpty.toString());
    }

    @Test
    @DisplayName("Test clear() method")
    public void clearTest() {
        list1.clear();
        assertEquals("0", list1.toString());

        list2.clear();
        assertEquals("0", list2.toString());

        listEmpty.clear();
        assertEquals("0", list2.toString());
    }

    @Test
    @DisplayName("Test get(int) method")
    public void getTest() {
        assertEquals(-6, list1.get(0));
        assertEquals(0, list1.get(1));
        assertEquals(-10, list1.get(5));
        assertEquals(11, list1.get(8));

        assertNotEquals(999, list1.get(4));
        assertNotEquals(1, list1.get(9));
        assertNotEquals(1, listEmpty.get(0));
    }

    @Test
    @DisplayName("Test set(int, E) method")
    public void setTest() {
        assertEquals(-6, list1.set(0, 99));
        assertEquals("99,0,11,2,11,-10,8,-28,11,", list1.toString());
        assertEquals(11, list1.set(4, 98));
        assertEquals("99,0,11,2,98,-10,8,-28,11,", list1.toString());
        assertEquals(11, list1.set(8, 97));
        assertEquals("99,0,11,2,98,-10,8,-28,97,", list1.toString());

        assertNotEquals(-99, list1.set(1, 96));
        assertEquals("99,96,11,2,98,-10,8,-28,97,", list1.toString());
        assertEquals(null, listEmpty.set(7, 1));
        assertEquals("0", listEmpty.toString());
    }

    @Test
    @DisplayName("Test add(int, E) method")
    public void addMiddleTest() {
        list1.add(0, 10);
        assertEquals("10,-6,0,11,2,11,-10,8,-28,11,", list1.toString());

        list1.add(6, 99999);
        assertEquals("10,-6,0,11,2,11,99999,-10,8,-28,11,", list1.toString());

        list2.add(7, -2);
        assertEquals("0,1,2,3,4,5,6,-2,7,", list2.toString());

        list2.add(8, -10);
        assertEquals("0,1,2,3,4,5,6,-2,-10,7,", list2.toString());

        list2.add(10, 90);
        assertEquals("0,1,2,3,4,5,6,-2,-10,7,", list2.toString());

        listEmpty.add(0, 1);
        assertEquals("0", listEmpty.toString());

        listEmpty.add(5, 2);
        assertEquals("0", listEmpty.toString());
    }

    @Test
    @DisplayName("Test remove(int) method")
    public void removeMiddleTest() {
        assertEquals(-6, list1.remove(0));
        assertEquals("0,11,2,11,-10,8,-28,11,", list1.toString());

        assertEquals(11, list1.remove(7));
        assertEquals("0,11,2,11,-10,8,-28,", list1.toString());

        assertEquals(-10, list1.remove(4));
        assertEquals("0,11,2,11,8,-28,", list1.toString());

        assertNotEquals(1, list1.remove(1));
        assertEquals("0,2,11,8,-28,", list1.toString());

        assertEquals(null, list1.remove(-100));
        assertEquals("0,2,11,8,-28,", list1.toString());

        assertEquals(null, listEmpty.remove(7));
        assertEquals("0", listEmpty.toString());
    }

    @Test
    @DisplayName("Test indexOf(E) method")
    public void indexOfTest() {
        assertEquals(2, list1.indexOf(11));
        assertEquals(6, list1.indexOf(8));
        assertEquals(2, list1.indexOf(11));
        assertEquals(5, list2.indexOf(5));

        assertEquals(-1, list1.indexOf(999999));
        assertNotEquals(1000, list1.indexOf(0));
        assertNotEquals(0, listEmpty.indexOf(7));
    }

    @Test
    @DisplayName("Test lastIndexOf(E) method")
    public void lastIndexOfTest() {
        assertEquals(8, list1.lastIndexOf(11));
        assertEquals(6, list1.lastIndexOf(8));
        assertEquals(8, list1.lastIndexOf(11));
        assertEquals(5, list2.lastIndexOf(5));

        assertEquals(-1, list1.lastIndexOf(999999));
        assertNotEquals(1000, list1.lastIndexOf(0));
        assertNotEquals(0, listEmpty.lastIndexOf(7));
    }
    
    @Test
    @DisplayName("Test subList(E) method")
    public void subListTest() {
        List<Integer> a = list1.subList(3, 7);
        assertEquals("2,11,-10,8,", a.toString());
        
        List<Integer> b = list1.subList(0, 8);
        assertEquals("-6,0,11,2,11,-10,8,-28,", b.toString());
        
        List<Integer> c = listEmpty.subList(0, 4);
        assertEquals("0", c.toString());
    }
    
    @Test
    @DisplayName("Test remove(E) method with a String type")
    public void removeStringTest() {
        List<String> stringList = new List<>();
        stringList.add("Hi");
        stringList.add("my");
        stringList.add("name");
        stringList.add("is");
        stringList.add("Max");
        
        assertTrue(stringList.remove("name"));
        assertEquals("Hi,my,is,Max,", stringList.toString());

        assertTrue(stringList.remove("is"));
        assertEquals("Hi,my,Max,", stringList.toString());
    }
}