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
	private static final long serialVersionUID = 987813508180475605L;
	private List<String> notes;
	
   
	/**
	 * Constructor
	 * @param date
	 * @param participant
	 */
	public PastMeetingImpl(Calendar date, Set<Contact> participant) {
		super(date, participant);
		notes = new ArrayList<String>();
	}

	/**
	 * This method return the notes from this meeting.
	 * @return notes from this meeting.
	 */
	@Override
	public String getNotes() {
		String result = "";
		for(int i=0; i<notes.size(); i++) {
			result = result + "[" + notes.get(i) + "]"; // Brackets were add to divide notes.
		}
		return result;
	}
	public void addNotes(String text) {
		notes.add(text);
		
	}
	

}
