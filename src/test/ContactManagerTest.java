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

public class ContactManagerTest {
	
	// list fields needed for methods
	// ContactManager keeps track of contacts, past and future meetings
	// When the application is closed, all data must be stored in a text file called contacts.txt, or can use XML, etc
	// This file must be read at start-up to recover all data introduced in a former session
	
	private int contactId;
	private int meetingId;
	private Calendar cmDate;
	private Set<Contact> cmContacts, setOf2TestContacts, setOfInvalidContacts;
	private List<Meeting> cmMeetings, cm5Meetings;
	private ContactManager emptyCm, cm5;
	static final String FILENAME = "contacts.txt";
	final File cmFile = new File(FILENAME);
	private Contact contact1;
	
	//--------------------------------Set Up -------------------------------------------
	
	/**
	 * Set up a clean ContactManager environment for each test
	 */
	@Before 
	public void setUp() {
		
		//Set time to current system time
		cmDate = Calendar.getInstance();
		
		//Delete all data files if they exist
	     try {
	    	 Path p = FileSystems.getDefault().getPath(FILENAME);
	          if (Files.exists(p)) { 
	        	  Files.delete(p);
	          }
	     } catch (IOException ioEx) {
	    	 ioEx.printStackTrace();
	     }
	    
	    //Create a test set of 2 contacts 
	    setOf2TestContacts = createSetof2TestContacts();
	    
		//Create a test set of 2 invalid contacts
	    //** to do ***
		setOfInvalidContacts = new HashSet<>();
		
		    
		//Create a new empty ContactManager implementation with no contacts or meetings
		emptyCm = new ContactManagerImpl();
		
		//Create a ContactManager implementation with 5 contacts
		cm5 = new ContactManagerImpl();
		cm5.addNewContact(CONTACT_NAME_01, CONTACT_NOTES_01);
		cm5.addNewContact(CONTACT_NAME_02, CONTACT_NOTES_02);
		cm5.addNewContact(CONTACT_NAME_03, CONTACT_NOTES_03);
		cm5.addNewContact(CONTACT_NAME_04, CONTACT_NOTES_04);
		cm5.addNewContact(CONTACT_NAME_05, CONTACT_NOTES_05);
	}

	@After
	public void tearDown() {
	}
	
	//---------------------------Test Constructor and initial methods ----------------------------
	
	@Test
	public void testContactManagerConstructor() {
		assertEquals(emptyCm.getContactId(), 0);
		assertEquals(emptyCm.getMeetingId(), 0);
		assertTrue(emptyCm.getContacts().isEmpty());
		assertTrue(emptyCm.getMeetings().isEmpty());
	}
	
	@Test
	public void testUpdateContactId() {
		emptyCm.updateContactId();
		emptyCm.updateContactId();
		assertEquals (emptyCm.getContactId(), 2);
	}
	
	@Test
	public void testUpdateMeetingId() {
		emptyCm.updateMeetingId();
		emptyCm.updateMeetingId();
		assertEquals (emptyCm.getMeetingId(), 2);
	}
	
	@Test
	public void testUpdateContactManagerFile() {
		fail("Not yet implemented");
	}
	
	//-------------------------Test addFutureMeeting-----------------------------------------------
	
	@Test (expected = NullPointerException.class)
	public void addFutureMeetingNullContactsThrowsException() {
		cm5.addFutureMeeting(null, FUTURE_DATE_02);
	}
		
	@Test (expected = NullPointerException.class)
	public void addFutureMeetingNullDateThrowsException() {
		emptyCm.addFutureMeeting(setOf2TestContacts, null);
		cm5.addFutureMeeting(setOf2TestContacts, null);
	}
	
