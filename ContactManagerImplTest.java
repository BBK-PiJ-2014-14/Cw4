package Cw4;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class ContactManagerImplTest {
	ContactManagerImpl c =new ContactManagerImpl();
	Calendar date;
	Calendar newDate;
	Set<Contact> contacts1;
	Set<Contact> contacts2;
	Contact john;
	Contact bob;
	
	
	@Before
	public void createContactMagager() {
		c = new ContactManagerImpl();
		c.addNewContact("John", "First contact");
		c.addNewContact("Bob", "Second contact");
		contacts1 = c.getContacts("John");
		contacts2 = c.getContacts("Bob");
		date = Calendar.getInstance();	
		newDate = Calendar.getInstance();
		Object [] con = contacts1.toArray();
		john = (Contact)con[0];
		con = contacts2.toArray();
		bob = (Contact)con[0];
	}
	
	/**
	 * Test for method addFutureMeeting(Set<Contact> contacts, Calendar date).
	 */
	@Test
	public void testAddFutureMeeting() {
		date.set(2015, 8, 8, 8, 00);
		int a = c.addFutureMeeting(contacts1, date);
		assertEquals(c.getFutureMeeting(a).getContacts(), contacts1);
		assertEquals(c.getFutureMeeting(a).getDate(), date);
		newDate.set(2015, 10, 15, 14, 00);
		int b = c.addFutureMeeting(contacts2, newDate ); // Another meeting.
		assertEquals(c.getFutureMeeting(b).getContacts(), contacts2);
		assertEquals(c.getFutureMeeting(b).getDate(), newDate);
		
	}

	/**
	 * Test for method getPastMeeting(int iD).
	 */
	@Test 
	public void testGetPastMeeting() {
		date.set(2012, 10, 15, 10, 00);
		c.addNewPastMeeting(contacts1, date, "A new past meeting");
		int id = c.getPastMeetingList(john).get(0).getId();
		assertEquals(c.getPastMeeting(id).getDate(), date);
		assertEquals(c.getPastMeeting(id).getContacts(), contacts1);
		assertEquals(c.getPastMeeting(id).getNotes(),"[A new past meeting]");
		newDate.set(2013, 10, 15, 10, 00);
		c.addNewPastMeeting(contacts2, newDate, "Another past meeting");
		id = c.getPastMeetingList(bob).get(0).getId();
		assertEquals(c.getPastMeeting(id).getDate(), newDate);
		assertEquals(c.getPastMeeting(id).getContacts(), contacts2);
		assertEquals(c.getPastMeeting(id).getNotes(),"[Another past meeting]");
	}

	/**
	 * Test for method getFutureMeeting(int iD).
	 */
	@Test
	public void testGetFutureMeeting() {
		assertNull(c.getFutureMeeting(50)); // check if return null for a non existing meeting.
		date.set(2015, 10, 15, 10, 00);
		int id1 = c.addFutureMeeting(contacts1, date);
		assertEquals(c.getFutureMeeting(id1).getContacts(), contacts1);
		assertEquals(c.getFutureMeeting(id1).getDate(), date);
		newDate.set(2016, 15, 10, 14, 00);
		int id2 = c.addFutureMeeting(contacts1, newDate);
		assertEquals(c.getFutureMeeting(id2).getContacts(), contacts1);
		assertEquals(c.getFutureMeeting(id2).getDate(), newDate);
	}

	/**
	 * Test for method getMeeting(int iD).
	 */
	@Test
	public void testGetMeeting() {
		assertNull(c.getMeeting(50));
		date.set(2015, 10, 15, 10, 00);
		int futureId = c.addFutureMeeting(contacts1, date); // Check if get future meeting
		assertEquals(c.getMeeting(futureId).getContacts(), contacts1);
		assertEquals(c.getMeeting(futureId).getDate(), date);
		newDate.set(2012, 10, 15, 10, 00);
		c.addNewPastMeeting(contacts1, newDate, "This is a past meeting"); // Check with past meeting
		int pastId = c.getPastMeetingList(john).get(0).getId();
		assertEquals(c.getMeeting(pastId).getContacts(), contacts1);
		assertEquals(c.getMeeting(pastId).getDate(), newDate);
	}

	/**
	 * Test for method getFutureMeetingList(Contact contact).
	 */
	@Test
	public void testGetFutureMeetingListContact() {
		assertTrue(c.getFutureMeetingList(john).isEmpty()); // check if return empty list if there are no meeting
		date.set(2016, 10, 15, 10, 00);
		c.addFutureMeeting(contacts1, date);
		assertEquals(c.getFutureMeetingList(john).get(0).getContacts(), contacts1); // check if return the meeting that added.
		assertEquals(c.getFutureMeetingList(john).get(0).getDate(), date);
		newDate.set(2015, 10, 15, 14, 00);
		c.addFutureMeeting(contacts1, newDate);
		assertEquals(c.getFutureMeetingList(john).get(0).getContacts(), contacts1);// index is 0 to check if the contacts are sorted.
		assertEquals(c.getFutureMeetingList(john).get(0).getDate(), newDate);
		assertEquals(c.getFutureMeetingList(john).size(), 2); // Check if the list contain both of the meetings.
	}

	/**
	 * Test for method getFutureMeetingList(Calendar date).
	 */
	@Test
	public void testGetFutureMeetingListCalendar() {
		assertTrue(c.getFutureMeetingList(date).isEmpty()); // Check if return empty list when there are no meetings.
		date.set(2015, 10, 15, 14, 00);
		c.addFutureMeeting(contacts1, date);
		assertEquals(c.getFutureMeetingList(date).get(0).getDate(), date); // Check if return the meeting that added.
		assertEquals(c.getFutureMeetingList(date).get(0).getContacts(), contacts1);
		c.addFutureMeeting(contacts2, date); // Another meeting, same date but different contacts and different hour.
		assertEquals(c.getFutureMeetingList(date).get(1).getDate(), date);
		assertEquals(c.getFutureMeetingList(date).get(1).getContacts(), contacts2);  
		assertEquals(c.getFutureMeetingList(date).size(), 2); // Check that the list still contain both meetings.
		newDate.set(2020, 10, 15, 15, 00);
		assertTrue(c.getFutureMeetingList(newDate).isEmpty()); // check if other dates are still empty.
	}

	/**
	 * Test for method getPastMeetingList(Contact contact).
	 */
	@Test
	public void testGetPastMeetingList() {
		date.set(2012, 10, 15, 10, 00);
		c.addNewPastMeeting(contacts1, date, "This is the first past meeting");
		assertFalse(c.getPastMeetingList(john).isEmpty());
		assertEquals(c.getPastMeetingList(john).size(), 1);
		newDate.set(2013, 10, 15, 10, 00);
		c.addNewPastMeeting(contacts1, newDate, "This is another meeting");
		assertEquals(c.getPastMeetingList(john).size(), 2);	
	}		


	/**
	 * Test for method addNewPastMeeting().
	 */
	@Test
	public void testAddNewPastMeeting() {
		date.set(2012, 10, 15, 10, 00);
		c.addNewPastMeeting(contacts1, date, "This is the first past meeting");
		assertFalse(c.getPastMeetingList(john).isEmpty());
		assertEquals(c.getPastMeetingList(john).size(), 1);
		newDate.set(2013, 10, 15, 10, 00);
		c.addNewPastMeeting(contacts1, newDate, "This is a nother meeting");
		assertEquals(c.getPastMeetingList(john).size(), 2);		
	}

	/**
	 * Test for method addMeetingNotes().
	 */
	@Test
	public void testAddMeetingNotes() {
		int futureId = c.addFutureMeeting(contacts1, date);
		date.set(2014, 10, 15, 15, 00);
		c.addMeetingNotes(futureId, "First meeting"); //now it should become a pastMeeting.
		int pastId = c.getPastMeetingList(john).get(0).getId();
		assertEquals(c.getPastMeeting(pastId).getNotes(),"[First meeting]");//check if the meeting is a pastMeeting.
		assertEquals(c.getPastMeeting(pastId).getDate(), date);
		assertEquals(c.getPastMeeting(pastId).getContacts(), contacts1);
		c.addMeetingNotes(pastId, "Adding notes for an exsisting past meeting");// Adding notes to an existing pastMeeting.
		assertEquals(c.getPastMeeting(pastId).getNotes(), "[First meeting][Adding notes for an exsisting past meeting]");	
	}

	/**
	 * Test for method addNewContact().
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
	 * Test for method getContacts(int... iD).
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
	}

	/**
	 * Test for method getContacts(String name).
	 */
	@Test
	public void testGetContactsString() {
		assertTrue(c.getContacts("Don").isEmpty());
		c.addNewContact("Don", "this is Don");
		assertEquals(c.getContacts("Don").size(), 1);
		c.addNewContact("Don", "This is another Don");
		assertEquals(c.getContacts("Don").size(), 2); //Check if return to different contacts if the name is the same.
		assertTrue(c.getContacts("Noam").isEmpty());
		c.addNewContact("Noam", "this is Noam");
		assertEquals(c.getContacts("Noam").size(), 1); 
	}


	/**
	 * Test for method flush().
	 */
	@Test
	public void testFlush() {
		date.set(2015, 10, 15, 10, 00);
		int id = c.addFutureMeeting(contacts1, date);
		c.flush();
		 ObjectInputStream in = null;
	        try {
	            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("cmInfo.txt")));
				@SuppressWarnings("unchecked")
				List<Contact> con = (List<Contact>) in.readObject();
	            @SuppressWarnings("unchecked")
				List<Meeting> past = (List<Meeting>) in.readObject();
	            @SuppressWarnings("unchecked")
				List<Meeting> future = (List<Meeting>) in.readObject();
	            ContactManagerImpl d = new ContactManagerImpl(future, past, con);
	            Object[] co = d.getFutureMeeting(id).getContacts().toArray();
	            Contact conD = (Contact)co[0]; // this should be the contact that participate in the meeting.
	            int jd =john.getId(); // contact id.
	            Object[] o = d.getContacts(jd).toArray();
	            Contact johnD = (Contact)o[0]; // This is the contact from the contact manager list of of contact.
	            assertEquals(c.getFutureMeeting(id).getDate(), d.getFutureMeeting(id).getDate()); // checks if the same meeting was saved.
	            assertEquals(john.getId(), conD.getId());
	            assertEquals(john.getName(), conD.getName());
	            assertEquals(c.getFutureMeeting(id).getContacts().size(), d.getFutureMeeting(id).getContacts().size()); //checks if the contacts are the same.
	            assertEquals(john.getName(), johnD.getName()); 
	            assertEquals(john.getNotes(), johnD.getNotes());
	        } catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
	        finally {	
	        	try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	}
	
	/**
	 * Test for comparator dateComp.
	 */
	@Test
	public void testDateComp() {
		date.set(2015, 10, 15, 10, 00);
		newDate.set(2016, 10, 15, 10, 00);
		int id = c.addFutureMeeting(contacts1, date);
		Meeting first = c.getMeeting(id);
		id = c.addFutureMeeting(contacts2, newDate);
		Meeting second = c.getMeeting(id);
		Meeting sameAsSecond = c.getMeeting(id);
		assertEquals(c.dateComp.compare(first, second),-1);
		assertEquals(c.dateComp.compare(second, sameAsSecond), 0);
		assertEquals(c.dateComp.compare(sameAsSecond, first), 1);
		
	}

}
