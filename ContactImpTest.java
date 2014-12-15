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
		fail("Not yet implemented");
	}

	@Test
	public void testAddNotes() {
		fail("Not yet implemented");
	}

}
