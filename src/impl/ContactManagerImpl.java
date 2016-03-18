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
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
		// Constructor to add, must allow file to be read at start-up and recover data from previous session
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
	 */
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		
		Objects.requireNonNull(contacts);
		Objects.requireNonNull(date);
		
		if (date.before(Calendar.getInstance())) {
			throw new IllegalArgumentException("A future meeting cannot be held in the past. Please check date"); 
		}
		
		if (validateContacts(contacts) == false) {
			throw new IllegalArgumentException("Contact not in Contact Manager. Please add contact first");
		}
		
		int meetingId = updateMeetingId(); // set meetingId to 1 for the first meeting, or add 1 if adding a meeting at the end of the list
		FutureMeeting futureMeetingToAdd = new FutureMeetingImpl(meetingId, date, contacts);
		Objects.requireNonNull(futureMeetingToAdd);
		cmMeetings.add(futureMeetingToAdd);
		return meetingId;
	}

	/**
	 * 
	 */
	@Override
	public PastMeeting getPastMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FutureMeeting getFutureMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Meeting getMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub

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
		Set<Contact> searchResults = new HashSet<>();
		
		for (Contact c : cmContacts) {
			if (c.getName().toLowerCase().contains(name.toLowerCase())) {
				searchResults.add(new ContactImpl(c.getId(), c.getName(), c.getNotes()));
			}
			else if (name.equals("")) {
				searchResults.add(new ContactImpl(c.getId(), c.getName(), c.getNotes()));
			}
		}
		return searchResults;
	}

	/**
	 * @see spec.ContactManager#getContacts(int ... ids) {
	 * @throws IllegalArgumentException if no IDs are provided or if any of the provided ids does not match a real contact
	 */
	@Override
	public Set<Contact> getContacts(int... ids) {
		// TODO Auto-generated method stub
		return null;
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
	
	/**
	 * Check whether a contact is unknown or non-existent
	 */
	private boolean validateContacts(Set<Contact> contacts) {
		return (cmContacts.containsAll(contacts))? true : false;
	}
		

} // end of class	
