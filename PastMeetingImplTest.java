package Cw4;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class PastMeetingImplTest {

	@Test
	public void testGetNotes() {
		Contact a = new ContactImpl("Noam");
		Calendar c = Calendar.getInstance();
		c.set(2015, 10, 15, 13, 30);
		Meeting m = new PastMeetingImpl(c, a);
		
		
		fail("Not yet implemented");
	}
}
