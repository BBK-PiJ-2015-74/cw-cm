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
	
	private Contact testCt1;
    private Contact testCt2;
    private String testFullName;
    private String testNotes1, testNotes2;
    private int testID;
	
    @Before
    public void setUp() {
        testID = 10;
        testFullName = "Humpty Dumpty";
        testNotes1 = "He's a good egg";
        testNotes2 = "All the king's horses and all the king's men couldn't put Humpty back together again";

        testCt1 = new ContactImpl(testID, testFullName, testNotes1);
        testCt2 = new ContactImpl(testID, testFullName, testNotes2);
    }
	
    
    /**
     * Test whether constructor is implemented correctly - tests notes
     */
    @Test
    public void testNotesFromConstructor() {
    	assertEquals(testNotes1, testCt1.getNotes());
    }
    
    /** 
     * Test whether constructor is implemented correctly - tests name
     */
    @Test
    public void testNamefromConstructor() {
    	assertEquals(testFullName, testCt1.getName());
    }
    
    /** 
     * Test whether constructor is implemented correctly - test ID
     */
    @Test
    public void testIDfromConstructor() {
    	assertEquals(testID,testCt1.getId());
    }
    
    /**
     * Test whether constructor is implemented correctly - null name
     */
    @Test
    public void testConstructorNullName() {
    	Contact testCt = new ContactImpl(testID, null, testNotes1);
    	assertNull(testCt.getName());
    }
    
    /**
     * Test whether constructor is implemented correctly - empty name
     */
    @Test
    public void testConstructorEmptyName() {
    	Contact testCt = new ContactImpl(testID, testFullName,"");
    	assertEquals("", testCt.getName());
    }
    
    /**
     * Test whether constructor is implemented correctly - null notes
     */
    @Test
    public void testConstructorNullNotes() {
    	Contact testCt = new ContactImpl(testID, testFullName, null);
    	assertNull(testCt.getNotes());
    }
    
    /**
     * Test whether constructor is implemented correctly - empty notes
     */
    @Test
    public void testConstructorEmptyNotes() {
    	Contact testCt = new ContactImpl(testID, testFullName,"");
    	assertEquals("", testCt.getName());
    }
    
    /**
     * Test whether constructor is implemented correctly - notes with null name
     */
    @Test
    public void testConstructorNotesWithNullName(){
    	Contact testCt = new ContactImpl(testID, null,testNotes1);
    	assertEquals(testNotes1, testCt.getNotes());
    }
    
    /**
	 * Tests whether getId() returns an int 
	 * This test should pass because testID has been defined as an int
	 */
	@Test 
	public void contactGetIdReturnsIntTest() {
		assertTrue (testCt1.getId() == (int)testCt1.getId());
	}
    
    /**
	 * Tests whether getId() returns testID
	 * This test will fail before ContactImpl implemented 
	 */
	@Test 
	public void contactGetIdReturnsID() {
		assertEquals (testID, testCt1.getId());
	}
	
	/**
	 * Tests whether GetName() returns expected string
	 * Fails before Contact is implemented (testing interface only)
	 */
	@Test
	public void contactGetNameReturnsStringTest() {
		fail("Not yet implemented");
	}
	
	/**
	 * Tests whether GetNotes() returns expected string
	 * Fails before Contact is implemented (testing interface only)
	 */
	@Test
	public void contactGetNotesReturnsStringTest() {
		fail("Not yet implemented");
	}
	
	/**
	 * Tests whether addNotes(note) takes parameter (note) correctly
	 * Fails before Contact is implemented (testing interface only)
	 */
	@Test
	public void contactAddNotesAcceptsParameterNoteTest() {
		fail("Not yet implemented");
	}
	
	@After
    public void tearDown() {
		testID = 0;
        testFullName = null;
        testNotes1 = null;
        testNotes2 = null;
        testCt1 = null;
        testCt2 = null;
    }
	
}
