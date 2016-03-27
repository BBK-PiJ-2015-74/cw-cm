package test;

import impl.ContactManagerImpl;
import impl.FutureMeetingImpl;
import impl.PastMeetingImpl;
import impl.ContactImpl;
import spec.*;
import static test.TestData.*;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.matchers.JUnitMatchers;
import static org.hamcrest.CoreMatchers.*;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author BBK-PiJ-2015-74 lburge01
 * A class to test ContactManagerImpl
 * ContactManager keeps track of contacts, past and future meetings
 */

public class ContactManagerTest {
	
	static final String FILENAME = "contacts.txt";
	final File cmFile = new File(FILENAME);
	private ContactManager emptyCM, testCM, testMeetingsCM;
	private Set<Contact> emptyContactSet, singleContact04, twoContactSet, fiveContactSet, singleContact06, threeContactSet, invalidContactSet;
	
	//--------------------------------Set Up -------------------------------------------------------------------------
	
	/**
	 * Set up a clean ContactManager environment for each test, set up test contact sets and delete data files if they exist
	 */
	@Before 
	public void setUp() {

	     try {
	    	 Path p = FileSystems.getDefault().getPath(FILENAME);
	          if (Files.exists(p)) { 
	        	  Files.delete(p);
	          }
	     } catch (IOException ioEx) {
	    	 ioEx.printStackTrace();
	     }
	     
	     //empty, no contacts or meetings, for testing
	     emptyCM = buildEmptyCM();
	     
	     // contains contacts 01, 02, 03, 04, 05, for testing Contacts methods
	     // contains contacts, but does not contain meetings
	     // Uses contact sets emptyContactSet, singleContact04, twoContactSet, fiveContactSet
	     testCM = buildTestCM();   
	     emptyContactSet = new HashSet<>();
	     singleContact04 = testCM.getContacts(CONTACT_ID_04);
	     twoContactSet = testCM.getContacts(CONTACT_ID_01, CONTACT_ID_02);
	     fiveContactSet = testCM.getContacts(CONTACT_ID_01, CONTACT_ID_02, CONTACT_ID_03, CONTACT_ID_04, CONTACT_ID_05);
	     invalidContactSet = buildInvalidContactSet();
	     
	     // contains contacts 01, 02, 03, 04, 05, 06 for testing Meetings methods
	     // Uses contact sets singleContact06, threeContactSet
	     // also contains some test Meetings. See TestData
	     testMeetingsCM = buildTestMeetingsCM();
	     singleContact06 = testMeetingsCM.getContacts(CONTACT_ID_06);
	     threeContactSet = testMeetingsCM.getContacts(CONTACT_ID_04, CONTACT_ID_05, CONTACT_ID_06);
	     addTestMeetings(testMeetingsCM, singleContact06, threeContactSet);
	     //addDuplicateMeetings(testMeetingsCM, threeContactSet);
	}

	@After
	public void tearDown() {
	}
	
	//---------------------------Test Constructor and initial methods ----------------------------
	
	@Test
	public void testContactManagerConstructor() {
		assertEquals(emptyCM.getContactId(), 0);
		assertEquals(emptyCM.getMeetingId(), 0);
		assertTrue(emptyCM.getContacts().isEmpty());
		assertTrue(emptyCM.getMeetings().isEmpty());
	}
	
	@Test
	public void testUpdateContactId() {
		emptyCM.updateContactId();
		emptyCM.updateContactId();
		assertEquals (emptyCM.getContactId(), 2);
	}
	
	@Test
	public void testUpdateMeetingId() {
		emptyCM.updateMeetingId();
		emptyCM.updateMeetingId();
		assertEquals (emptyCM.getMeetingId(), 2);
	}
	
	@Test
	public void testUpdateContactManagerFile() {
		fail("Not yet implemented");
	}
	
	
	//----------------------Test addNewContact-------------------------------------------------------------
	
	@Test
	public void addNewContactReturnsCorrectId() {
		assertTrue(testCM.addNewContact(CONTACT_NAME_07, CONTACT_NOTES_07) == 7);
		assertTrue(testCM.addNewContact(CONTACT_NAME_08, CONTACT_NOTES_08) == 8);
		assertTrue(testCM.addNewContact(CONTACT_NAME_09, CONTACT_NOTES_09) == 9);
		assertTrue(testCM.addNewContact(CONTACT_NAME_10, CONTACT_NOTES_10) == 10);
		assertTrue(testCM.getContacts("").size() == 10);
	}
	
