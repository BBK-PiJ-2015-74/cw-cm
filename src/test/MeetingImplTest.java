package test;

import spec.Contact;
import spec.Meeting;
import impl.ContactImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

public class MeetingImplTest {

	private int meetingID;
	private Calendar meetingDate;
	private Set<Contact> meetingDelegates;
	private Meeting meeting;
	
	@Before
	public void setUpMockMeeting() {
		
		Meeting mockMeeting = new MeetingMock(meetingID, meetingDate, meetingDelegates);
	}

	@Test
	public void test() throws Exception {
		fail("Not yet implemented");
	}

	// Create a test meeting with some basic details using a mock
	
	private class MeetingMock implements Meeting {
		
		private int mockMeetingID;
		private Calendar mockMeetingDate;
		private Set<Contact> mockMeetingDelegates;
		
		private MeetingMock (int id, Calendar date, Set<Contact> contacts) {
			if (id <=0 ) {
				throw new IllegalArgumentException ("meeting ID must be positive and non-zero");
			}
			
			Objects.requireNonNull(date);
			Objects.requireNonNull(contacts);
			
			this.mockMeetingID = id;
			this.mockMeetingDate = date;
			this.mockMeetingDelegates = contacts;
		
			mockMeetingID = 001;
			mockMeetingDate = new GregorianCalendar(1974, 06, 06);
			
			Contact ct1 = new ContactImpl(279, "Humpty Dumpty", "He's a good egg");
			Contact ct2 = new ContactImpl(823, "Little Miss Muppet", "The spider scared her"); 
			Contact ct3 = new ContactImpl(526, "The Grand Old Duke of York", "He had ten thousand men");		
			
			mockMeetingDelegates = new HashSet<>();
			mockMeetingDelegates.add(ct1);
			mockMeetingDelegates.add(ct2);
			mockMeetingDelegates.add(ct3);
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
	} // end of MeetingMock class
	
	
	@After
	public void tearDown() {
		Meeting mockMeeting = null;
	}
	
}
