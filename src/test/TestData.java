package test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import impl.ContactImpl;
import spec.Contact;

/**
 * A class which contains contacts, meetings, date and other data in static final form for testing purposes
 * @author BBK-PiJ-2015-74
 *
 */

public class TestData {

	public TestData() {
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
	
	static final int MEETING_ID_01 = 1;
	static final int MEETING_ID_02 = 2;
	static final int MEETING_ID_03 = 3;
	static final int MEETING_ID_04 = 4;
	static final int MEETING_ID_05 = 5;
	static final int MEETING_ID_06 = 6;
	static final int MEETING_ID_07 = 7;
	static final int MEETING_ID_08 = 8;
	static final int MEETING_ID_09 = 9;
	static final int MEETING_ID_10 = 10;
	
	static final Calendar TIME_NOW = Calendar.getInstance();
	static final Calendar FUTURE_DATE_01 = new GregorianCalendar(2017,9,29);
	static final Calendar FUTURE_DATE_02 = new GregorianCalendar(2018,7,30);
	static final Calendar FUTURE_DATE_03 = new GregorianCalendar(2016,10,15);
	static final Calendar PAST_DATE_01 = new GregorianCalendar(2015,3,10);
	static final Calendar PAST_DATE_02 = new GregorianCalendar(2015,12,05);
	static final Calendar PAST_DATE_03 = new GregorianCalendar(2016,1,11);	
	
	static Contact contact1 = new ContactImpl(CONTACT_ID_01, CONTACT_NAME_01, CONTACT_NOTES_01);
    static Contact contact2 = new ContactImpl(CONTACT_ID_02, CONTACT_NAME_02, CONTACT_NOTES_02);
    static Contact contact3 = new ContactImpl(CONTACT_ID_03, CONTACT_NAME_03, CONTACT_NOTES_03);
    static Contact contact4 = new ContactImpl(CONTACT_ID_04, CONTACT_NAME_04, CONTACT_NOTES_04);
    static Contact contact5 = new ContactImpl(CONTACT_ID_05, CONTACT_NAME_05, CONTACT_NOTES_05);
    static Contact contact6 = new ContactImpl(CONTACT_ID_06, CONTACT_NAME_06, CONTACT_NOTES_06);
    static Contact contact7 = new ContactImpl(CONTACT_ID_07, CONTACT_NAME_07, CONTACT_NOTES_07);
    static Contact contact8 = new ContactImpl(CONTACT_ID_08, CONTACT_NAME_08, CONTACT_NOTES_08);
    static Contact contact9 = new ContactImpl(CONTACT_ID_09, CONTACT_NAME_09, CONTACT_NOTES_09);
    static Contact contact10 = new ContactImpl(CONTACT_ID_10, CONTACT_NAME_10, CONTACT_NOTES_10);
}
	


