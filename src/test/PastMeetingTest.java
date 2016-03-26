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
	private Set<Contact> meetingContacts;
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
		
		meetingContacts = new HashSet<>();
		meetingContacts.add(ct1);
		meetingContacts.add(ct2);
		meetingContacts.add(ct3);
		
		meetingNotes1 = "Three in a bed and the little one said";
		meetingNotes2 = "Roll over";
		meetingNotes = meetingNotes1.concat(" ").concat(meetingNotes2);
		
		pastMeeting = new PastMeetingImpl(meetingID, meetingDate, meetingContacts, meetingNotes);
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
		assertEquals(meetingContacts, pastMeeting.getContacts());
	}
	
	@Test
	public void testPastMeetingNotes() {
		assertEquals(meetingNotes, pastMeeting.getNotes());
	}
	
	@Test
	public void testEqualsSame() {
		int meetingId2 = 012;
		Calendar date2 = new GregorianCalendar (2015,03,07);
		String meetingNotes = "Three in a bed and the little one said Roll over";
		
		PastMeeting pastMeeting2 = new PastMeetingImpl(meetingId2, date2, meetingContacts, meetingNotes);
		assertTrue(pastMeeting.getId()==(meetingId2));
		assertTrue(pastMeeting.getDate().equals(date2));
		assertTrue(pastMeeting.getContacts().equals(meetingContacts));
		assertTrue(pastMeeting.getNotes().equals(meetingNotes));
		//assertTrue(pastMeeting.equals(pastMeeting2));
	}

	@Test
	public void testEqualsDifferentNotes() {
		int meetingId2 = 012;
		Calendar date2 = new GregorianCalendar (2015,03,07);
		meetingNotes2 = "This note a very long and boring repetitive note";
		
		PastMeeting pastMeeting2 = new PastMeetingImpl(meetingId2, date2, meetingContacts, meetingNotes2);
		assertFalse(pastMeeting2.equals(pastMeeting));
	}
	
	@Test (expected = NullPointerException.class)
	public void nullDateThrowsException() {
		new PastMeetingImpl(meetingID, null, meetingContacts, meetingNotes);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullContactsThrowsException() {
		new PastMeetingImpl(meetingID, meetingDate, null, meetingNotes);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullNotesThrowsException() {
		new PastMeetingImpl(meetingID, meetingDate, meetingContacts, null);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullDateAndContactsThrowsException() {
		new PastMeetingImpl(meetingID, null, meetingContacts, null);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullContactsAndNotesThrowsException() {
		new PastMeetingImpl(meetingID, meetingDate, null, null);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullDateAndContactsAndNotesThrowsException() {
		new PastMeetingImpl(meetingID, null, null, null);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void negativeIdThrowsException() {
		new PastMeetingImpl(-1, meetingDate, meetingContacts, meetingNotes);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void zeroIdThrowsException() {
		new PastMeetingImpl(0, meetingDate, meetingContacts, meetingNotes);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void emptyContactsThrowsException() {
		Set<Contact> emptyContacts = new HashSet<>();
		new PastMeetingImpl(meetingID, meetingDate, emptyContacts, meetingNotes);
	}
	
	
	@After
	public void tearDown() {
		meetingID = 0;
		meetingDate = null;	
		ct1 = null;
		ct2 = null;
		ct3 = null;
		meetingContacts = null;
		meetingNotes = null;
	}
	
}
