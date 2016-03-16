package test;

import impl.ContactManagerImpl;
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
	private Set<Contact> cmContacts;
	private List<Meeting> cmMeetings;
	
	static final String FILENAME = "contacts.txt";
	
	final File cmFile = new File(FILENAME);
	
	/**
	 * Set up a clean ContactManager environment for each test
	 */
	@Before
	public void setUp() {
		
		//Set time to current system time
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		cmDate = Calendar.getInstance();
		System.out.println(dateFormat.format(cmDate.getTime()));
		
		//Delete all data files if they exist
	     try {
	    	 Path p = FileSystems.getDefault().getPath(FILENAME);
	          if (Files.exists(p)) { 
	        	  Files.delete(p);
	          }
	     } catch (IOException ioEx) {
	    	 ioEx.printStackTrace();
	     }
	     
	    //Create a fresh ContactManagerImpl
	    ContactManager cm = new ContactManagerImpl();
		
		//Create a set of contacts
	    Set<Contact> contactSet = new HashSet<>();
	    //contactSet.add(TestData.contact1);
	    //contactSet.add(TestData.contact2);
	    //contactSet.add(TestData.contact3);
		
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testContactManagerConstructor() {
		ContactManager cm = new ContactManagerImpl();
		assertEquals(cm.getContactId(), -1);
		assertEquals(cm.getMeetingId(), -1);
		assertTrue(cm.getContacts().isEmpty());
		assertTrue(cm.getMeetings().isEmpty());
	}
	
	/**
	 * Test counter for contactId
	 */
	@Test
	public void testUpdateContactId() {
		ContactManager cm = new ContactManagerImpl();
		cm.updateContactId();
		assertEquals (cm.getContactId(), 1);
	}
	
	/**
	 * Test counter for meetingId
	 */
	@Test
	public void testUpdateMeetingId() {
		ContactManager cm = new ContactManagerImpl();
		cm.updateMeetingId();
		assertEquals (cm.getMeetingId(), 1);
	}
	
	
	@Test
	public void testUpdateContactManagerFile() {
		fail("Not yet implemented");
	}

}