	/** 
	 * @test This test checks the size of the contact set is zero before and after the addition of the contact
	 */
	@Test (expected = IllegalArgumentException.class)
	public void addNewContactEmptyNameThrowsException() {	
		assertTrue(emptyCM.getContacts("").size()==0);
		emptyCM.addNewContact(EMPTY_CONTACT_NAME, CONTACT_NOTES_07);
		assertTrue(emptyCM.getContacts("").size()==0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addNewContactEmptyNotesThrowsException() {
		assertTrue(emptyCM.getContacts("").size()==0);
		emptyCM.addNewContact(CONTACT_NAME_04, EMPTY_CONTACT_NOTES);
		assertTrue(emptyCM.getContacts("").size()==0);
	}

	@Test (expected = NullPointerException.class) 
	public void addNewContactNullNameThrowsException() {
		assertTrue(emptyCM.getContacts("").size()==0);
		emptyCM.addNewContact(null, CONTACT_NOTES_07);
		assertTrue(emptyCM.getContacts("").size()==0);
	}
	
	@Test (expected = NullPointerException.class) 
	public void addNewContactNullNotesThrowsException() {
		assertTrue(emptyCM.getContacts("").size()==0);
		emptyCM.addNewContact(CONTACT_NAME_04, null);
		assertTrue(emptyCM.getContacts("").size()==0);
	}
	
	//------------------------Test getContacts(String name)-----------------------------------------------------
	/**
	 * This test builds a new contact set for the search results and therefore contains(Set) or containsAll(Set) 
	 * cannot be used, as the object references are not the same. Therefore the tests look at size and whether
	 * the new sets created are empty
	 */
	
	@Test 
	public void getContactsReturnsContactSetContainingString() {		
		Set<Contact> contactSet = new HashSet<>();
		contactSet = testCM.getContacts("Humpty");
		assertFalse(contactSet.isEmpty());
		assertTrue(contactSet.size() == 1);
	}
	
	@Test
	public void getContactsReturnsTwoContactSetContainingString() {
		Set<Contact> contactSet = new HashSet<>();
		contactSet = testCM.getContacts("Little"); // Little Bo Peep, Little Miss Muffett
		assertFalse(contactSet.isEmpty());
		assertTrue(contactSet.size() == 2);
	}

	@Test 
	public void getContactsEmptySearchStringReturnsAllContacts() {
		Set<Contact> contactSet =  testCM.getContacts("");
		assertFalse(contactSet.isEmpty());
		assertTrue(contactSet.size() == 6);
	}
	
	@Test (expected = NullPointerException.class)
	public void getContactsNullSearchStringThrowsException() {
		testCM.getContacts(NULL_SEARCH_STRING);
	}

	//------------------------------Test getContacts(int... ids)-------------------------------------
	
	@Test
	public void getContactsReturnsContactSetFromId() {
		Set<Contact> contactSet = new HashSet<>();
		contactSet = testCM.getContacts(CONTACT_ID_01,CONTACT_ID_02,CONTACT_ID_03,CONTACT_ID_04,CONTACT_ID_05);
		assertTrue(contactSet.size() == 5);
		assertTrue(contactSet.containsAll(twoContactSet));
		assertTrue(contactSet.containsAll(fiveContactSet));
	}
	
	@Test
	public void getContactsReturnsSingleContactFromId() {
		Set<Contact> contactSet = testCM.getContacts(04);
		assertTrue(contactSet.size() == 1);
		assertTrue(contactSet.containsAll(singleContact04));
		assertFalse(singleContact04.isEmpty());
	}

	@Test (expected = IllegalArgumentException.class)
	public void getContactsInvalidIdsThrowsException() {
		testCM.getContacts(INVALID_ID_101, INVALID_ID_102, INVALID_ID_103);
	}
	
	//-----------------------------------More basic tests relating to Contacts methods---------------------------------

	@Test
	public void emptyCMcontainsNoContacts() {
		assertTrue(emptyCM.getContacts().isEmpty());
	}
	
	@Test
	public void emptyCMaddContacts() {
		emptyCM.addNewContact(CONTACT_NAME_01, CONTACT_NOTES_01);
		assertTrue(emptyCM.getContacts().size() == 1);
	}
	
	@Test
	public void emptyCMgetContactsbyNameReturnsNoContacts() {
		assertTrue(emptyCM.getContacts(CONTACT_NAME_02).isEmpty());
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void emptyCMgetContactsByIdReturnsNoContacts() {
		emptyCM.getContacts(CONTACT_ID_02);
	}
	
	//-------------------------FUTURE MEETINGS------------------------------------------------------------------------
	
	//-------------------------Test addFutureMeeting-------------------------------------------------------------------	
	
		@Test
		public void addFutureMeetingReturnsCorrectId() {
			assertTrue(testCM.addFutureMeeting(twoContactSet, FUTURE_DATE_01) == 1);
			assertTrue(testCM.addFutureMeeting(fiveContactSet, FUTURE_DATE_03) == 2);
		}
		
		@Test (expected = NullPointerException.class)
		public void addFutureMeetingNullContactsThrowsException() {
			testCM.addFutureMeeting(null, FUTURE_DATE_02);
		}
			
		@Test (expected = NullPointerException.class)
		public void addFutureMeetingNullDateThrowsException() {
			emptyCM.addFutureMeeting(twoContactSet, null);
			testCM.addFutureMeeting(twoContactSet, null);
		}
		
		@Test (expected = NullPointerException.class)
		public void addFutureMeetingNullFutureMeetingThrowsException() {
			testCM.addFutureMeeting(null, null);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void addFutureMeetingPastDateThrowsException() {
			testCM.addFutureMeeting(twoContactSet, PAST_DATE_03);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void addFutureMeetingInvalidContactsThrowsException() {
			testCM.addFutureMeeting(invalidContactSet, FUTURE_DATE_01);
		}
		
		//-------------------------Test getFutureMeeting-----------------------------------------------------------------
		
		/**
		 * The first test adds a future meeting and then checks it returns the correct meeting details
		 */
		@Test
		public void getFutureMeetingReturnsCorrectId() {
			
			assertTrue(testMeetingsCM.getFutureMeeting(MTG_ID_01).getId()==MTG_ID_01);
			assertTrue(testMeetingsCM.getFutureMeeting(MTG_ID_01).getDate()==FUTURE_DATE_01);
			assertTrue(testMeetingsCM.getFutureMeeting(MTG_ID_01).getContacts()==singleContact06);
		
			assertTrue(testMeetingsCM.getFutureMeeting(MTG_ID_02).getId()==MTG_ID_02);
			assertTrue(testMeetingsCM.getFutureMeeting(MTG_ID_02).getDate()==FUTURE_DATE_02);
			assertTrue(testMeetingsCM.getFutureMeeting(MTG_ID_02).getContacts()==threeContactSet);
			
			assertTrue(testMeetingsCM.getFutureMeeting(MTG_ID_04).getId()==MTG_ID_04);
			assertTrue(testMeetingsCM.getFutureMeeting(MTG_ID_04).getDate()==FUTURE_DATE_03);
			assertTrue(testMeetingsCM.getFutureMeeting(MTG_ID_04).getContacts()==singleContact06);
			
			assertTrue(testMeetingsCM.getFutureMeeting(MTG_ID_07).getId()==MTG_ID_07);
			assertTrue(testMeetingsCM.getFutureMeeting(MTG_ID_07).getDate()==FUTURE_DATE_04);
			assertTrue(testMeetingsCM.getFutureMeeting(MTG_ID_07).getContacts()==threeContactSet);
			
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void getFutureMeetingPastDateThrowsException() {
			testCM.addNewPastMeeting(twoContactSet, PAST_DATE_01, PAST_MTG_NOTES_01);
			testCM.getFutureMeeting(PAST_MTG_ID_01);
		}
		
		
		//--------------------------Test addNewPastMeeting ------------------------------------------------------
		
		/**
		 * Set up some past meetings to test
		 * Test creating a new PastMeeting. Note this returns void rather than int, but must still update the meeting Id
		 * None of the tests in this section will work unless getPastMeeting is implemented
		 */
		
		@Test
		public void addNewPastMeetingTest01() {
			testCM.addNewPastMeeting(singleContact04, PAST_DATE_01, PAST_MTG_NOTES_01);
			PastMeeting pastMeeting01 = testCM.getPastMeeting(PAST_MTG_ID_01);
			assertTrue(pastMeeting01.getId() == PAST_MTG_ID_01);
			assertTrue(pastMeeting01.getDate() == PAST_DATE_01);
			assertTrue(pastMeeting01.getContacts() == singleContact04);
			assertTrue(pastMeeting01.getNotes() == PAST_MTG_NOTES_01);
		}
		
		@Test
		public void addNewPastMeetingTest02() {
			testCM.addNewPastMeeting(singleContact04, PAST_DATE_01, PAST_MTG_NOTES_01);
			testCM.addNewPastMeeting(twoContactSet, PAST_DATE_02, PAST_MTG_NOTES_02);
			PastMeeting pastMeeting02 = testCM.getPastMeeting(PAST_MTG_ID_02);
			assertTrue(pastMeeting02.getId() == PAST_MTG_ID_02);
			assertTrue(pastMeeting02.getDate() == PAST_DATE_02);
			assertTrue(pastMeeting02.getContacts() == twoContactSet);
			assertTrue(pastMeeting02.getNotes() == PAST_MTG_NOTES_02);
		}
		
		@Test
		public void addNewPastMeetingTest03() {
			testCM.addNewPastMeeting(singleContact04, PAST_DATE_01, PAST_MTG_NOTES_01);
			testCM.addNewPastMeeting(twoContactSet, PAST_DATE_02, PAST_MTG_NOTES_02);
			testCM.addNewPastMeeting(fiveContactSet, PAST_DATE_03, PAST_MTG_NOTES_03);
			PastMeeting pastMeeting03 = testCM.getPastMeeting(PAST_MTG_ID_03);
			assertTrue(pastMeeting03.getId() == PAST_MTG_ID_03);
			assertTrue(pastMeeting03.getDate() == PAST_DATE_03);
			assertTrue(pastMeeting03.getContacts() == fiveContactSet);
			assertTrue(pastMeeting03.getNotes() == PAST_MTG_NOTES_03);
		}
		
		@Test (expected = IllegalArgumentException.class) 
		public void addNewPastMeetingFutureDateThrowsException() {
			testCM.addNewPastMeeting(singleContact04, FUTURE_DATE_01, PAST_MTG_NOTES_01);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void addNewPastMeetingEmptyContactsThrowsException() {
			testCM.addNewPastMeeting(emptyContactSet, PAST_DATE_01, PAST_MTG_NOTES_01);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void addNewPastMeetingInvalidContactsThrowsException() {
			testCM.addNewPastMeeting(invalidContactSet, PAST_DATE_02, PAST_MTG_NOTES_02);
		}
		
		@Test (expected = NullPointerException.class)
		public void addNewPastMeetingNullContactsThrowsException() {
			testCM.addNewPastMeeting(null, PAST_DATE_03, PAST_MTG_NOTES_03);
		}
		
		@Test (expected = NullPointerException.class)
		public void addNewPastMeetingNullDateThrowsException() {
			testCM.addNewPastMeeting(twoContactSet, null, PAST_MTG_NOTES_03);
		}
		
		@Test (expected = NullPointerException.class)
		public void addNewPastMeetingNullNotesThrowsException() {
			testCM.addNewPastMeeting(twoContactSet, PAST_DATE_01, null);
		}
		
		
		//--------------------------------Test getPastMeeting------------------------------------------------
		
		/**
		 * The first test adds a past meeting and then checks it returns the correct meeting details
		 * NB. addNewPastMeeting returns void
		 */
		@Test
		public void getPastMeetingReturnsCorrectMeeting() {
			assertTrue(testMeetingsCM.getPastMeeting(MTG_ID_03).getId()==MTG_ID_03);
			assertTrue(testMeetingsCM.getPastMeeting(MTG_ID_03).getDate()==PAST_DATE_01);
			assertTrue(testMeetingsCM.getPastMeeting(MTG_ID_03).getContacts()==singleContact06);
			assertTrue(testMeetingsCM.getPastMeeting(MTG_ID_03).getNotes()==PAST_MTG_NOTES_01);
			
			assertTrue(testMeetingsCM.getPastMeeting(MTG_ID_05).getId()==MTG_ID_05);
			assertTrue(testMeetingsCM.getPastMeeting(MTG_ID_05).getDate()==PAST_DATE_02);
			assertTrue(testMeetingsCM.getPastMeeting(MTG_ID_05).getContacts()==threeContactSet);
			assertTrue(testMeetingsCM.getPastMeeting(MTG_ID_05).getNotes()==PAST_MTG_NOTES_02);
			
			assertTrue(testMeetingsCM.getPastMeeting(MTG_ID_06).getId()==MTG_ID_06);
			assertTrue(testMeetingsCM.getPastMeeting(MTG_ID_06).getDate()==PAST_DATE_03);
			assertTrue(testMeetingsCM.getPastMeeting(MTG_ID_06).getContacts()==singleContact06);
			assertTrue(testMeetingsCM.getPastMeeting(MTG_ID_06).getNotes()==PAST_MTG_NOTES_03);
		
			assertTrue(testMeetingsCM.getPastMeeting(MTG_ID_08).getId()==MTG_ID_08);
			assertTrue(testMeetingsCM.getPastMeeting(MTG_ID_08).getDate()==PAST_DATE_04);
			assertTrue(testMeetingsCM.getPastMeeting(MTG_ID_08).getContacts()==threeContactSet);
			assertTrue(testMeetingsCM.getPastMeeting(MTG_ID_08).getNotes()==PAST_MTG_NOTES_04);
		}
		
		/**
		 * Check that this method throws IllegalStateException if there is a meeting with this Id 
		 * happening on a future date
		 * Could implement getMeeting(int id) and check with another test
		 */
		@Test (expected = IllegalStateException.class)
		public void getPastMeetingFutureDateThrowsException() {
			testCM.addFutureMeeting(twoContactSet, FUTURE_DATE_01);
			testCM.getPastMeeting(PAST_MTG_ID_01);
		}
		
		//--------------------------------Test getMeeting(int id)-----------------------------------------------
		
		/**
		 * The method getMeeting(int id) should return both PastMeetings and FutureMeetings.
		 * ids should be sequential
		 */
		@Test
		public void getMeetingReturnsCorrectMeeting() {
			
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_01).getId()==MTG_ID_01);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_02).getId()==MTG_ID_02);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_03).getId()==MTG_ID_03);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_04).getId()==MTG_ID_04);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_05).getId()==MTG_ID_05);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_06).getId()==MTG_ID_06);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_07).getId()==MTG_ID_07);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_08).getId()==MTG_ID_08);
					
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_01).getDate()==FUTURE_DATE_01);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_02).getDate()==FUTURE_DATE_02);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_03).getDate()==PAST_DATE_01);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_04).getDate()==FUTURE_DATE_03);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_05).getDate()==PAST_DATE_02);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_06).getDate()==PAST_DATE_03);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_07).getDate()==FUTURE_DATE_04);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_08).getDate()==PAST_DATE_04);
			
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_01).getContacts()==singleContact06);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_02).getContacts()==threeContactSet);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_03).getContacts()==singleContact06);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_04).getContacts()==singleContact06);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_05).getContacts()==threeContactSet);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_06).getContacts()==singleContact06);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_07).getContacts()==threeContactSet);
			assertTrue(testMeetingsCM.getMeeting(MTG_ID_08).getContacts()==threeContactSet);
			
			assertNull(testMeetingsCM.getMeeting(INVALID_MTG_ID_101));
			assertNull(testMeetingsCM.getMeeting(INVALID_MTG_ID_102));
		}
		
