package Cw4;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

public class ContactManagerImplTest {
	ContactManager c;
	Contact a;
	Contact b;
	Calendar date;
	Set<Contact> contacts;
	
	
	@Before
	public void createContactMagager() {
		c = new ContactManagerImpl();
		a = new ContactImpl("Noam");
		b = new ContactImpl("Daniel");
		contacts = new TreeSet<Contact>();
		date = Calendar.getInstance();			
	}
	

	/**
	 * Test for method addFutureMeeting(Set<Contact> contacts, Calendar date).
	 */
	@Test
	public void testAddFutureMeeting() {
		contacts.add(a);
		c.addFutureMeeting(contacts, date);
		assertFalse(c.getFutureMeetingList(a).isEmpty()); //Check that the meeting was added
		date.set(2015, 10, 15, 14, 00);
		c.addFutureMeeting(contacts, date ); // Another meeting on a different date.
		assertEquals(2, c.getFutureMeetingList(a).size()); // the 2 meeting are with a so should be 2.
	}

	/**
	 * Test for method getPastMeeting(int iD).
	 */
	@Test
	public void testGetPastMeeting() {
		assertNull(c.getPastMeeting(5)); // check if return null for a non existing meeting.
		c.addNewPastMeeting(contacts, date, "this is the first meeting"); // iD is 1.
		assertEquals(c.getPastMeeting(1).getContacts(), contacts);
		assertEquals(c.getPastMeeting(1).getDate(), date);
		assertEquals(c.getPastMeeting(1).getNotes(),"[this is the first meeting]");
		date.set(2015, 15, 10, 4, 00);
		contacts.add(a);
		c.addNewPastMeeting(contacts, date, "this is another meeting"); // Another meeting, parameters changed.
		assertEquals(c.getPastMeeting(2).getContacts(), contacts); // Second so ID is 2.
		assertEquals(c.getPastMeeting(2).getDate(), date);
		assertEquals(c.getPastMeeting(1).getNotes(),"[this is another meeting]");
	}

	/**
	 * Test for method getFutureMeeting(int iD).
	 */
	@Test
	public void testGetFutureMeeting() {
		assertNull(c.getFutureMeeting(3)); // check if return null for a non existing meeting.
		c.addFutureMeeting(contacts, date);
		assertEquals(c.getFutureMeeting(1).getContacts(), contacts);
		assertEquals(c.getFutureMeeting(1).getDate(), date);
		contacts.add(a);
		date.set(2015, 15, 10, 14, 00);
		c.addFutureMeeting(contacts, date);
		assertEquals(c.getFutureMeeting(2).getContacts(), contacts);
		assertEquals(c.getFutureMeeting(2).getDate(), date);
	}

	/**
	 * Test for method getMeeting(int iD).
	 */
	@Test
	public void testGetMeeting() {
		assertNull(c.getMeeting(5));
		c.addFutureMeeting(contacts, date); // Check if get future meeting
		assertEquals(c.getMeeting(1).getContacts(), contacts);
		assertEquals(c.getMeeting(1).getDate(), date);
		c.addNewPastMeeting(contacts, date, "This is another meeting, id would be 2"); // Check with past meeting
		assertEquals(c.getMeeting(2).getContacts(), contacts);
		assertEquals(c.getPastMeeting(2).getDate(), date);
	}

	/**
	 * Test for method getFutureMeetingList(Contact contact).
	 */
	@Test
	public void testGetFutureMeetingListContact() {
		assertTrue(c.getFutureMeetingList(a).isEmpty()); // check if return empty list if there are no meeting
		contacts.add(a);
		c.addFutureMeeting(contacts, date);
		assertEquals(c.getFutureMeetingList(a).get(0).getContacts(), contacts); // check if return the meeting that added.
		assertEquals(c.getFutureMeetingList(a).get(0).getDate(), date);
		date.set(2015, 10, 15, 14, 00);
		c.addFutureMeeting(contacts, date);
		assertEquals(c.getFutureMeetingList(a).get(0).getContacts(), contacts);
		assertEquals(c.getFutureMeetingList(a).get(0).getDate(), date);
		assertEquals(c.getFutureMeetingList(a).size(), 2); // Check if the list contain both of the meetings.
		assertTrue(c.getFutureMeetingList(b).isEmpty()); // Check that not return same for every contact.
	}

	/**
	 * Test for method getFutureMeetingList(Calendar date).
	 */
	@Test
	public void testGetFutureMeetingListCalendar() {
		assertTrue(c.getFutureMeetingList(date).isEmpty()); // Check if return empty list when there are no meetings.
		date.set(2015, 10, 15, 14, 00);
		c.addFutureMeeting(contacts, date);
		assertEquals(c.getFutureMeetingList(date).get(0).getDate(), date); // Check if return the meeting that added.
		assertEquals(c.getFutureMeetingList(date).get(0).getContacts(), contacts);
		contacts.add(a);
		date.set(2015,  10, 15, 13, 00);
		c.addFutureMeeting(contacts, date); // Another meeting, same date but different contacts and different hour.
		assertEquals(c.getFutureMeetingList(date).get(0).getDate(), date);
		assertEquals(c.getFutureMeetingList(date).get(0).getContacts(), contacts);
		// index is 0 cause list should be returned in a chronological order.  
		assertEquals(c.getFutureMeetingList(date).size(), 2); // Check that the list still contain both meetings.
		date.set(2015,  10, 15, 14, 00);
		assertTrue(c.getFutureMeetingList(date).isEmpty()); // check if other dates are still empty.
	}

