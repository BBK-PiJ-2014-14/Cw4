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
	 * Test for method addFutureMeeting().
	 */
	@Test
	public void testAddFutureMeeting() {
		assertTrue(c.getFutureMeetingList(a).isEmpty());
		contacts.add(a);
		date.set(2015, 1, 31, 10, 00);
		c.addFutureMeeting(contacts, date);
		assertFalse(c.getFutureMeetingList(a).isEmpty());
		Set<Contact> anotherContacts = new TreeSet<Contact>();
		anotherContacts.add(a);
		anotherContacts.add(b);
		c.addFutureMeeting(anotherContacts, date );
		int expected = 2;
		assertEquals(expected, c.getFutureMeetingList(date).size());
	}

	/**
	 * Test for method getMeeting(int iD).
	 */
	@Test
	public void testGetPastMeeting() {
		assertNull(c.getPastMeeting(5));
		c.addNewPastMeeting(contacts, date, "this is the first meeting"); // iD is 1.
		assertEquals(c.getPastMeeting(1).getContacts(), contacts);
		assertEquals(c.getPastMeeting(1).getDate(), date);
	}

	/**
	 * Test for method getFutureMeeting(int iD).
	 */
	@Test
	public void testGetFutureMeeting() {
		contacts.add(a);
		assertNull(c.getFutureMeeting(3));
		c.addFutureMeeting(contacts, date);
		assertTrue(c.getFutureMeeting(1).getContacts().contains(a));
		assertEquals(date, c.getFutureMeeting(1).getDate());
	}

	/**
	 * Test for method getMeeting(int iD).
	 */
	@Test
	public void testGetMeeting() {
		assertNull(c.getMeeting(5));
		c.addFutureMeeting(contacts, date); // This is the first meeting so the id will be 1.
		assertEquals(c.getMeeting(1).getContacts(), contacts);
		assertEquals(c.getMeeting(1).getDate(), date);
		c.addNewPastMeeting(contacts, date, "This is another meeting, id would be 2");
		assertEquals(c.getMeeting(2).getContacts(), contacts);
		assertEquals(c.getPastMeeting(2).getDate(), date);
	}

	/**
	 * Test for method getFutureMeetingList(Contact contact).
	 */
	@Test
	public void testGetFutureMeetingListContact() {
		assertTrue(c.getFutureMeetingList(a).isEmpty());
		contacts.add(a);
		c.addFutureMeeting(contacts, date);
		assertEquals(c.getFutureMeetingList(a).get(0).getContacts(), a);
		assertEquals(c.getFutureMeetingList(a).get(0).getDate(), date);
	}

	/**
	 * Test for method getFutureMeetingList(Calendar date)
	 */
	@Test
	public void testGetFutureMeetingListCalendar() {
		assertTrue(c.getFutureMeetingList(date).isEmpty());
		date.set(2015, 2, 2, 2, 00);
		c.addFutureMeeting(contacts, date);
		assertEquals(c.getFutureMeetingList(date).get(0).getDate(), date);
		assertEquals(c.getFutureMeetingList(date).size(), 1);
		date.set(2015, 2, 2, 3, 00);
		c.addFutureMeeting(contacts, date);
		assertEquals(c.getFutureMeetingList(date).size(), 2);
		assertEquals(c.getFutureMeetingList(date).get(1).getDate(), date); // check if the order is correct
	}

	/**
	 * Test for method getPastMeetingList().
	 */
	@Test
	public void testGetPastMeetingList() {
		assertTrue(c.getPastMeetingList(a).isEmpty());
		contacts.add(a);
		c.addNewPastMeeting(contacts, date, "This is the first meeting");
		assertEquals(c.getPastMeetingList(a).get(0).getContacts(), a);
		assertEquals(c.getPastMeetingList(a).get(0).getDate(), date);
	}

	/**
	 * Test for method addNewPastMeeting().
	 */
	@Test
	public void testAddNewPastMeeting() {
		assertTrue(c.getPastMeetingList(a).isEmpty());
		contacts.add(a);
		c.addNewPastMeeting(contacts, date, "This is the first past meeting");
		int expected = 1;
		assertEquals(expected, c.getPastMeetingList(a));
		date.set(2015, 1, 29, 15, 00);
		c.addNewPastMeeting(contacts, date, "This is another past meeting");
		expected = 2;
		assertEquals(expected, c.getPastMeetingList(a));
	
	}

	@Test
	public void testAddMeetingNotes() {
		fail("Not yet implemented");
	}

	/**
	 * Test for method addNewContact().
	 */
	@Test
	public void testAddNewContact() {
		assertTrue(c.getContacts(1).isEmpty());
		c.addNewContact("John", "First contact");
		int expected = 1;
		assertEquals(expected, c.getContacts(1).size());
		c.addNewContact("Bob", "Second contact");
		expected = 2;
		assertEquals(expected, c.getContacts(1, 2));
	}

	/**
	 * Test for method getContacts(int... iD).
	 */
	@Test
	public void testGetContactsIntArray() {
		assertNull(c.getContacts(2));
		c.addNewContact("John", "This is John and he is the first contact");
		assertEquals(c.getContacts("John"), c.getContacts(1));
		c.addNewContact("Bob", "This is Bob and he is the second");
		int expected = 2;
		assertEquals(expected, c.getContacts(1, 2).size());
	}

	/**
	 * Test for method getContacts(String name).
	 */
	@Test
	public void testGetContactsString() {
		assertTrue(c.getContacts("John").isEmpty()); 
		c.addNewContact("John", "this is John");
		int expected = 1;
		assertEquals(expected, c.getContacts("John").size());
		c.addNewContact("John", "and this is another John");
		expected = 2;
		assertEquals(expected, c.getContacts("John"));
	}

	@Test
	public void testFlush() {
		fail("Not yet implemented");
	}

}
