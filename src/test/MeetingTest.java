package test;

import spec.Contact;
import spec.Meeting;
import impl.ContactImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author BBK-PiJ-2015-74 lburge01
 * Test the MeetingImpl implementation of the interface Meeting
 * Meetings have unique IDs, a scheduled date an a set of contacts that must be non-empty
 * Try setting up a mock meeting to test the implementation
 */

public class MeetingTest {

	private int meetingID;
	private Calendar meetingDate;
	private Set<Contact> meetingDelegates;
	private Meeting mockMeeting;
	
	/**
	 * Create a mock meeting to test 
	 * class {@code MeetingMock} sets up a basic meeting with an id, date and contacts
	 */
	
	Contact ct1 = new ContactImpl(279, "Humpty Dumpty", "He's a good egg");
	Contact ct2 = new ContactImpl(823, "Little Miss Muppet", "The spider scared her"); 
	Contact ct3 = new ContactImpl(526, "The Grand Old Duke of York", "He had ten thousand men");
	
	@Before
	public void setUp() {
		meetingID = 001;
		meetingDate = new GregorianCalendar(1974,06,06);		
		meetingDelegates = new HashSet<>();
		meetingDelegates.add(ct1);
		meetingDelegates.add(ct2);
		meetingDelegates.add(ct3);
		
		mockMeeting = new MeetingMock(meetingID, meetingDate, meetingDelegates);
	}
	
	@Test
	public void testMeetingID() {
		assertEquals(001, mockMeeting.getId());
	}
	
	@Test
	public void testMeetingDate() {
		assertEquals (new GregorianCalendar(1974,06,06), mockMeeting.getDate());
	}
	
	@Test
	public void testMeetingDelegates() {
		assertEquals (meetingDelegates, mockMeeting.getContacts());
	}

	@Test
	public void testContactsEqualSame() {	
		Set<Contact> testDelegates = new HashSet<>();
		testDelegates.add(ct1);
		testDelegates.add(ct2);
		testDelegates.add(ct3);
		assertTrue(testDelegates.equals(mockMeeting.getContacts()));
	}
	
	@Test
	public void testIDEqualsSame() {
		int testMeetingID = 001;
		assertTrue(testMeetingID == mockMeeting.getId());
	}
	
	@Test 
	public void testMeetingDateEqualsSame() {
		Calendar testDate = new GregorianCalendar(1974,06,06);
		assertTrue(testDate.equals(mockMeeting.getDate()));
	}
	
	@Test (expected = NullPointerException.class)
	public void nullDateThrowsException() {
		mockMeeting = new MeetingMock(meetingID, null, meetingDelegates);
	}
	
	@Test (expected = NullPointerException.class)
	public void nullContactsThrowsException() {
		mockMeeting = new MeetingMock(meetingID, meetingDate, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void negativeIDThrowsException() {
		mockMeeting = new MeetingMock(-1, meetingDate, meetingDelegates);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void zeroIDThrowsException() {
		mockMeeting = new MeetingMock(0, meetingDate, meetingDelegates);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void emptyContactsThrowsException() {
		Set<Contact> emptyContacts = new HashSet<>();
		mockMeeting = new MeetingMock(meetingID, meetingDate, emptyContacts);
	}
	
	/**
	 * a basic mock class created to test the functionality of interface Meeting
	 *
	 */	
	private class MeetingMock implements Meeting {
		
		private int mockMeetingID;
		private Calendar mockMeetingDate;
		private Set<Contact> mockMeetingDelegates;
		
		private MeetingMock (int id, Calendar date, Set<Contact> contacts) {
			if (id <=0 ) {
				throw new IllegalArgumentException ("meeting ID must be positive and non-zero");
			}
			
			if (contacts.isEmpty()){
				throw new IllegalArgumentException ("Contacts cannot be empty");
			}
			
			Objects.requireNonNull(date);
			Objects.requireNonNull(contacts);
			
			this.mockMeetingID = id;
			this.mockMeetingDate = date;
			this.mockMeetingDelegates = contacts;
		}
		
		@Override
		public int getId() {
			return mockMeetingID;
		}
		
		@Override
		public Calendar getDate() {
			return mockMeetingDate;
		}
			
		@Override
		public Set<Contact> getContacts() {
			return mockMeetingDelegates;
		}
	}
	
	@After
	public void tearDown() {
		meetingID = 0;
		meetingDate = null;		
		meetingDelegates = null;
		ct1 = null;
		ct2 = null;
		ct3 = null;
	}
	
}
