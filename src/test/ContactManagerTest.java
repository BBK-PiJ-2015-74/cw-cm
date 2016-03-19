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
	private Set<Contact> twoContactSet, fiveContactSet, tenContactSet, invalidContactSet;
	
	//--------------------------------Set Up -------------------------------------------------------------------------
	
	/**
	 * Set up a clean ContactManager environment for each test, test contact sets and delete data files if they exist
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
	     
	     emptyCM = buildEmptyCM(); 
	     testCM = buildTestCM();   
	     twoContactSet = buildTwoContactSet();
	     fiveContactSet = buildFiveContactSet();
	     tenContactSet = buildTenContactSet();
	     
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
	
	//-------------------------Test addFutureMeeting-------------------------------------------------------------------
	
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
	
	@Test 
	public void add3FutureMeetingsReturnsCorrectID() {
		assertTrue(testCM.addFutureMeeting(fiveContactSet,FUTURE_DATE_01) == 1);
		assertTrue(testCM.addFutureMeeting(fiveContactSet,FUTURE_DATE_02) == 2);
		assertTrue(testCM.addFutureMeeting(fiveContactSet,FUTURE_DATE_03) == 3);
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
	// assertTrue(contactSet.contains(CONTACT1)); this doesn't work, presumably because the object references are not the same?
	
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
		System.out.println(contactSet.size());
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
		Set<Contact> contactSet =  testCM.getContacts("");
		assertTrue(contactSet.size() == 5);
		testCM.getContacts(NULL_SEARCH_STRING);
	}

	//------------------------------Test getContacts(int... ids)-------------------------------------
	
	@Test
	public void getContactsReturnsContactSetFromId() {
		
		Set<Contact> contactSet = new HashSet<>();
		contactSet = testCM.getContacts(CONTACT_ID_01,CONTACT_ID_02,CONTACT_ID_03,CONTACT_ID_04,CONTACT_ID_05);
		assertTrue(contactSet.size() == 5);
	}
	
}
