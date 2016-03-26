package test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import impl.ContactImpl;
import impl.ContactManagerImpl;
import spec.Contact;
import spec.ContactManager;

/**
 * A class which contains contacts, meetings, date and other data in static final form for testing purposes
 * @author BBK-PiJ-2015-74 lburge01
 */

public final class TestData {

	private TestData() {
	}
	
	static final int CONTACT_ID_01 = 1;
	static final int CONTACT_ID_02 = 2;
	static final int CONTACT_ID_03 = 3;
	static final int CONTACT_ID_04 = 4;
	static final int CONTACT_ID_05 = 5;
	static final int CONTACT_ID_06 = 6;
	static final int CONTACT_ID_07 = 7;
	static final int CONTACT_ID_08 = 8;
	static final int CONTACT_ID_09 = 9;
	static final int CONTACT_ID_10 = 10;
	static final int INVALID_ID_101 = 101;
	static final int INVALID_ID_102 = 102;
	static final int INVALID_ID_103 = 103;
	
	static final String CONTACT_NAME_01 = "Humpty Dumpty";
	static final String CONTACT_NAME_02 = "Little Miss Muffet";
	static final String CONTACT_NAME_03 = "The Grand Old Duke of York";
	static final String CONTACT_NAME_04 = "Jack and Jill";
	static final String CONTACT_NAME_05 = "Little Bo Peep";
	static final String CONTACT_NAME_06 = "Cinderella";
	static final String CONTACT_NAME_07 = "Rapunzel";
	static final String CONTACT_NAME_08 = "Wicked witch";
	static final String CONTACT_NAME_09 = "Tom Kitten";
	static final String CONTACT_NAME_10 = "The Gruffalo";
	static final String EMPTY_CONTACT_NAME = "";
	
	static final String CONTACT_NOTES_01 = "He's a good egg";
	static final String CONTACT_NOTES_02 = "The spider scared her";
	static final String CONTACT_NOTES_03 = "He had ten thousand men";
	static final String CONTACT_NOTES_04 = "They went up the hill to fetch a pail of water";
	static final String CONTACT_NOTES_05 = "She lost her sheep, silly girl";
	static final String CONTACT_NOTES_06 = "Disliked by the ugly sisters";
	static final String CONTACT_NOTES_07 = "Let down her hair";
	static final String CONTACT_NOTES_08 = "Green face, big chin, ugly nose";
	static final String CONTACT_NOTES_09 = "Cute character of Beatrix Potter fame";
	static final String CONTACT_NOTES_10 = "Foiled by the big bad mouse";
	static final String EMPTY_CONTACT_NOTES = "";
	
	static final String NULL_SEARCH_STRING = null;
	
	static final Contact CONTACT_01 = new ContactImpl(CONTACT_ID_01, CONTACT_NAME_01, CONTACT_NOTES_01);
    static final Contact CONTACT_02 = new ContactImpl(CONTACT_ID_02, CONTACT_NAME_02, CONTACT_NOTES_02);
    static final Contact CONTACT_03 = new ContactImpl(CONTACT_ID_03, CONTACT_NAME_03, CONTACT_NOTES_03);
    static final Contact CONTACT_04 = new ContactImpl(CONTACT_ID_04, CONTACT_NAME_04, CONTACT_NOTES_04);
    static final Contact CONTACT_05 = new ContactImpl(CONTACT_ID_05, CONTACT_NAME_05, CONTACT_NOTES_05);
    static final Contact CONTACT_06 = new ContactImpl(CONTACT_ID_06, CONTACT_NAME_06, CONTACT_NOTES_06);
    static final Contact INVALID_CONTACT = new ContactImpl(CONTACT_ID_07, CONTACT_NAME_07, CONTACT_NOTES_07);
    
    
    static final Contact INVALID_CONTACT1 = new ContactImpl(INVALID_ID_101, "Mickey Mouse", "Married to Minnie Mouse");
    static final Contact INVALID_CONTACT2 = new ContactImpl(INVALID_ID_102, "Donald Duck", "Quack quack");
    static final Contact INVALID_CONTACT3 = new ContactImpl(INVALID_ID_103, "Pluto the dog", "Goofy teeth");
	
	static final Calendar TIME_NOW = Calendar.getInstance();
	static final Calendar FUTURE_DATE_01 = new GregorianCalendar(2016,10,15);
	static final Calendar FUTURE_DATE_02 = new GregorianCalendar(2016,11,22);
	static final Calendar FUTURE_DATE_03 = new GregorianCalendar(2017,7,30);
	static final Calendar FUTURE_DATE_04 = new GregorianCalendar(2017,9,29);
	static final Calendar FUTURE_DATE_05 = new GregorianCalendar(2018,1,1);
	
	static final Calendar PAST_DATE_01 = new GregorianCalendar(2015,3,10);
	static final Calendar PAST_DATE_02 = new GregorianCalendar(2015,12,05);
	static final Calendar PAST_DATE_03 = new GregorianCalendar(2016,1,11);	
	static final Calendar PAST_DATE_04 = new GregorianCalendar(2016,2,17);
	static final Calendar PAST_DATE_05 = new GregorianCalendar(2016,3,13);
	
	static final String PAST_MTG_NOTES_01 = "A solitary meeting involving a monologue";
	static final String PAST_MTG_NOTES_02 = "Looney tunes, that's all Folks!";
	static final String PAST_MTG_NOTES_03 = "A boardroom coup";
	static final String PAST_MTG_NOTES_04 = "A nursery rhyme smorgasbord";
	static final String PAST_MTG_NOTES_05 = "Too many cooks spoil the broth. We could have done it with half the people";
	
