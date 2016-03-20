package test;

import impl.ContactManagerImpl;
import impl.FutureMeetingImpl;
import impl.ContactImpl;
import spec.*;
import static test.TestData.*;
import java.util.Set;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.matchers.JUnitMatchers;
import static org.hamcrest.CoreMatchers.*;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author BBK-PiJ-2015-74 lburge01
 * A class to test ContactManagerImpl
 * ContactManager keeps track of contacts, past and future meetings
 */

public class ContactManagerTest {
	
	static final String FILENAME = "contacts.txt";
	final File cmFile = new File(FILENAME);
	private ContactManager emptyCM, testCM;
	private Set<Contact> singleContactSet, twoContactSet, fiveContactSet, tenContactSet, invalidContactSet;
	
	//--------------------------------Set Up -------------------------------------------------------------------------
	
	/**
	 * Set up a clean ContactManager environment for each test, set up test contact sets and delete data files if they exist
	 */
	@Before 
	public void setUp() {

	     try {
	    	 Path p = FileSystems.getDefault().getPath(FILENAME);
	          if (Files.exists(p)) { 
	        	  Files.delete(p);
	          }
	     } catch (IOException ioEx) {
	    	 ioEx.printStackTrace();
	     }
	     
	     emptyCM = buildEmptyCM(); // empty, no contacts
	     testCM = buildTestCM();   // contains contacts 01, 02, 03, 04, 05
	     singleContactSet = testCM.getContacts(CONTACT_ID_04);
	     twoContactSet = testCM.getContacts(CONTACT_ID_01, CONTACT_ID_02);
	     fiveContactSet = testCM.getContacts(CONTACT_ID_01, CONTACT_ID_02, CONTACT_ID_03, CONTACT_ID_04, CONTACT_ID_05);
	     invalidContactSet = buildInvalidContactSet();
	     System.out.println("SetUp completed");
	}

	@After
	public void tearDown() {
	}
	
	//---------------------------Test Constructor and initial methods ----------------------------
	
	@Test
	public void testContactManagerConstructor() {
		assertEquals(emptyCM.getContactId(), 0);
		assertEquals(emptyCM.getMeetingId(), 0);
		assertTrue(emptyCM.getContacts().isEmpty());
		assertTrue(emptyCM.getMeetings().isEmpty());
	}
	
	@Test
	public void testUpdateContactId() {
		emptyCM.updateContactId();
		emptyCM.updateContactId();
		assertEquals (emptyCM.getContactId(), 2);
	}
	
	@Test
	public void testUpdateMeetingId() {
		emptyCM.updateMeetingId();
		emptyCM.updateMeetingId();
		assertEquals (emptyCM.getMeetingId(), 2);
	}
	
	@Test
	public void testUpdateContactManagerFile() {
		fail("Not yet implemented");
	}
	
	
	//----------------------Test addNewContact-------------------------------------------------------------
	
	@Test
	public void addNewContactReturnsCorrectId() {
		assertTrue(testCM.addNewContact(CONTACT_NAME_06, CONTACT_NOTES_06) == 6);
		assertTrue(testCM.addNewContact(CONTACT_NAME_07, CONTACT_NOTES_07) == 7);
		assertTrue(testCM.addNewContact(CONTACT_NAME_08, CONTACT_NOTES_08) == 8);
		assertTrue(testCM.addNewContact(CONTACT_NAME_09, CONTACT_NOTES_09) == 9);
		assertTrue(testCM.addNewContact(CONTACT_NAME_10, CONTACT_NOTES_10) == 10);
		assertTrue(testCM.getContacts("").size() == 10);
	}
	
