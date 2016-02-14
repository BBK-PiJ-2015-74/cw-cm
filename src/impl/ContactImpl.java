/**
 * Implementation of interface Contact
 * @author BBK-PiJ-2015-74 lburge01
 */

package impl;

import java.util.ArrayList;

import spec.Contact;
import java.io.Serializable;

public class ContactImpl implements Contact, Serializable {
	
	/**
	 * Chose to use an array list to hold the added notes so this can be easily updated and output
	 * Otherwise we just have to make a longer string every time and it's difficult to track updates
	 */
	private int ID;
	private String fullName;
	private ArrayList<String> initialNotes;

	/**
	 * The most general constructor must have three parameters: int, String, String
	 * The first one corresponds to the ID provided by the ContactManager, the next one corresponds to the name,
	 * and the last one corresponds to the initial set of notes about the contact
	 * @param ID, the ID provided by the Contact Manager
	 * @param fullName, the name of the contact
	 * @param initialNotes, the initial set of notes about the contact
	 */
	public ContactImpl(int ID, String fullName, String initialNotes) {
		// TODO Auto-generated constructor stub	
	}
	
	/**
	 * Another, more restricted constructor must have two parameters only: ID and name
	 * if the ID provided is zero or negative, an IllegalArgumentException must be thrown
	 * If any of the references provided/ pointers passed as parameters to the constructors is null, 
	 * a NullPointerException must be thrown.
	 * @param ID
	 * @param fullName
	 */
	public ContactImpl(int ID, String fullName) {
		//TODO second constructor stub
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