	/**
	 * Test for method getPastMeetingList().
	 */
	@Test
	public void testGetPastMeetingList() {
		assertTrue(c.getPastMeetingList(a).isEmpty());
		contacts.add(a);
		date.set(2012,10, 15, 14, 00);
		c.addNewPastMeeting(contacts, date, "This is the first meeting");
		assertTrue(c.getPastMeetingList(a).get(0).getContacts().contains(a));
		assertEquals(c.getPastMeetingList(a).get(0).getDate(), date);
		assertEquals(c.getPastMeetingList(a).get(0).getNotes(),"[This is the first meeting]");
		date.set(2010, 10, 15, 14, 00);
		c.addNewPastMeeting(contacts, date, "Another meeting with a"); // On a different date.
		assertTrue(c.getPastMeetingList(a).get(0).getContacts().contains(a));
		assertEquals(c.getPastMeetingList(a).get(0).getDate(), date);
		assertEquals(c.getPastMeetingList(a).get(0).getNotes(),"[Another meeting with a]");
		// index is again 0 cause list should be in a chronological order.
		assertEquals(c.getPastMeetingList(a).size(), 2); // Check if both meetings are there.
		assertTrue(c.getPastMeetingList(b).isEmpty()); // Check if still return empty for a contact with no meetings.
	}

	/**
	 * Test for method addNewPastMeeting().
	 */
	@Test
	public void testAddNewPastMeeting() {
		c.addNewPastMeeting(contacts, date, "This is the first past meeting");
		assertEquals(c.getPastMeeting(1).getDate(), date); // Check if the right date was added.
		assertEquals(c.getPastMeeting(1).getContacts(), contacts);	//Check if the right contacts were added.
		assertEquals(c.getPastMeeting(1).getNotes(), "[This is the first past meeting]");// Check if the notes were added.
		date.set(2012, 10, 15, 14, 00);
		contacts.add(a);
		c.addNewPastMeeting(contacts, date, "This is another past meeting");//Another meeting, different parameters
		assertEquals(c.getPastMeeting(1).getDate(), date); 
		assertEquals(c.getPastMeeting(1).getContacts(), contacts);	
		assertEquals(c.getPastMeeting(1).getNotes(), "[This is another past meeting]");
	}

	/**
	 * Test for method addMeetingNotes().
	 */
	@Test
	public void testAddMeetingNotes() {
		c.addFutureMeeting(contacts, date);
		c.addMeetingNotes(1, "First meeting"); //now it should become a pastMeeting.
		assertEquals(c.getPastMeeting(1).getNotes(),"[First meeting]");//check if the meeting is a pastMeeting.
		assertEquals(c.getPastMeeting(1).getDate(), date);
		assertEquals(c.getPastMeeting(1).getContacts(), contacts);
		c.addMeetingNotes(1, "Adding notes for an exsisting past meeting");
		assertEquals(c.getPastMeeting(1).getNotes(), "[First meeting][Adding notes for an exsisting past meeting]");
	}

	/**
	 * Test for method addNewContact().
	 */
	@Test
	public void testAddNewContact() {
		c.addNewContact("John", "First contact");
		assertEquals(c.getContacts(1).size(), 1);
		assertEquals(c.getContacts("John").size(), 1);
		c.addNewContact("Bob", "Second contact");
		assertEquals(c.getContacts(1).size(), 2);
	}

	/**
	 * Test for method getContacts(int... iD).
	 */
	@Test
	public void testGetContactsIntArray() {
		c.addNewContact("John", "This is John and he is the first contact");
		assertEquals(c.getContacts("John"), c.getContacts(1));
		c.addNewContact("Bob", "This is Bob and he is the second");
		assertEquals(c.getContacts("Bob"), c.getContacts(2));
		assertEquals(c.getContacts(1, 2).size(), 2); // Check with more then one ID.
	}

	/**
	 * Test for method getContacts(String name).
	 */
	@Test
	public void testGetContactsString() {
		assertTrue(c.getContacts("John").isEmpty()); 
		c.addNewContact("John", "this is John");
		assertEquals(c.getContacts("John"), c.getContacts(1));
		c.addNewContact("Bob", "and this is another John");
		assertEquals(c.getContacts("Bob"), c.getContacts(2));
		c.addNewContact("John", "this is Another John");
		assertEquals(c.getContacts("John").size(), 2); //Check if return to different contacts if the name is the same.
	}


	@Test
	public void testFlush() {
		fail("Not yet implemented");
	}

}
