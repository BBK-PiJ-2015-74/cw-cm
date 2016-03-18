package test;
import impl.ContactManagerImpl;
import impl.FutureMeetingImpl;
import impl.ContactImpl;
import spec.*;
import test.TestData;
import java.util.Set;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class ContactManagerMain {

	public ContactManagerMain() {
	}

	public static void main(String[] args) {
		
		Calendar now = Calendar.getInstance();
		Set<Contact> setOf2TestContacts;
		
		//Set time to current system time
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		System.out.println(dateFormat.format(now.getTime()));
				
		//Delete all data files if they exist
			     try {
			    	 Path p = FileSystems.getDefault().getPath("contacts.txt");
			          if (Files.exists(p)) { 
			        	  Files.delete(p);
			          }
			     } catch (IOException ioEx) {
			    	 ioEx.printStackTrace();
			     }
			    
			    //Create a test set of 2 contacts 
			    setOf2TestContacts = new HashSet<>();
				setOf2TestContacts.add(TestData.CONTACT1);
				setOf2TestContacts.add(TestData.CONTACT4);
				
				System.out.println(TestData.CONTACT1.getName());
				System.out.println(TestData.CONTACT4.getName());
				    
				//Create a new ContactManager implementation
				ContactManager cm = new ContactManagerImpl();
				
				//Create a ContactManager implementation with 5 contacts
				ContactManager cm5 = new ContactManagerImpl();
				System.out.println("Printing the ids of the contacts added");
				System.out.println(cm5.addNewContact(TestData.CONTACT_NAME_01, TestData.CONTACT_NOTES_01));
				System.out.println(cm5.addNewContact(TestData.CONTACT_NAME_02, TestData.CONTACT_NOTES_02));
				System.out.println(cm5.addNewContact(TestData.CONTACT_NAME_03, TestData.CONTACT_NOTES_03));
				System.out.println(cm5.addNewContact(TestData.CONTACT_NAME_04, TestData.CONTACT_NOTES_04));
				System.out.println(cm5.addNewContact(TestData.CONTACT_NAME_05, TestData.CONTACT_NOTES_05));
				
				Set<Contact> contactSet = cm5.getContacts("Humpty");
				
				System.out.println(contactSet.size());
				System.out.println(contactSet.toString());
				System.out.println(cm5.getContacts().toString());
				
				if (contactSet.contains(TestData.CONTACT_NAME_01)) {
					System.out.println("Search results contain Humpty");
				} else {
					System.out.println("Cannot find specified string");
				}
				
				int FM1 = cm5.addFutureMeeting(setOf2TestContacts, TestData.FUTURE_DATE_03);
				
				System.out.println(FM1);


	}

}