	@Test (expected = NullPointerException.class)
	public void addFutureMeetingNullFutureMeetingThrowsException() {
		cm5.addFutureMeeting(null, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addFutureMeetingPastDateThrowsException() {
		cm5.addFutureMeeting(setOf2TestContacts, PAST_DATE_03);
	}
	
	//Need to implement set of invalid contacts - this test passes but invalid contacts not implemented
	@Test (expected = IllegalArgumentException.class)
	public void addFutureMeetingInvalidContactsThrowsException() {
		cm5.addFutureMeeting(setOfInvalidContacts, FUTURE_DATE_01);
		
	}
	
	@Test //This test won't pass until addNewContact() is implemented
	// Still not working - come back to it ... maybe need to implement getContacts(String name) first
	public void add3FutureMeetingsReturnsCorrectID() {
		assertTrue(cm5.addFutureMeeting(setOf2TestContacts,FUTURE_DATE_01) == 1);
		assertTrue(cm5.addFutureMeeting(setOf2TestContacts,FUTURE_DATE_02) == 2);
		assertTrue(cm5.addFutureMeeting(setOf2TestContacts,FUTURE_DATE_03) == 3);
	}
	
	//----------------------Test addNewContact------------------------------
	
	@Test
	public void addNewContactReturnsCorrectId() {
		assertTrue(emptyCm.addNewContact(TestData.CONTACT_NAME_07, TestData.CONTACT_NOTES_07) == 1);
		assertTrue(emptyCm.addNewContact(TestData.CONTACT_NAME_03, TestData.CONTACT_NOTES_03) == 2);
		assertTrue(emptyCm.addNewContact(TestData.CONTACT_NAME_05, TestData.CONTACT_NOTES_05) == 3);
		assertTrue(emptyCm.addNewContact(TestData.CONTACT_NAME_09, TestData.CONTACT_NOTES_09) == 4);
		assertTrue(emptyCm.addNewContact(TestData.CONTACT_NAME_10, TestData.CONTACT_NOTES_10) == 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addNewContactEmptyNameThrowsException() {
		emptyCm.addNewContact(TestData.EMPTY_CONTACT_NAME, TestData.CONTACT_NOTES_07);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addNewContactEmptyNotesThrowsException() {
		emptyCm.addNewContact(TestData.CONTACT_NAME_04, TestData.EMPTY_CONTACT_NOTES);
	}

	@Test (expected = NullPointerException.class) 
	public void addNewContactNullNameThrowsException() {
		emptyCm.addNewContact(null, TestData.CONTACT_NOTES_07);
	}
	
	@Test (expected = NullPointerException.class) 
	public void addNewContactNullNotesThrowsException() {
		emptyCm.addNewContact(TestData.CONTACT_NAME_04, null);
	}
	
	//------------------------Test getContacts -----------------------------------------------------
	
	@Test //getContacts returns a set with all the contacts containing the specified string
	public void getContactsReturnsContactSetContainingString() {
		
		Set<Contact> contactSet = new HashSet<>();
		contactSet = cm5.getContacts("Humpty");
		assertFalse(contactSet.isEmpty());
		assertTrue(contactSet.size() == 1);
		
		// assertTrue(contactSet.contains(CONTACT1)); this doesn't work, presumably because the object references are not the same?
		
		Set<Contact> contactSet2 = new HashSet<>();
		contactSet2 = cm5.getContacts("Little"); // Little Bo Peep, Little Miss Muffett
		assertFalse(contactSet2.isEmpty());
		System.out.println(contactSet2.size());
		assertTrue(contactSet2.size() == 2);
	}

	@Test 
	public void getContactsEmptySearchStringReturnsAllContacts() {
		Set<Contact> contactSet =  cm5.getContacts("");
		assertTrue(contactSet.contains(TestData.CONTACT1));
		assertTrue(contactSet.contains(TestData.CONTACT2));
		assertTrue(contactSet.contains(TestData.CONTACT3));
		assertTrue(contactSet.contains(TestData.CONTACT4));
		assertTrue(contactSet.contains(TestData.CONTACT5));
	}
	
	@Test (expected = NullPointerException.class)
	public void getContactsNullSearchStringThrowsException() {
		cm5.getContacts(TestData.NULL_SEARCH_STRING);
	}

	
}
