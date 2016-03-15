/**
 * Implementation of interface Contact
 * @author BBK-PiJ-2015-74 lburge01
 */

package impl;

import java.util.ArrayList;
import java.util.Objects;

import spec.Contact;
import java.io.Serializable;

public class ContactImpl implements Contact, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Chose to use an array list to hold the added notes so this can be easily updated and output
	 * contactNotes is initialised as an Array List using the Java Collections Array List interface 
	 * Otherwise we just have to make a longer string every time and it's difficult to track updates
	 * Successive notes will be added to an array list
	 */
	private int contactId;
	private String contactName;
	private ArrayList<String> contactNotes;

	/**
	 * This constructor creates a new Contact without notes
	 * Another, more restricted constructor must have two parameters only: ID and name
	 * if the ID provided is zero or negative, an IllegalArgumentException must be thrown
	 * If any of the references provided/ pointers passed as parameters to the constructors is null, 
	 * a NullPointerException must be thrown.
	 * @param id, the ID provided by the Contact Manager
	 * @param name, the name of the Contact
	 */
	public ContactImpl(int id, String name) {
		
		if (id <= 0) {
			throw new IllegalArgumentException ("Error in contact: contact ID must not be zero or negative");
		}
		
		if (name.equals("")) {
			throw new IllegalArgumentException ("Error in contact: name cannot be empty" + "for contactId :" + id);
		}
		
		this.contactId = Objects.requireNonNull(id);
		this.contactName = Objects.requireNonNull(name);
		contactNotes = new ArrayList<>(); 
	}
	
	/**
	 * This constructor creates a new Contact with notes
	 * The most general constructor must have three parameters: int, String, String
	 * The first one corresponds to the ID provided by the ContactManager, the next one corresponds to the name,
	 * and the last one corresponds to the initial set of notes about the contact
	 * @param id, the ID provided by the Contact Manager
	 * @param name, the name of the contact
	 * @param notes, the initial set of notes about the contact
	 */
	public ContactImpl(int id, String name, String notes) {
		
		this (id, name); // this passes the parameters from the first constructor through to the second
		addNotes(notes); 
	}
	
	/**
	 * @see Contact#getId()
	 */
	@Override
	public int getId() {
		return contactId;
	}

	/**
	 * @see Contact#getName()
	 */
	@Override
	public String getName() {
		return contactName;
	}

	/**
	 * @see Contact#getNotes()
	 */
	@Override
	public String getNotes() {
		String notesString = "";
		for (int k = 0; k <= contactNotes.size()-1; k++) {
			notesString = notesString + "\n" + contactNotes.get(k);
		}
		return notesString;
	}

	/**
	 * @see Contact#addNotes(note)
	 * @see Contact#addNewContact(String name, String notes)
	 * This specifies an IllegalArgumentException must be returned if the name or notes are empty strings
	 */
	@Override
	public void addNotes(String note) {
		Objects.requireNonNull(note);
		if (note.equals("")) {
			throw new IllegalArgumentException ("Error in contact: notes cannot be empty" + "for contactId :" + contactId);
		}
		contactNotes.add(note);
	}

}
