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
	private ArrayList<String> meetingNotes, meetingNotes2;
	private Meeting pastMeeting;
	private Contact ct1, ct2, ct3;
	private String pastMeetingNotes1, pastMeetingNotes2;
	
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
		
		pastMeetingNotes1 = "Three in a bed and the little one said";
		pastMeetingNotes2 = "Roll over";
		
		meetingNotes = new ArrayList<>();
		meetingNotes.add(pastMeetingNotes1);
		meetingNotes.add(pastMeetingNotes2);
		
		pastMeeting = new PastMeetingImpl (meetingID, meetingDate, meetingDelegates, meetingNotes);
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
	
	@Test
	public void testEqualsSameNotes() {
		Calendar date2 = new GregorianCalendar (2015,03,07);
		Set<Contact> meetingDelegates2 = new HashSet<>();
		meetingDelegates2.add(ct1);
		meetingDelegates2.add(ct2);
		meetingDelegates2.add(ct3);
		
		meetingNotes2 = new ArrayList<>();
		meetingNotes2.add(pastMeetingNotes1);
		meetingNotes2.add(pastMeetingNotes2);
		
		Meeting pastMeeting2 = new PastMeetingImpl(012, date2, meetingDelegates2, meetingNotes2);
		assertTrue(pastMeeting2.equals(pastMeeting));
	}

	@Test
	public void testEqualsDifferentNotes() {
		Calendar date2 = new GregorianCalendar (2015,03,07);
		Set<Contact> meetingDelegates2 = new HashSet<>();
		meetingDelegates2.add(ct1);
		meetingDelegates2.add(ct2);
		meetingDelegates2.add(ct3);
		
		meetingNotes2 = new ArrayList<>();
		meetingNotes2.add("This is a different note");
		meetingNotes2.add("This note a very long and boring repetitive note");
		
		Meeting pastMeeting2 = new PastMeetingImpl(012, date2, meetingDelegates2, meetingNotes2);
		assertFalse(pastMeeting2.equals(pastMeeting));
	}
	
	@Test (expected = NullPointerException.class)
	public void nullDateThrowsException() {
		Meeting pastMeeting = new PastMeetingImpl(meetingID, null, meetingDelegates, meetingNotes);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullContactsThrowsException() {
		Meeting pastMeeting = new PastMeetingImpl(meetingID, meetingDate, null, meetingNotes);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullNotesThrowsException() {
		Meeting pastMeeting = new PastMeetingImpl(meetingID, meetingDate, meetingDelegates, null);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullDateAndContactsThrowsException() {
		Meeting pastMeeting = new PastMeetingImpl(meetingID, null, meetingDelegates, null);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullContactsAndNotesThrowsException() {
		Meeting pastMeeting = new PastMeetingImpl(meetingID, meetingDate, null, null);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullDateAndContactsAndNotesThrowsException() {
		Meeting pastMeeting = new PastMeetingImpl(meetingID, null, null, null);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void negativeIdThrowsException() {
		Meeting pastMeeting = new PastMeetingImpl(-1, meetingDate, meetingDelegates, meetingNotes);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void zeroIdThrowsException() {
		Meeting pastMeeting = new PastMeetingImpl(0, meetingDate, meetingDelegates, meetingNotes);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void emptyContactsThrowsException() {
		Set<Contact> emptyContacts = new HashSet<>();
		Meeting pastMeeting = new PastMeetingImpl(meetingID, meetingDate, emptyContacts, meetingNotes);
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
