package test;

import impl.ContactImpl;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import spec.Contact;

/**
 * @author BBK-PiJ-2015-74
 * @since 07.02.2016
 */
public class ContactImplTest {
	
	private Contact testCt1, testCt2;
    private String testName, testName2;
    private String testNotes1, testNotes2;
    private int testID;
	
    @Before
    public void setUp() {
        testID = 10;
        testName = "Humpty_Dumpty";
        testName2 = "Moppet the Kitten";
        testNotes1 = "He's a good egg";
        testNotes2 = "All the king's horses and all the king's men couldn't put Humpty back together again";

        testCt1 = new ContactImpl(testID, testName, testNotes1);
        testCt2 = new ContactImpl(testID, testName2, testNotes2);
    }
	
    
    /** 
     * Test whether constructor is implemented correctly - test ID
     * won't work until getId() is implemented
     */
    @Test
    public void testIDfromConstructor() {
    	assertEquals(testID,testCt1.getId());
    }
    
    /** 
     * Test whether constructor is implemented correctly - test name
     * won't work until getName() is implemented
     */
    @Test
    public void testNamefromConstructor() {
    	assertEquals(testName, testCt1.getName());
    }
    
    /**
     * Test whether constructor is implemented correctly - test notes
     * won't work until getNotes() is implemented
     */
    @Test
    public void testNotesFromConstructor() {
    	assertEquals(testNotes1, testCt1.getNotes());
    }
    
    /**
     * Test constructor throws NullPointerException for null name
     */
    @Test (expected = NullPointerException.class)
    public void testConstructorNullName() {
    	Contact testCt = new ContactImpl(testID, null, testNotes1);
    }
    
    /** 
     * Test constructor throws Illegal ArgumentException for negative ID
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConstructorNegativeID() {
    	Contact testCt = new ContactImpl(-1, testName, testNotes2);
    }
    
    /** 
     * Test constructor throws Illegal ArgumentException for zero ID
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConstructorZeroID() {
    	Contact testCt = new ContactImpl(0, testName, testNotes2);
    }
    
    /**
     * Test constructor throws Illegal Argument Exception for empty name
     * won't work until getName() is implemented
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConstructorEmptyName() {
    	Contact testCt = new ContactImpl(testID, "", testNotes1);
    }
    
    /**
     * Test whether constructor is implemented correctly - null notes
     * won't work until getNotes() is implemented
     * should throw NullPointerException for null notes?
     */
    @Test
    public void testConstructorNullNotes() {
    	Contact testCt = new ContactImpl(testID, testName, null);
    	assertNull(testCt.getNotes());
    }
    
    /**
     * Test whether constructor is implemented correctly - empty notes
     * won't work until getName() is implemented
     */
    @Test
    public void testConstructorEmptyNotes() {
    	Contact testCt = new ContactImpl(testID, testName,"");
    	assertEquals("", testCt.getName());
    }
    
    /**
     * Test whether constructor is implemented correctly - notes with null name
     * Null name should throw NullPointerException
     */
    @Test (expected = NullPointerException.class)
    public void testConstructorNotesWithNullName(){
    	Contact ct = new ContactImpl(testID, null,testNotes1);
    }
    
    /**
	 * Tests whether getId() returns an int 
	 * This test should pass because testID has been defined as an int
	 */
	@Test 
	public void testContactGetIdReturnsInt() {
		assertTrue (testCt1.getId() == (int)testCt1.getId());
	}
    
    /**
	 * Tests whether getId() returns testID
	 * This test will fail until getId() implemented
	 */
	@Test 
	public void testContactGetIdReturnsID() {
		assertEquals (testID, testCt1.getId());
	}
	
	/**
	 * Tests whether getName() returns expected string
	 * This test will fail until getName() implemented
	 */
	@Test
	public void testContactGetNameReturnsExpectedName() {
		assertEquals (testName, testCt1.getName());
	}
	
	/**
	 * Test whether getName() returns a name with spaces included
	 * This test will fail until getName() is implemented
	 */
	@Test
	public void testContactGetNameWithSpaces() {
		assertEquals (testName2, testCt2.getName());
	}
	
	/**
	 * Tests whether getNotes() returns expected string
	 * This test will fail until getNotes() is implemented
	 */
	@Test
	public void testContactGetNotesReturnsNotes() {
		assertEquals (testNotes2, testCt2.getNotes());
	}
	
	/**
	 * Tests whether addNotes(note) takes argument (note) correctly
	 * Fails before Contact is implemented (testing interface only)
	 */
	@Test
	public void contactAddNotesAcceptsArgumentNote() {
		fail("Not yet implemented");
	}
	
	@After
    public void tearDown() {
		testID = 0;
        testName = null;
        testName2 = null;
        testNotes1 = null;
        testNotes2 = null;
        testCt1 = null;
        testCt2 = null;
    }
	
}
