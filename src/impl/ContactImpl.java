/**
 * Implementation of interface Contact
 * @author BBK-PiJ-2015-74 lburge01
 */

package impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import spec.Contact;
import java.io.Serializable;

public class ContactImpl implements Contact, Serializable {
	
	/**
	 * Chose to use a list to hold the added notes so this can be easily updated and output
	 * Otherwise we just have to make a longer string every time and it's difficult to track updates
	 * Successive notes will be added to an array list
	 */
	private int contactId;
	private String contactName;
	private List<String> contactNotes;

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
		contactNotes = new ArrayList<>(); // initialises a new array list to hold the notes
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
	

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNotes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNotes(String note) {
		// TODO Auto-generated method stub

	}

}
