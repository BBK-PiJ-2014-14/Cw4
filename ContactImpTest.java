package Cw4;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContactImpTest {
	/*
	 * This class test  interface Contact.
	 */
	
    // Test for method getId().
	@Test
	public void testGetId() {
		Contact a = new ContactImp("Noam");
		Contact b = new ContactImp("Daniel");
		Contact c = new ContactImp("Noam");
		assertNotSame(a.getId(), b.getId());
		assertNotSame(a.getId(), c.getId());
	}

	// Test for method getName().
	@Test
	public void testGetName() {
		Contact a = new ContactImp("Noam");
		Contact b = new ContactImp("Daniel");
		Contact c = new ContactImp("");
		assertNotSame(a.getName(), b.getName());
		assertNotNull(a.getName());
		assertNotNull(c.getName());
	}

	// Test for method getNotes().
	@Test
	public void testGetNotes() {
		Contact a = new ContactImp("Noam");
		assertNotNull(a.getNotes());
		a.addNotes("This is me");
		String expected = "[This is me]";
		assertEquals(expected, a.getNotes());
	}

	@Test
	public void testAddNotes() {
		Contact a = new ContactImp("Noam");
		String expected = "";
		assertEquals(expected, a.getNotes());
		a.addNotes("The guy from MScCs");
		expected = "[The guy from MScCs]";
		assertEquals(expected, a.getNotes());
		a.addNotes("Must meet soon");	
		expected = "[The guy from MScCs][Must meet soon]";
		assertEquals(expected, a.getNotes());
	}

}
