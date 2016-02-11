/**
 * Implementation of interface Contact
 * @author BBK-PiJ-2015-74 lburge01
 */

package impl;

import spec.Contact;

public class ContactImpl implements Contact {

	/**
	 * The most general constructor must have three parameters: int, String, String
	 * The first one corresponds to the ID provided by the ContactManager, the next one corresponds to the name,
	 * and the last one corresponds to the initial set of notes about the contact
	 * @param ID, the ID provided by the Contact Manager
	 * @param name, the name of the contact
	 * @param firstnotes, the initial set of notes about the contact
	 */
	public ContactImpl(int ID, String name, String firstnotes) {
		// TODO Auto-generated constructor stub	
	}
	
	/**
	 * Another, more restricted constructor must have two parameters only: ID and name
	 * if the ID provided is zero or negative, an IllegalArgumentException must be thrown
	 * If any of the references provided/ pointers passed as parameters to the constructors is null, 
	 * a NullPointerException must be thrown.
	 * @param ID
	 * @param name
	 */
	protected ContactImpl(int ID, String name) {
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
