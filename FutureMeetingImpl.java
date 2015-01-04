package Cw4;

import java.util.Calendar;
import java.util.Set;

/**
 * This class is for cast only, no methods.
 * @author Noam
 *
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor use the same constructor as MeetingImpl.
	 * @param date
	 * @param participant
	 */
	public FutureMeetingImpl(Calendar date, Set<Contact> participant) {
		super(date, participant);
	}

}
