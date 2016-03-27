package impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.*;

import spec.*;
import impl.*;

public class ContactManagerImpl implements ContactManager {
	
	private int contactId;
	private int meetingId;
	private Calendar cmDate; // the date of the Contact Manager
	private Set<Contact> cmContacts; // the contacts added and that already exist
	private List<Meeting> cmMeetings; //the meetings added and that already exist
	private static final String FILENAME = "contacts.txt";

	public ContactManagerImpl() {	
		readContactManagerFile();
	}
	
	/**
	 * @see spec.ContactManager#getContactId()
	 */
	public int getContactId() {
		return contactId;
	}
	
	/**
	 * @see spec.ContactManager#getMeetingId()
	 */
	public int getMeetingId() {
		return meetingId;
	}
	
	/** 
	 * @see spec.ContactManager#getContacts()
	 */
	public Set<Contact> getContacts() {
		return cmContacts;
	}
	
	/** 
	 * @see spec.ContactManager#getMeetings()
	 */
	public List<Meeting> getMeetings() {
		return cmMeetings;
	}
	
	/**
	 * @see spec.ContactManager#updateContactId()
	 */
	public int updateContactId() {
		contactId++;
		return contactId;
	}
	
	/**
	 * Method adds one to the id of the meeting.
	 */
	public int updateMeetingId() {
		meetingId++;
		return meetingId;
	}
	
	/**
	 * @see spec.ContactManager#addFutureMeeting
	 * @throws IllegalArgumentException if the contact is not in the ContactManager
	 * @throws IllegalArgumentException if the meeting is set for a time in the past
	 * @throws NullPointerException if the contacts or date are null
	 */
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		
		Objects.requireNonNull(contacts);
		Objects.requireNonNull(date);
		
		if (date.before(Calendar.getInstance())) {
			throw new IllegalArgumentException("A future meeting cannot be held in the past. Please check date"); 
		}
		
		if (!cmContacts.containsAll(contacts)) {
			throw new IllegalArgumentException("Contact not in Contact Manager. Please add contact first");
		}
		
