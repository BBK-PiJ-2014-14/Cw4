package Cw4;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

public class MeetingImpTest {
	Meeting first;
	Meeting second;
	Contact a;
	Contact b;
	

	/**
	 *  This method create instances of objects to be use on the tests.
	 */
	@Before
	public void CreateForTest() {
		a = new ContactImp("Noam");
		b = new ContactImp("Daniel");
		Calendar c = Calendar.getInstance();
		c.set(2015, 10, 1, 10, 50);
		Calendar d = Calendar.getInstance();
		d.set(2014, 12, 31, 23, 59);
		first = new MeetingImp(c, a);
		second = new MeetingImp(d, a, b);	
	}
	
    /**
    * Test for method getId(). 
	* Id needs to be unique so the test check that two meeting does not have the same ID. 
	*/
	@Test
	public void testGetId() {
		assertNotEquals(first.getId(), second.getId());
	}

	/** 
	 * Test for method getDate().
	 */
	@Test
	public void testGetDate() {
		Calendar expected = Calendar.getInstance();
		expected.set(2015, 10, 1, 10, 50);
		assertEquals(expected, first.getDate());
	}

	/**
	 * Test for method getContact().
	 */
	@Test
	public void testGetContacts() {
		Set<Contact> expected = new TreeSet<Contact>();
		try {
			expected.add(a);
			expected.add(b);
		} catch (ClassCastException e) {
			e.getStackTrace();
		}
		assertEquals(expected, second.getContacts());
		assertEquals(expected, first.getContacts());	
	}

}
