package Cw4;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class PastMeetingImplTest {
	
	/**
	 * This test check both getNotes() and addNotes() methods.
	 */

	@Test
	public void testGetNotes() {
		Calendar date = Calendar.getInstance();
		Set<Contact> contacts = new HashSet<Contact>();
		PastMeetingImpl newPast = new PastMeetingImpl(date, contacts);
		assertEquals(newPast.getNotes(),""); // Check if return empty string if there are no notes.
		newPast.addNotes("This is a note for past meeting");
		String expected1 = "[This is a note for past meeting]";
		assertEquals(newPast.getNotes(), expected1); // Check with one note.
		newPast.addNotes("This is another note");
		String expected2 = "[This is a note for past meeting][This is another note]";
		assertEquals(newPast.getNotes(), expected2);// Check with two notes.
	}
}
