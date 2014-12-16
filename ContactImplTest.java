package Cw4;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author Noam
 * Test Class for class ContactImp.
 *
 */
public class ContactImplTest {
	
    /** 
     * Test for method getId().
     */
	@Test
	public void testGetId() {
		Contact a = new ContactImpl("Noam");
		Contact b = new ContactImpl("Daniel");
		Contact c = new ContactImpl("Noam");
		assertNotSame(a.getId(), b.getId());
		assertNotSame(a.getId(), c.getId());
	}

	/** 
	 * Test for method getName().
	 */
	@Test
	public void testGetName() {
		Contact a = new ContactImpl("Noam");
		Contact b = new ContactImpl("Daniel");
		Contact c = new ContactImpl("");
		assertNotSame(a.getName(), b.getName());
		assertNotNull(a.getName());
		assertNotNull(c.getName());
	}

	/** 
	 * Test for method getNotes().
	 */
	@Test
	public void testGetNotes() {
		Contact a = new ContactImpl("Noam");
		assertNotNull(a.getNotes());
		a.addNotes("This is me");
		String expected = "[This is me]";
		assertEquals(expected, a.getNotes());
	}

	/** 
	 * Test for method addNotes().
	 */
	@Test
	public void testAddNotes() {
		Contact a = new ContactImpl("Noam");
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