	/** 
	 * @test This test checks the size of the contact set is zero before and after the addition of the contact
	 */
	@Test (expected = IllegalArgumentException.class)
	public void addNewContactEmptyNameThrowsException() {	
		assertTrue(emptyCM.getContacts("").size()==0);
		emptyCM.addNewContact(EMPTY_CONTACT_NAME, CONTACT_NOTES_07);
		assertTrue(emptyCM.getContacts("").size()==0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addNewContactEmptyNotesThrowsException() {
		assertTrue(emptyCM.getContacts("").size()==0);
		emptyCM.addNewContact(CONTACT_NAME_04, EMPTY_CONTACT_NOTES);
		assertTrue(emptyCM.getContacts("").size()==0);
	}

	@Test (expected = NullPointerException.class) 
	public void addNewContactNullNameThrowsException() {
		assertTrue(emptyCM.getContacts("").size()==0);
		emptyCM.addNewContact(null, CONTACT_NOTES_07);
		assertTrue(emptyCM.getContacts("").size()==0);
	}
	
	@Test (expected = NullPointerException.class) 
	public void addNewContactNullNotesThrowsException() {
		assertTrue(emptyCM.getContacts("").size()==0);
		emptyCM.addNewContact(CONTACT_NAME_04, null);
		assertTrue(emptyCM.getContacts("").size()==0);
	}
	
	//------------------------Test getContacts(String name)-----------------------------------------------------
	/**
	 * This test builds a new contact set for the search results and therefore contains(Set) or containsAll(Set) 
	 * cannot be used, as the object references are not the same. Therefore the tests look at size and whether
	 * the new sets created are empty
	 */
	
	@Test 
	public void getContactsReturnsContactSetContainingString() {		
		Set<Contact> contactSet = new HashSet<>();
		contactSet = testCM.getContacts("Humpty");
		assertFalse(contactSet.isEmpty());
		assertTrue(contactSet.size() == 1);
	}
	
	@Test
	public void getContactsReturnsTwoContactSetContainingString() {
		Set<Contact> contactSet = new HashSet<>();
		contactSet = testCM.getContacts("Little"); // Little Bo Peep, Little Miss Muffett
		assertFalse(contactSet.isEmpty());
		assertTrue(contactSet.size() == 2);
	}

	@Test 
	public void getContactsEmptySearchStringReturnsAllContacts() {
		Set<Contact> contactSet =  testCM.getContacts("");
		assertFalse(contactSet.isEmpty());
		assertTrue(contactSet.size() == 5);
	}
	
	@Test (expected = NullPointerException.class)
	public void getContactsNullSearchStringThrowsException() {
		testCM.getContacts(NULL_SEARCH_STRING);
	}

	//------------------------------Test getContacts(int... ids)-------------------------------------
	
	@Test
	public void getContactsReturnsContactSetFromId() {
		Set<Contact> contactSet = new HashSet<>();
		contactSet = testCM.getContacts(CONTACT_ID_01,CONTACT_ID_02,CONTACT_ID_03,CONTACT_ID_04,CONTACT_ID_05);
		assertTrue(contactSet.size() == 5);
		assertTrue(contactSet.containsAll(twoContactSet));
		assertTrue(contactSet.containsAll(fiveContactSet));
	}
	
	@Test
	public void getContactsReturnsSingleContactFromId() {
		Set<Contact> contactSet = testCM.getContacts(04);
		assertTrue(contactSet.size() == 1);
		assertTrue(contactSet.containsAll(singleContactSet));
		assertFalse(singleContactSet.isEmpty());
	}

	@Test (expected = IllegalArgumentException.class)
	public void getContactsInvalidIdsThrowsException() {
		testCM.getContacts(INVALID_ID_101, INVALID_ID_102, INVALID_ID_103);
	}
	
	//-----------------------------------More basic tests relating to Contacts methods---------------------------------

	
	
	//-------------------------Test addFutureMeeting-------------------------------------------------------------------
	
		@Test
		public void addFutureMeetingReturnsCorrectID() {
			assertTrue(testCM.addFutureMeeting(twoContactSet, FUTURE_DATE_01) == 1);
			assertTrue(testCM.addFutureMeeting(fiveContactSet, FUTURE_DATE_03) == 2);
		}
		
		@Test (expected = NullPointerException.class)
		public void addFutureMeetingNullContactsThrowsException() {
			testCM.addFutureMeeting(null, FUTURE_DATE_02);
		}
			
		@Test (expected = NullPointerException.class)
		public void addFutureMeetingNullDateThrowsException() {
			emptyCM.addFutureMeeting(twoContactSet, null);
			testCM.addFutureMeeting(twoContactSet, null);
		}
		
		@Test (expected = NullPointerException.class)
		public void addFutureMeetingNullFutureMeetingThrowsException() {
			testCM.addFutureMeeting(null, null);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void addFutureMeetingPastDateThrowsException() {
			testCM.addFutureMeeting(twoContactSet, PAST_DATE_03);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void addFutureMeetingInvalidContactsThrowsException() {
			testCM.addFutureMeeting(invalidContactSet, FUTURE_DATE_01);
		}
		
	
}

