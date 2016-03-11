package impl;

import java.util.Calendar;
import java.util.Objects;
import java.util.Set;

import spec.Contact;
import spec.Meeting;
import java.io.Serializable;

/**
 * Implementation of interface Meeting
 * @author BBK-PiJ-2015-74 lburge01
 * 
 * The class implementing the interface must be abstract
 * It must have only one constructor with three arguments: int ID, a date, and a set of contacts
 *  that must be non-empty - otherwise an IllegalArgumentException must be thrown
 *  An IllegalArgumentException must also be thrown in the case the ID provided was negative or non-zero
 *  If any of the references or pointers passed to the parameters is null, a NullPointerException must be thrown
 *  Meeting is an abstract class which means we will not create instances of objects of this class
 *  Instead, we'll use new PastMeetingImpl and new FutureMeetingImpl to create instances of meeting objects
 */

public abstract class MeetingImpl implements Meeting, Serializable {

	/**
	 * Constructs a Meeting
	 * @param meetingID the ID of the meeting (this class does not ensure uniqueness)
	 * @param meetingDate the date of the meeting using the Gregorian Calendar (day, month, year)
	 * @param meetingDelegates the Contacts for the people who attended the meeting - at least one
	 * 
	 * @throws NullPointerException if the date or contacts are null
	 * @throws IllegalArgumentException if the meetingID is negative or zero
	 * @throws IllegalArgumentException if the contacts are empty
	 */
	private static final long serialVersionUID = 1L;
	private int meetingID;
	private Calendar meetingDate;
	private Set<Contact> meetingDelegates;
		
	public MeetingImpl (int id, Calendar date, Set<Contact> contacts) {
		if (id <=0 ) {
			throw new IllegalArgumentException ("Meeting ID must be positive and non-zero");
		}
			
		if (contacts.isEmpty()){
			throw new IllegalArgumentException ("You must enter at least one contact for the meeting");
		}
			
		Objects.requireNonNull(date);
		Objects.requireNonNull(contacts);
			
		this.meetingID = id;
		this.meetingDate = date;
		this.meetingDelegates = contacts;
		}
		
	/**
	 * @see Meeting#getId()
	 */
		@Override
		public int getId() {
			return meetingID;
		}
	
	/**
	 * @see Meeting#getDate()
	 */
		@Override
		public Calendar getDate() {
			return meetingDate;
		}
		
	/**
	 * @see Meeting#getContacts()
	 */
		@Override
		public Set<Contact> getContacts() {
			return meetingDelegates;
		}
	}
