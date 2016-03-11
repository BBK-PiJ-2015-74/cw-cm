package impl;

import java.util.Calendar;
import java.util.Set;

import spec.Contact;
import spec.PastMeeting;

/**
 * 
 * @author BBK-PiJ-2015-74 lburge01
 * Implementation of interface PastMeeting
 */

public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
	
	/**
	 * The class implementing this interface has one constructor with four parameters:
	 * an ID (int), a date, a set of contacts that must be non-empty, and a String containing the notes for the meeting
	 * 
	 * @param int meetingID the id of the meeting
	 * @param Calendar meetingDate the date of the meeting
	 * @param Set<Contact> meetingDelegates the set of contacts that attended the meeting
	 * @param ArrayList<String> meetingNotes the notes from the meeting
	 * 
	 * @throws NullPointerException if meetingDate, meetingDelegates, meetingNotes is null
	 * @throws IllegalArgumentException if id is negative or zero or if contacts is empty
	 */

	public PastMeetingImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getNotes() {
		// TODO Auto-generated method stub
		return null;
	}

}
