package Cw4;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class PastMeetingImplTest {

	@Test
	public void testGetNotes() {
	    Set<Contact> contacts = new TreeSet<Contact>();
		ContactManager c = new ContactManagerImpl();
		Calendar date = Calendar.getInstance();
		c.addNewPastMeeting(contacts, date, "");
		assertEquals(c.getPastMeeting(1).getNotes(), ""); //Check with an empty string.
		c.addMeetingNotes(1, "This is the note");
		assertEquals(c.getPastMeeting(1).getNotes(),"[This is the note]" );//Check after adding note.
	}
}
