package test;

import impl.ContactManagerImpl;
import impl.FutureMeetingImpl;
import impl.ContactImpl;
import spec.*;
import test.TestData;
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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
	private ContactManager cm, cm5;
	
	
	static final String FILENAME = "contacts.txt";
	
	final File cmFile = new File(FILENAME);
	
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
	    setOf2TestContacts = new HashSet<>();
		setOf2TestContacts.add(TestData.contact1);
		setOf2TestContacts.add(TestData.contact4);
		    
		//Create a test set of 2 invalid contacts
		setOfInvalidContacts = new HashSet<>();
		
		    
		//Create a new ContactManager implementation
		cm = new ContactManagerImpl();
		
		//Create a ContactManager implementation with 5 contacts
		//NB. Need to implement addNewContact(name, notes) before tests using this method will pass
		cm5 = new ContactManagerImpl();
		cm5.addNewContact(TestData.CONTACT_NAME_01, TestData.CONTACT_NOTES_01);
		cm5.addNewContact(TestData.CONTACT_NAME_02, TestData.CONTACT_NOTES_02);
		cm5.addNewContact(TestData.CONTACT_NAME_03, TestData.CONTACT_NOTES_03);
		cm5.addNewContact(TestData.CONTACT_NAME_04, TestData.CONTACT_NOTES_04);
		cm5.addNewContact(TestData.CONTACT_NAME_05, TestData.CONTACT_NOTES_05);

	}

	@After
	public void tearDown() {
	}
	
	//---------------------------Test Constructor and initial methods ----------------------------
	
	@Test
	public void testContactManagerConstructor() {
		assertEquals(cm.getContactId(), -1);
		assertEquals(cm.getMeetingId(), -1);
		assertTrue(cm.getContacts().isEmpty());
		assertTrue(cm.getMeetings().isEmpty());
	}
	
	@Test
	public void testUpdateContactId() {
		cm.updateContactId();
		assertEquals (cm.getContactId(), 1);
	}
	
	@Test
	public void testUpdateMeetingId() {
		cm.updateMeetingId();
		assertEquals (cm.getMeetingId(), 1);
	}
	
	@Test
	public void testUpdateContactManagerFile() {
		fail("Not yet implemented");
	}
	
	//-------------------------Test addFutureMeeting-----------------------------------------------
	
	@Test (expected = NullPointerException.class)
	public void addFutureMeetingNullContactsThrowsException() {
		cm5.addFutureMeeting(null, TestData.FUTURE_DATE_02);
	}
		
	@Test (expected = NullPointerException.class)
	public void addFutureMeetingNullDateThrowsException() {
		cm.addFutureMeeting(setOf2TestContacts, null);
		cm5.addFutureMeeting(setOf2TestContacts, null);
	}
	
	@Test (expected = NullPointerException.class)
	public void addFutureMeetingNullFutureMeetingThrowsException() {
		cm5.addFutureMeeting(null, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addFutureMeetingPastDateThrowsException() {
		cm5.addFutureMeeting(setOf2TestContacts, TestData.PAST_DATE_03);
	}
	
	//Need to implement set of invalid contacts - this test passes but invalid contacts not implemented
	@Test (expected = IllegalArgumentException.class)
	public void addFutureMeetingInvalidContactsThrowsException() {
		cm5.addFutureMeeting(setOfInvalidContacts, TestData.FUTURE_DATE_01);
		
	}
	
	@Test //This test won't pass until addNewContact() is implemented
	public void add3FutureMeetingsReturnsCorrectID() {
		assertTrue(cm5.addFutureMeeting(setOf2TestContacts,TestData.FUTURE_DATE_01) == 1);
		assertTrue(cm5.addFutureMeeting(setOf2TestContacts,TestData.FUTURE_DATE_02) == 2);
		assertTrue(cm5.addFutureMeeting(setOf2TestContacts,TestData.FUTURE_DATE_03) == 3);
	}
	
	//----------------------Test addNewContact------------------------------
	
	@Test
	public void addNewContactReturnsCorrectId() {
		assertTrue(cm.addNewContact(TestData.CONTACT_NAME_07, TestData.CONTACT_NOTES_07) == 1);
		assertTrue(cm.addNewContact(TestData.CONTACT_NAME_03, TestData.CONTACT_NOTES_03) == 2);
		assertTrue(cm.addNewContact(TestData.CONTACT_NAME_05, TestData.CONTACT_NOTES_05) == 3);
		assertTrue(cm.addNewContact(TestData.CONTACT_NAME_09, TestData.CONTACT_NOTES_09) == 4);
		assertTrue(cm.addNewContact(TestData.CONTACT_NAME_10, TestData.CONTACT_NOTES_10) == 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addNewContactEmptyNameThrowsException() {
		cm.addNewContact(TestData.EMPTY_CONTACT_NAME, TestData.CONTACT_NOTES_07);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addNewContactEmptyNotesThrowsException() {
		cm.addNewContact(TestData.CONTACT_NAME_04, TestData.EMPTY_CONTACT_NOTES);
	}

	@Test (expected = NullPointerException.class) 
	public void addNewContactNullNameThrowsException() {
		cm.addNewContact(null, TestData.CONTACT_NOTES_07);
	}
	
	@Test (expected = NullPointerException.class) 
	public void addNewContactNullNotesThrowsException() {
		cm.addNewContact(TestData.CONTACT_NAME_04, null);
	}
	
	
}
