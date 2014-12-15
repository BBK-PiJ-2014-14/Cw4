package Cw4;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Noam
 *This class implements the interface Meeting. 
 *It has a static field meetingNum which produce a unique ID for each Meeting.
 *It has also other fields as required by the interface. 
 */
public class MeetingImp implements Meeting {
	private static int meetingNum = 1;
	private int iD;
	private Calendar date;
	private Contact[] participents;
	
	
	// Constructor
	public MeetingImp(Calendar date, Contact... participent) {
		this.iD = meetingNum;
		meetingNum++;
		this.date = date;
		this.participents = participent;	
		
	}
	



	
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public Calendar getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Set<Contact> getContacts() {
		// TODO Auto-generated method stub
		return null;
	}

}
