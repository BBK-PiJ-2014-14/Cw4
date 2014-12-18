package Cw4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Noam
 * An Implementation to PastMeeting interface.
 *
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
	private List<String> notes;
	
   
	/**
	 * Constructor
	 * @param date
	 * @param participant
	 */
	public PastMeetingImpl(Calendar date, Set<Contact> participant) {
		super(date, participant);
		this.notes = new ArrayList<String>(); 
	}

	/**
	 * This method return the notes from this meeting.
	 * @return notes from this meeting.
	 */
	@Override
	public String getNotes() {
		// TODO Auto-generated method stub
		return null;
	}

}
