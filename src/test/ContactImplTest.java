package test;

import impl.ContactImpl;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import spec.Contact;

/**
 * @author BBK-PiJ-2015-74
 * @since 07.02.2016
 */
public class ContactImplTest {
	
	private Contact testContact1;
    private Contact testContact2;
    private String testFullName;
    private String testNotes1, testNotes2;
    private int testID;
	
    @Before
    public void setUp() {
        testID = 10;
        testFullName = "Humpty Dumpty";
        testNotes1 = "He's a good egg";
        testNotes2 = "All the king's horses and all the king's men couldn't put Humpty back together again";

        testContact1 = new ContactImpl(testID, testFullName, testNotes1);
        testContact2 = new ContactImpl(testID, testFullName, testNotes2);
    }
	
    /**
	 * Tests whether getId() returns testID
	 * This test will fail before ContactImpl implemented 
	 */
	@Test 
	public void contactGetIdReturnsID() {
		assertEquals (testID, testContact1.getId());
	}
    
    /**
	 * Tests whether getId() returns an int 
	 * This test should pass because testID has been defined as an int
	 */
	@Test 
	public void contactGetIdReturnsIntTest() {
		assertTrue (testContact1.getId() == (int)testContact1.getId());
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

        testContact1 = null;
        testContact1 = null;
    }
	
}
