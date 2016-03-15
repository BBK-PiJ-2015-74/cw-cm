package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import impl.ContactImpl;
import impl.FutureMeetingImpl;
import spec.Contact;
import spec.FutureMeeting;
import spec.Meeting;

/**
 * @author BBK-PiJ-2015-74
 * Tests for implementation of FutureMeeting 
 */

public class FutureMeetingTest {
		
	private int meetingID;
	private Calendar meetingDate;
	private Set<Contact> meetingDelegates;
	private Contact ct1, ct2, ct3;
		
	/**
	 * Set up a future meeting to test
	 */
	@Before
	public void setUp() {
			
		meetingID = 17;
		meetingDate = new GregorianCalendar(2016,9,29);	
			
		ct1 = new ContactImpl(279, "Humpty Dumpty", "He's a good egg");
		ct2 = new ContactImpl(823, "Little Miss Muppet", "The spider scared her"); 
		ct3 = new ContactImpl(526, "The Grand Old Duke of York", "He had ten thousand men");
			
		meetingDelegates = new HashSet<>();
		meetingDelegates.add(ct1);
		meetingDelegates.add(ct2);
		meetingDelegates.add(ct3);
		}

		@Test
		public void createFutureMeeting() {
			FutureMeeting futureMeeting = new FutureMeetingImpl(meetingID, meetingDate, meetingDelegates);
			assertNotNull(futureMeeting);
		}
		
		@Test
		public void createFutureMeetingofTypeMeeting() {
			Meeting futureMeeting = new FutureMeetingImpl(meetingID, meetingDate, meetingDelegates);
			assertNotNull(futureMeeting);
		}
		
		@Test 
		public void testFutureMeetingID() {
			FutureMeeting futureMeeting = new FutureMeetingImpl(meetingID, meetingDate, meetingDelegates);
			assertEquals(meetingID, futureMeeting.getId());
		}
		
		@Test
		public void testFutureMeetingDate() {
			FutureMeeting futureMeeting = new FutureMeetingImpl(meetingID, meetingDate, meetingDelegates);
			assertEquals(meetingDate, futureMeeting.getDate());
		}
		
		@Test
		public void testFutureMeetingDelegates() {
			FutureMeeting futureMeeting = new FutureMeetingImpl(meetingID, meetingDate, meetingDelegates);
			assertEquals(meetingDelegates, futureMeeting.getContacts());
		}
		
		@Test
		public void testEqualsFutureMeeting() {
			int meetingId2 = 17;
			Calendar meetingDate2 = new GregorianCalendar(2016,9,29);	
			Set<Contact> meetingDelegates2 = new HashSet<>();
			meetingDelegates2.add(ct1);
			meetingDelegates2.add(ct2);
			meetingDelegates2.add(ct3);
			
			FutureMeeting futureMeeting2 = new FutureMeetingImpl(meetingId2, meetingDate2, meetingDelegates2);
			assertTrue(futureMeeting2.getId()==(meetingId2));
			assertTrue(futureMeeting2.getDate().equals(meetingDate2));
			assertTrue(futureMeeting2.getContacts().equals(meetingDelegates2));
		}
		
		
		@Test (expected = NullPointerException.class)
		public void nullDateThrowsException() {
			new FutureMeetingImpl(meetingID, null, meetingDelegates);
		}
		
		@Test (expected = NullPointerException.class)
		public void nullContactsThrowsException() {
			new FutureMeetingImpl(meetingID, meetingDate, null);
		}
		
		@Test (expected = NullPointerException.class)
		public void nullDateAndContactsThrowsException() {
			new FutureMeetingImpl(meetingID, null, meetingDelegates);
		}
		
		@Test (expected = IllegalArgumentException.class) 
		public void negativeIdThrowsException() {
			new FutureMeetingImpl(-1, meetingDate, meetingDelegates);
		}
		
		@Test (expected = IllegalArgumentException.class) 
		public void zeroIdThrowsException() {
			new FutureMeetingImpl(0, meetingDate, meetingDelegates);
		}
		
		@Test (expected = IllegalArgumentException.class) 
		public void emptyContactsThrowsException() {
			Set<Contact> emptyContacts = new HashSet<>();
			new FutureMeetingImpl(meetingID, meetingDate, emptyContacts);
		}
		
		
		@After
		public void tearDown() {
			meetingID = 0;
			meetingDate = null;	
			ct1 = null;
			ct2 = null;
			ct3 = null;
			meetingDelegates = null;
		}
		
	}