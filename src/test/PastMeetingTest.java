package test;

import impl.ContactImpl;
import impl.PastMeetingImpl;
import spec.Contact;
import spec.Meeting;
import spec.PastMeeting;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;

/**
 * @author BBK-PiJ-2015-74
 * Tests for implementation of PastMeeting 
 */

public class PastMeetingTest {
	
	private int meetingID;
	private Calendar meetingDate;
	private Set<Contact> meetingDelegates;
	private PastMeeting pastMeeting;
	private Contact ct1, ct2, ct3;
	private String meetingNotes, meetingNotes1, meetingNotes2;
	
	/**
	 * Set up a past meeting to test
	 */
	@Before
	public void setUp() {
		
		meetingID = 012;
		meetingDate = new GregorianCalendar(2015,03,07);	
		
		ct1 = new ContactImpl(279, "Humpty Dumpty", "He's a good egg");
		ct2 = new ContactImpl(823, "Little Miss Muppet", "The spider scared her"); 
		ct3 = new ContactImpl(526, "The Grand Old Duke of York", "He had ten thousand men");
		
		meetingDelegates = new HashSet<>();
		meetingDelegates.add(ct1);
		meetingDelegates.add(ct2);
		meetingDelegates.add(ct3);
		
		meetingNotes1 = "Three in a bed and the little one said";
		meetingNotes2 = "Roll over";
		meetingNotes = meetingNotes1.concat(" ").concat(meetingNotes2);
		
		pastMeeting = new PastMeetingImpl(meetingID, meetingDate, meetingDelegates, meetingNotes);
	}

	@Test
	public void createPastMeeting() {
		assertNotNull(pastMeeting);
	}
	
	@Test 
	public void testPastMeetingID() {
		assertEquals(meetingID, pastMeeting.getId());
	}
	
	@Test
	public void testPastMeetingDate() {
		assertEquals(meetingDate, pastMeeting.getDate());
	}
	
	@Test
	public void testPastMeetingDelegates() {
		assertEquals(meetingDelegates, pastMeeting.getContacts());
	}
	
	@Test
	public void testPastMeetingNotes() {
		assertEquals(meetingNotes, pastMeeting.getNotes());
	}
	
	// This test fails and I don't know why. All the parameters of the object pass the test individually
	@Test
	public void testEqualsSameNotes() {
		int meetingId2 = 012;
		Calendar date2 = new GregorianCalendar (2015,03,07);
		Set<Contact> meetingDelegates2 = new HashSet<>();
		meetingDelegates2.add(ct1);
		meetingDelegates2.add(ct2);
		meetingDelegates2.add(ct3);
		String meetingNotes = "Three in a bed and the little one said Roll over";
		
		PastMeeting pastMeeting2 = new PastMeetingImpl(meetingId2, date2, meetingDelegates2, meetingNotes);
		//assertTrue(pastMeeting.getId()==(meetingId2));
		//assertTrue(pastMeeting.getDate().equals(date2));
		//assertTrue(pastMeeting.getContacts().equals(meetingDelegates2));
		//assertTrue(pastMeeting.getNotes().equals(meetingNotes));
		assertTrue(pastMeeting.equals(pastMeeting2));
	}

	@Test
	public void testEqualsDifferentNotes() {
		Calendar date2 = new GregorianCalendar (2015,03,07);
		Set<Contact> meetingDelegates2 = new HashSet<>();
		meetingDelegates2.add(ct1);
		meetingDelegates2.add(ct2);
		meetingDelegates2.add(ct3);
	
		meetingNotes2 = "This note a very long and boring repetitive note";
		
		PastMeeting pastMeeting2 = new PastMeetingImpl(012, date2, meetingDelegates2, meetingNotes2);
		assertFalse(pastMeeting2.equals(pastMeeting));
	}
	
	@Test (expected = NullPointerException.class)
	public void nullDateThrowsException() {
		PastMeeting pastMeeting = new PastMeetingImpl(meetingID, null, meetingDelegates, meetingNotes);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullContactsThrowsException() {
		PastMeeting pastMeeting = new PastMeetingImpl(meetingID, meetingDate, null, meetingNotes);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullNotesThrowsException() {
		PastMeeting pastMeeting = new PastMeetingImpl(meetingID, meetingDate, meetingDelegates, null);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullDateAndContactsThrowsException() {
		PastMeeting pastMeeting = new PastMeetingImpl(meetingID, null, meetingDelegates, null);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullContactsAndNotesThrowsException() {
		PastMeeting pastMeeting = new PastMeetingImpl(meetingID, meetingDate, null, null);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullDateAndContactsAndNotesThrowsException() {
		PastMeeting pastMeeting = new PastMeetingImpl(meetingID, null, null, null);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void negativeIdThrowsException() {
		PastMeeting pastMeeting = new PastMeetingImpl(-1, meetingDate, meetingDelegates, meetingNotes);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void zeroIdThrowsException() {
		PastMeeting pastMeeting = new PastMeetingImpl(0, meetingDate, meetingDelegates, meetingNotes);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void emptyContactsThrowsException() {
		Set<Contact> emptyContacts = new HashSet<>();
		PastMeeting pastMeeting = new PastMeetingImpl(meetingID, meetingDate, emptyContacts, meetingNotes);
	}
	
	
	@After
	public void tearDown() {
		meetingID = 0;
		meetingDate = null;	
		ct1 = null;
		ct2 = null;
		ct3 = null;
		meetingDelegates = null;
		meetingNotes = null;
	}
	
}
