package Cw4;

import java.util.Calendar;

/**
 * 
 * @author Noam
 * An Implementation to PastMeeting interface.
 *
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
	
   
	/**
	 * Constructor
	 * @param date
	 * @param participant
	 */
	public PastMeetingImpl(Calendar date, Contact... participant) {
		super(date, participant);
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
