package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import spec.Contact;

public class ContactImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
	}
	
	private int testID = 1;
	private String testName = "Humpty Dumpty";
	private String testEmptyNote = "";
	private String testNotes = "He's a good egg";
	
	/**
	 * Tests whether getId() returns an int 
	 * Fails before Contact is implemented (testing interface only)
	 */
	@Test 
	public void contactGetIdReturnsIntTest() {
		fail("Not yet implemented");
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
	public void tearDown() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
}