	static final int PAST_MTG_ID_01 = 1;
	static final int PAST_MTG_ID_02 = 2;
	static final int PAST_MTG_ID_03 = 3;
	static final int PAST_MTG_ID_04 = 4;
	static final int PAST_MTG_ID_05 = 5;
	
	static final int FUTURE_MTG_ID_01 = 1;
	static final int FUTURE_MTG_ID_02 = 2;
	static final int FUTURE_MTG_ID_03 = 3;
	static final int FUTURE_MTG_ID_04 = 4;
	static final int FUTURE_MTG_ID_05 = 5;
	
	static final int MTG_ID_01 = 1;
	static final int MTG_ID_02 = 2;
	static final int MTG_ID_03 = 3;
	static final int MTG_ID_04 = 4;
	static final int MTG_ID_05 = 5;
	static final int MTG_ID_06 = 6;
	static final int MTG_ID_07 = 7;
	static final int MTG_ID_08 = 8;
	static final int DUPLICATE_MTG_ID_07 = 9;
	static final int DUPLICATE_MTG_ID_08 = 10;
	static final int INVALID_MTG_ID_101 = 101;
	static final int INVALID_MTG_ID_102 = 102;
	
	/**
	 * Create empty and test ContactManager implementations for use in tests
	 * @return emptyCM
	 */
	static ContactManager buildEmptyCM() {
		ContactManager emptyCM = new ContactManagerImpl();
		return emptyCM;
	}
	
	/**
	 * @return testCM with 6 test contacts but no meetings
	 */
	static ContactManager buildTestCM() {
		ContactManager testCM = new ContactManagerImpl();
		testCM.addNewContact(CONTACT_NAME_01, CONTACT_NOTES_01); //NB. The ContactManager assigns the contact ID.
		testCM.addNewContact(CONTACT_NAME_02, CONTACT_NOTES_02);
		testCM.addNewContact(CONTACT_NAME_03, CONTACT_NOTES_03);
		testCM.addNewContact(CONTACT_NAME_04, CONTACT_NOTES_04);
		testCM.addNewContact(CONTACT_NAME_05, CONTACT_NOTES_05);
		testCM.addNewContact(CONTACT_NAME_06, CONTACT_NOTES_06);
		return testCM;
	}
	
	static Set<Contact> buildInvalidContactSet() {
		Set<Contact> invalidContactSet = new HashSet<>();
		invalidContactSet.add(INVALID_CONTACT1);
		invalidContactSet.add(INVALID_CONTACT2);
		invalidContactSet.add(INVALID_CONTACT3);
		return invalidContactSet;
	}
	
	/**
	 * @return testMeetingsCM with 6 test contacts and some future and past meetings for testing purposes
	 * Also add some duplicate meetings for duplication tests required for methods which return a List<Meeting>
	 * The future, past and duplicate meetings are added in ContactManagerTest
	 */
	static ContactManager buildTestMeetingsCM() {
		ContactManager testMeetingsCM = new ContactManagerImpl();
		testMeetingsCM.addNewContact(CONTACT_NAME_01, CONTACT_NOTES_01); //NB. The ContactManager assigns the contact ID.
		testMeetingsCM.addNewContact(CONTACT_NAME_02, CONTACT_NOTES_02);
		testMeetingsCM.addNewContact(CONTACT_NAME_03, CONTACT_NOTES_03);
		testMeetingsCM.addNewContact(CONTACT_NAME_04, CONTACT_NOTES_04);
		testMeetingsCM.addNewContact(CONTACT_NAME_05, CONTACT_NOTES_05);
		testMeetingsCM.addNewContact(CONTACT_NAME_06, CONTACT_NOTES_06);
		return testMeetingsCM;
	}
    
	//threeContactSet is CONTACT_04, CONTACT_05 and CONTACT_06; singleContactSet is CONTACT_06
	static void addTestMeetings(ContactManager testMeetingsCM, Set<Contact> singleContact06, Set<Contact> threeContactSet) {
		testMeetingsCM.addFutureMeeting(singleContact06, FUTURE_DATE_01); //id 1, FutureMeeting
	    testMeetingsCM.addFutureMeeting(threeContactSet, FUTURE_DATE_02); // id 2, FutureMeeting
	    testMeetingsCM.addNewPastMeeting(singleContact06, PAST_DATE_01, PAST_MTG_NOTES_01);// id 3, PastMeeting
	    testMeetingsCM.addFutureMeeting(singleContact06, FUTURE_DATE_03); // id 4, FutureMeeting
	    testMeetingsCM.addNewPastMeeting(threeContactSet, PAST_DATE_02, PAST_MTG_NOTES_02);// id 5, PastMeeting
	    testMeetingsCM.addNewPastMeeting(singleContact06, PAST_DATE_03, PAST_MTG_NOTES_03); //id 6, PastMeeting
	    testMeetingsCM.addFutureMeeting(threeContactSet, FUTURE_DATE_04); //id 7, FutureMeeting
	    testMeetingsCM.addNewPastMeeting(threeContactSet, PAST_DATE_04, PAST_MTG_NOTES_04); //id 8, PastMeeting
	}
	     
	static void addDuplicateMeetings(ContactManager testMeetingsCM, Set<Contact> threeContactSet) {  
	     testMeetingsCM.addFutureMeeting(threeContactSet, FUTURE_DATE_05); //id 9
	     testMeetingsCM.addFutureMeeting(threeContactSet, FUTURE_DATE_05); //id 10
	     //testMeetingsCM.addNewPastMeeting(threeContactSet, PAST_DATE_05, PAST_MTG_NOTES_05); //id 11
	     //testMeetingsCM.addNewPastMeeting(threeContactSet, PAST_DATE_05, PAST_MTG_NOTES_05); //id 12
	}
	
} // end of class
	