// ------------------------------Test getFutureMeetingList(Contact contact)----------------------------------
		
		/**
		 * The method returns a list of future meetings scheduled with this contact
		 * The list must be chronologically sorted (by meeting id or date?) and will not contain duplicates
		 * Question is whether we should avoid duplicates in the List, or avoid duplicate meetings being added in the first place
		 * At the moment duplicate meetings can be given a new Id
		 * Streams used to get a contact from Set<Contact> (getContacts(id)) return type is Set<Contact>, not contact
		 */
		
		@Test
		public void getFutureMeetingListReturnsCorrectListSize() {
			
			assertEquals(testMeetingsCM.getContacts("").size(),6);
			//CONTACT_01 should not appear
			//CONTACT_04 should appear x2, in MTG_ID_02 and MTG_ID_07
			//CONTACT_06 should appear x4, in meetings 1, 2, 4 and 7
			
			Contact Contact01 = testMeetingsCM.getContacts(CONTACT_ID_01).stream().findAny().get();
			Contact Contact04 = testMeetingsCM.getContacts(CONTACT_ID_04).stream().findAny().get();
			Contact Contact06 = testMeetingsCM.getContacts(CONTACT_ID_06).stream().findAny().get();

			assertEquals(testMeetingsCM.getFutureMeetingList(Contact01).size(),0);
			assertEquals(testMeetingsCM.getFutureMeetingList(Contact04).size(),2); // in threeContactSet
			assertEquals(testMeetingsCM.getFutureMeetingList(Contact06).size(),4); // in threeContactSet and singleContact06
		}
		
		/**
		 * I am interpreting 'chronologically sorted' as sorted by date, rather than by id
		 */
		@Test
		public void getFutureMeetingListReturnsSortedList() {
			
			assertTrue(testMeetingsCM.getContacts().size() == 6);
			
			Contact Contact04 = testMeetingsCM.getContacts(CONTACT_ID_04).stream().findAny().get();
			Contact Contact06 = testMeetingsCM.getContacts(CONTACT_ID_06).stream().findAny().get();
			
			assertTrue(testMeetingsCM.getFutureMeetingList(Contact04).get(0).getDate()==FUTURE_DATE_02);
			assertTrue(testMeetingsCM.getFutureMeetingList(Contact04).get(1).getDate()==FUTURE_DATE_04);
			
			assertTrue(testMeetingsCM.getFutureMeetingList(Contact06).get(0).getDate()==FUTURE_DATE_01);
			assertTrue(testMeetingsCM.getFutureMeetingList(Contact06).get(1).getDate()==FUTURE_DATE_02);
			assertTrue(testMeetingsCM.getFutureMeetingList(Contact06).get(2).getDate()==FUTURE_DATE_03);
			assertTrue(testMeetingsCM.getFutureMeetingList(Contact06).get(3).getDate()==FUTURE_DATE_04);
		}

		/**
		 * Contact04 is in the two future meetings which are duplicated
		 * Previous list size was 2, so should now be 3 with the duplicate meetings, although 2 have been added
		 * distinct() call within getFutureMeetingList(Contact contact) does not seem to be working ...
		 * WHY DOESN'T THIS WORK???
		 */
		@Test
		public void getFutureMeetingListContainsNoDuplicates() {
			
			addDuplicateMeetings(testMeetingsCM, threeContactSet);
			Contact Contact04 = testMeetingsCM.getContacts(CONTACT_ID_04).stream().findAny().get();
			
			assertTrue(testMeetingsCM.getFutureMeetingList(Contact04).get(0).getDate()==FUTURE_DATE_02);
			assertTrue(testMeetingsCM.getFutureMeetingList(Contact04).get(1).getDate()==FUTURE_DATE_04);
			assertTrue(testMeetingsCM.getFutureMeetingList(Contact04).get(2).getDate()==FUTURE_DATE_05);
			assertTrue(testMeetingsCM.getFutureMeetingList(Contact04).get(3).getDate()==FUTURE_DATE_05);
			
			assertEquals(testMeetingsCM.getFutureMeetingList(Contact04).get(2).getId(), 9);
			assertEquals(testMeetingsCM.getFutureMeetingList(Contact04).get(3).getId(), 10);
			
			assertEquals(testMeetingsCM.getFutureMeetingList(Contact04).size(),3); 
		}
		
		@Test (expected = NullPointerException.class) 
		public void getFutureMeetingListNullContactThrowsException() {
			testMeetingsCM.getFutureMeetingList(null);
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void getFutureMeetingListUnknownContactThrowsException() {
			testMeetingsCM.getFutureMeetingList(INVALID_CONTACT);
		}
		
}

