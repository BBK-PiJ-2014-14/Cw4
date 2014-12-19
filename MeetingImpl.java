package Cw4;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Noam
 *This class implements the interface Meeting. 
 *It has a static field meetingNum which produce a unique ID for each Meeting.
 *It has a String field note to keep notes about the meeting.
 *It has also other fields as required by the interface. 
 */
public class MeetingImpl implements Meeting {
	private static int meetingNum = 1;
	private int iD;
	private Calendar date;
	private List<Contact> participants;
	
	
	
	/** 
	 * Constructor
	 * @param date
	 * @param participant
	 */
	public MeetingImpl(Calendar date, List<Contact> participant) {
		this.iD = meetingNum;
		meetingNum++;
		this.date = date;
		this.participants = participant;		
	}
	
	/**
	 * This method return the ID of the meeting.
	 * @return the meeting's ID.
	 */
	@Override
	public int getId() {
		return iD;
	}

	/**
	 * This method return A calendar object set to the date of the meeting.
	 * @return the calendar of the meeting.
	 */
	@Override
	public Calendar getDate() {
		return date;
	}

	/**
	 * This method return a set of contact participating in this meeting.
	 * @return a set of contact participating in this meeting.
	 */
	@Override
	public Set<Contact> getContacts() {
		Set<Contact> result = new HashSet<Contact>();
		for(int i=0; i<participants.size(); i++) {
			result.add(participants.get(i));
		}
		return result;
	}

}
