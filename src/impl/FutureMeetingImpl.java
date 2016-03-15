package impl;

import java.util.Calendar;
import java.util.Set;
import java.io.Serializable;

import spec.Contact;
import spec.FutureMeeting;
import impl.MeetingImpl;

/**
 * @author BBK-PiJ-2015-74
 * Implementation of interface FutureMeeting
 * This is only used for type checking and/or downcasting
 * @see spec.FutureMeeting
 * @see spec.Meeting
 */

public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting, Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see impl.MeetingImpl
	 * @param id the id of the meeting
	 * @param date, the date the meeting is to be held
	 * @param contacts, the people attending the meeting
	 */

	public FutureMeetingImpl(int id, Calendar date, Set<Contact> contacts) {
		super (id, date, contacts);
	}
}
