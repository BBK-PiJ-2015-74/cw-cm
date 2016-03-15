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

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import spec.Contact;
import spec.ContactManager;
import spec.FutureMeeting;
import spec.Meeting;
import spec.PastMeeting;

public class ContactManagerImpl implements ContactManager {
	
	private int contactId;
	private int meetingId;
	private Calendar cmDate; // the date of the Contact Manager
	private Set<Contact> cmContacts; // the contacts that already exist
	private List<Meeting> cmMeetings; //the meetings that already exist
	private static final String FILENAME = "contacts.txt";

	public ContactManagerImpl() {
		
		contactId = -1;
		meetingId = -1;
		readContactManagerFile();
		// Constructor to add, must allow file to be read at start-up and recover data from previous session
	}

	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		// TODO Auto-generated method stub
		return 0;
	}

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

	@Override
	public int addNewContact(String name, String notes) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Contact> getContacts(String name) {
		// TODO Auto-generated method stub
		return null;
	}

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
	 * This method checks whether a file already exists of contacts and meetings for the ContactManager to use
	 * If not, a file is created
	 */
	@SuppressWarnings("unchecked")
	public void readContactManagerFile() {
		
	    	 Path p = FileSystems.getDefault().getPath(FILENAME);
	         if (Files.exists(p)) {
	        	 try {
	        		 ObjectInputStream s = new ObjectInputStream 
	        				 						(new BufferedInputStream
	        				 								(new FileInputStream(FILENAME)));
	        		 cmMeetings = (List<Meeting>) s.readObject();
	        		 cmContacts = (Set<Contact>) s.readObject();
	        		 contactId = (int) s.readObject();
	        		 meetingId = (int) s.readObject();
	        		 cmDate = (Calendar) s.readObject();
	           
	        	 } catch (IOException | ClassNotFoundException e) {
	        		 e.printStackTrace();
	        	 }
	        }
	      }

} // end of class	
