package Cw4;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class ContactManagerImplTest {
	ContactManagerImpl c =new ContactManagerImpl();
	Calendar date;
	Set<Contact> contacts;
	
	
	@Before
	public void createContactMagager() {
		c = new ContactManagerImpl();
		c.addNewContact("John", "First contact");
		c.addNewContact("Bob", "Second contact");
		contacts = c.getContacts("John");
		date = Calendar.getInstance();			
	}

	

	/**
	 * Test for method addFutureMeeting(Set<Contact> contacts, Calendar date).+
	 */
	@Test
	public void testAddFutureMeeting() {
		date.set(2015, 8, 8, 8, 00);
		int a = c.addFutureMeeting(contacts, date);
		assertEquals(c.getFutureMeeting(a).getContacts(), contacts); //Check that the meeting was added
		assertEquals(c.getFutureMeeting(a).getDate(), date);
		date.set(2015, 10, 15, 14, 00);
		int b = c.addFutureMeeting(contacts, date ); // Another meeting on a different date.
		assertEquals(c.getFutureMeeting(b).getContacts(), contacts); // the 2 meeting are with a so should be 2.
		assertEquals(c.getFutureMeeting(a).getDate(), date);
	}

	/**
	 * Test for method getPastMeeting(int iD).+
	 */
	@Test 
	public void testGetPastMeeting() {
		Object[] con = contacts.toArray();
		Contact john = (Contact)con[0];
		date.set(2012, 10, 15, 10, 00);
		c.addNewPastMeeting(contacts, date, "A new past meeting");
		int id = c.getPastMeetingList(john).get(0).getId();
		assertEquals(c.getPastMeeting(id).getDate(), date);
		assertEquals(c.getPastMeeting(id).getContacts(), contacts);
		assertEquals(c.getPastMeeting(id).getNotes(),"[A new past meeting]");
	}

	/**
	 * Test for method getFutureMeeting(int iD).+
	 */
	@Test
	public void testGetFutureMeeting() {
		assertNull(c.getFutureMeeting(50)); // check if return null for a non existing meeting.
		date.set(2015, 10, 15, 10, 00);
		int a = c.addFutureMeeting(contacts, date);
		assertEquals(c.getFutureMeeting(a).getContacts(), contacts);
		assertEquals(c.getFutureMeeting(a).getDate(), date);
		date.set(2016, 15, 10, 14, 00);
		int b = c.addFutureMeeting(contacts, date);
		assertEquals(c.getFutureMeeting(b).getContacts(), contacts);
		assertEquals(c.getFutureMeeting(b).getDate(), date);
	}

	/**
	 * Test for method getMeeting(int iD).+
	 */
	@Test
	public void testGetMeeting() {
		assertNull(c.getMeeting(10));
		date.set(2015, 10, 15, 10, 00);
		int futureId = c.addFutureMeeting(contacts, date); // Check if get future meeting
		assertEquals(c.getMeeting(futureId).getContacts(), contacts);
		assertEquals(c.getMeeting(futureId).getDate(), date);
		date.set(2012, 10, 15, 10, 00);
		c.addNewPastMeeting(contacts, date, "This is a past meeting"); // Check with past meeting
		Object[] con = contacts.toArray();
		Contact john = (Contact)con[0];
		int pastId = c.getPastMeetingList(john).get(0).getId();
		assertEquals(c.getMeeting(pastId).getContacts(), contacts);
		assertEquals(c.getMeeting(pastId).getDate(), date);
	}

	/**
	 * Test for method getFutureMeetingList(Contact contact).+
	 */
	@Test
	public void testGetFutureMeetingListContact() {
		Object[] con = contacts.toArray();
		Contact john = (Contact)con[0];
		assertTrue(c.getFutureMeetingList(john).isEmpty()); // check if return empty list if there are no meeting
		date.set(2015, 10, 15, 10, 00);
		c.addFutureMeeting(contacts, date);
		assertEquals(c.getFutureMeetingList(john).get(0).getContacts(), contacts); // check if return the meeting that added.
		assertEquals(c.getFutureMeetingList(john).get(0).getDate(), date);
		date.set(2016, 10, 15, 14, 00);
		c.addFutureMeeting(contacts, date);
		assertEquals(c.getFutureMeetingList(john).get(1).getContacts(), contacts);
		assertEquals(c.getFutureMeetingList(john).get(1).getDate(), date);
		assertEquals(c.getFutureMeetingList(john).size(), 2); // Check if the list contain both of the meetings.
	}

	/**
	 * Test for method getFutureMeetingList(Calendar date).+
	 */
	@Test
	public void testGetFutureMeetingListCalendar() {
		assertTrue(c.getFutureMeetingList(date).isEmpty()); // Check if return empty list when there are no meetings.
		date.set(2015, 10, 15, 14, 00);
		c.addFutureMeeting(contacts, date);
		assertEquals(c.getFutureMeetingList(date).get(0).getDate(), date); // Check if return the meeting that added.
		assertEquals(c.getFutureMeetingList(date).get(0).getContacts(), contacts);
		date.set(2015,  10, 15, 13, 00);
		c.addFutureMeeting(contacts, date); // Another meeting, same date but different contacts and different hour.
		assertEquals(c.getFutureMeetingList(date).get(0).getDate(), date);
		assertEquals(c.getFutureMeetingList(date).get(0).getContacts(), contacts);
		// index is 0 cause list should be returned in a chronological order.  
		assertEquals(c.getFutureMeetingList(date).size(), 2); // Check that the list still contain both meetings.
		Calendar newDate = Calendar.getInstance();
		newDate.set(2020, 10, 15, 15, 00);
		assertTrue(c.getFutureMeetingList(newDate).isEmpty()); // check if other dates are still empty.
	}

	/**
	 * Test for method getPastMeetingList(Contact contact).+
	 */
	@Test
	public void testGetPastMeetingList() {
		Object[] con = c.getContacts("John").toArray();
		Contact john = (Contact)con[0];
		date.set(2012, 10, 15, 10, 00);
		c.addNewPastMeeting(contacts, date, "This is the first past meeting");
		assertFalse(c.getPastMeetingList(john).isEmpty());
		assertEquals(c.getPastMeetingList(john).size(), 1);
		date.set(2013, 10, 15, 10, 00);
		c.addNewPastMeeting(contacts, date, "This is a nother meeting");
		assertEquals(c.getPastMeetingList(john).size(), 2);	
	}		


	/**
	 * Test for method addNewPastMeeting().+
	 */
	@Test
	public void testAddNewPastMeeting() {
		Object[] con = c.getContacts("John").toArray();
		Contact john = (Contact)con[0];
		date.set(2012, 10, 15, 10, 00);
		c.addNewPastMeeting(contacts, date, "This is the first past meeting");
		assertFalse(c.getPastMeetingList(john).isEmpty());
		assertEquals(c.getPastMeetingList(john).size(), 1);
		date.set(2013, 10, 15, 10, 00);
		c.addNewPastMeeting(contacts, date, "This is a nother meeting");
		assertEquals(c.getPastMeetingList(john).size(), 2);		
	}

	/**
	 * Test for method addMeetingNotes().+
	 */
	@Test
	public void testAddMeetingNotes() {
		int futureId = c.addFutureMeeting(contacts, date);
		date.set(2014, 10, 15, 15, 00);
		c.addMeetingNotes(futureId, "First meeting"); //now it should become a pastMeeting.
		Object [] con = contacts.toArray();
		Contact john = (Contact)con[0];
		int pastId = c.getPastMeetingList(john).get(0).getId();
		assertEquals(c.getPastMeeting(pastId).getNotes(),"[First meeting]");//check if the meeting is a pastMeeting.
		assertEquals(c.getPastMeeting(pastId).getDate(), date);
		assertEquals(c.getPastMeeting(pastId).getContacts(), contacts);
		c.addMeetingNotes(pastId, "Adding notes for an exsisting past meeting");
		assertEquals(c.getPastMeeting(pastId).getNotes(), "[First meeting][Adding notes for an exsisting past meeting]");	
	}

	/**
	 * Test for method addNewContact().+
	 */
	@Test
	public void testAddNewContact() {
		assertTrue(c.getContacts("Noam").isEmpty());
		c.addNewContact("Noam", " Noam is added");
		assertFalse(c.getContacts("Noam").isEmpty());
		c.addNewContact("Noam", "Another Noam");
		assertEquals(c.getContacts("Noam").size(), 2);
		c.addNewContact("Guy", "Someone else");
		assertEquals(c.getContacts("Noam").size(), 2);
		assertFalse(c.getContacts("Guy").isEmpty());
		assertTrue(c.getContacts("Don").isEmpty());
	}

	/**
	 * Test for method getContacts(int... iD).+
	 */
	@Test
	public void testGetContactsIntArray() {
		c.addNewContact("Noam", "This is Noam");
		Object[] con = c.getContacts("Noam").toArray();
		Contact n = (Contact)con[0];
		int noamId = n.getId();
		assertEquals(c.getContacts(noamId), c.getContacts("Noam"));
		c.addNewContact("Bob", "This is Bob");
		con = c.getContacts("Bob").toArray();
		n = (Contact)con[0];
		int bobId = n.getId();
		assertEquals(c.getContacts(noamId, bobId).size(), 2);
		c.getContacts(noamId, bobId).containsAll(c.getContacts("Noam"));
		c.getContacts(noamId, bobId).containsAll(c.getContacts("Bob"));
		
		
		//assertEquals(c.getContacts(1, 2).size(), 2);
		//assertEquals(c.getContacts(3, 4).size(), 2); // Check with more then one ID.
	}

	/**
	 * Test for method getContacts(String name).+
	 */
	@Test
	public void testGetContactsString() {
		assertTrue(c.getContacts("Don").isEmpty());
		c.addNewContact("Don", "this is Don");
		assertEquals(c.getContacts("Don").size(), 1);
		c.addNewContact("Don", "This is another Don");
		assertEquals(c.getContacts("Don").size(), 2);
		assertTrue(c.getContacts("Noam").isEmpty());
		c.addNewContact("Noam", "this is Noam");
		assertEquals(c.getContacts("Noam").size(), 1); //Check if return to different contacts if the name is the same.
	}


	@Test
	public void testFlush() {
		fail("Not yet implemented");
	}

}