		meetingId = updateMeetingId();
		FutureMeeting newFutureMeeting = new FutureMeetingImpl(meetingId, date, contacts);
		Objects.requireNonNull(newFutureMeeting);
		cmMeetings.add(newFutureMeeting);
		return meetingId;
	}

	/**
	 * @see spec.ContactManager#getPastMeeting(int id)
	 * @throws IllegalStateException if there is a meeting with that Id happening in the future
	 */
	//all meetings have a unique id whether FutureMeetings or PastMeetings
	//The type of meeting is indicated by the date. i.e. meetings happening in a date > cmDate are FutureMeetings
	// meetings happening on a date < cmDate are PastMeetings
	// somehow we have to ensure that FutureMeetings become PastMeetings when the date changes ...
	@Override
	public PastMeeting getPastMeeting(int id) { 
	
		List<Meeting> meetingstream = cmMeetings.stream()
				.filter(m -> m.getId() == id)
				.collect(Collectors.toList());
		if(!(meetingstream.isEmpty())) {
			Meeting meeting = meetingstream.get(0);
				if (!(meeting instanceof PastMeeting)) {
					throw new IllegalStateException("This meeting is not a past meeting");
				}
			return (PastMeeting) meeting;
		} else { 
			return null;
		}
	}
	
	/**
	 * @see spec.ContactManager#getFutureMeeting(int id)
	 * @throws IllegalArgumentException if there is a meeting with that Id happening in the past
	 */
	@Override
	public FutureMeeting getFutureMeeting(int id) {
		
		List<Meeting> meetingstream = cmMeetings.stream()
				.filter(m -> m.getId() == id)
				.collect(Collectors.toList());
		if(!(meetingstream.isEmpty())) {
			Meeting meeting = meetingstream.get(0);
				if (!(meeting instanceof FutureMeeting)) {
					throw new IllegalArgumentException("This meeting is not a future meeting");
				}
			return (FutureMeeting) meeting;
		} else { 
			return null;
		}
	}

	/**
	 * @see spec.ContactManager#getMeeting(int id)
	 */
	@Override
	public Meeting getMeeting(int id) {
		
		List<Meeting> meetingstream = cmMeetings.stream()
				.filter(m -> m.getId() == id)
				.collect(Collectors.toList());
		if(!(meetingstream.isEmpty())) {
			Meeting meeting = meetingstream.get(0);
			return meeting;
		} else {
		return null;
		}
	}

	@Override
	/**
	 * @see spec.ContactManager#getFutureMeetingList(Contact contact)
	 * @throws IllegalArgumentException if the contact is not in the Contact Manager
	 * @throws NullPointerException if the contact is null
	 */
	public List<Meeting> getFutureMeetingList(Contact contact) {
		
		Objects.requireNonNull(contact);
		
		if (!cmContacts.contains(contact)) throw new IllegalArgumentException("The contact must be in the Contact Manager");
		
		List<Meeting> meetingstream = cmMeetings.stream()
				.filter(m -> m.getContacts().contains(contact) && m instanceof FutureMeeting)
//				.sorted(Comparator.comparing(Meeting::getId)) // sorts by Id
				.sorted(Comparator.comparing(Meeting::getDate)) // sorts by date
				.distinct() // shows only those meetings with same contacts on different dates
				.collect(Collectors.toList());
				return meetingstream;		
		}

	@Override
	public List<Meeting> getMeetingListOn(Calendar date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PastMeeting> getPastMeetingListFor(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
		Objects.requireNonNull(contacts);
		Objects.requireNonNull(date);
		Objects.requireNonNull(text);
		
		cmDate = Calendar.getInstance();
		
		if (!cmContacts.containsAll(contacts)) throw new IllegalArgumentException("The contacts you entered do not exist in the ContactManager");
		if (contacts.isEmpty()) throw new IllegalArgumentException("There must be at least one contact for the meeting");
		if (date.after(cmDate)) throw new IllegalArgumentException("A past meeting cannot take place in the future");
		
		meetingId = updateMeetingId();
		PastMeeting newPastMeeting = new PastMeetingImpl(meetingId, date, contacts, text);
		cmMeetings.add(newPastMeeting);
	}

	@Override
	public PastMeeting addMeetingNotes(int id, String text) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see spec.ContactManager#addNewContact 
	 */
	@Override
	public int addNewContact(String name, String notes) {
		
		Objects.requireNonNull(name);
		Objects.requireNonNull(notes);
		if (name.equals("")|| notes.equals("")) throw new IllegalArgumentException("Please enter a name and notes for the contact");
		
		int id = updateContactId(); // set contactId to 1 for the first meeting, or add 1 if adding a contact within the set
		Contact newContact = new ContactImpl(id, name, notes);
		cmContacts.add(newContact);
		return id;
	}

	/**
	 * @see spec.ContactManager#getContacts(String name)
	 * @throws NullPointerException if the name passed as parameter is null
	 * This method creates a new set called searchResults
	 * It iterates over the set looking for the specified string converted to lower case
	 * Once the name is returned, a new contact with the specified id, name and notes is
	 * added to the searchResults set
	 */
	@Override
	public Set<Contact> getContacts(String name) {
		
		Objects.requireNonNull(name);
		if (name.equals("")) {
			return cmContacts.stream().collect(Collectors.toCollection(HashSet::new));
		} else {
			Set<Contact> searchResults = cmContacts.stream()
				.filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
				.collect(Collectors.toCollection(HashSet::new));
		return searchResults;
		}
	}

	/**
	 * @see spec.ContactManager#getContacts(int ... ids) {
	 * @throws IllegalArgumentException if no IDs are provided or if any of the provided ids does not match a real contact
	 */
	@Override
	public Set<Contact> getContacts(int... ids) { // int... ids means an array of ints
		
		if (ids.length==0) throw new IllegalArgumentException("Please enter an id of the contact you wish to find");
		
		Set<Contact> contactStream = cmContacts.stream()
				.filter(c -> Arrays.stream(ids).anyMatch(id -> id == c.getId()))
				.collect(Collectors.toCollection(HashSet::new));
		
		if (contactStream.size() == 0) throw new IllegalArgumentException("The id you entered could not be found");
		
		return contactStream;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * This method checks whether a file already exists of contacts and meetings for the ContactManager to use, and 
	 * read from the file. If not, a file is created.
	 * Variables meetingId, contactId, cmContacts, cmMeetings and cmDate are initialized.
	 */
	@SuppressWarnings("unchecked")
	private void readContactManagerFile() {
		
	    	 Path p = FileSystems.getDefault().getPath(FILENAME);
	         if (Files.exists(p)) {
	        	 try {
	        		 ObjectInputStream objectIn = new ObjectInputStream 
	        				 						(new BufferedInputStream
	        				 								(new FileInputStream(FILENAME)));
	        		 cmMeetings = (List<Meeting>) objectIn.readObject();
	        		 cmContacts = (Set<Contact>) objectIn.readObject();
	        		 contactId = (int) objectIn.readObject();
	        		 meetingId = (int) objectIn.readObject();
	        		 cmDate = (Calendar) objectIn.readObject();
	        		 objectIn.close();
	        	 } catch (IOException | ClassNotFoundException e) {
	        		 e.printStackTrace();
	        	 }	 
	         } else {
	        	 meetingId = 0;
	        	 contactId = 0;
	        	 cmContacts = new HashSet<>();
	        	 cmMeetings = new ArrayList<>();
	        	 cmDate = Calendar.getInstance();
	         }
	}

} // end of class	
