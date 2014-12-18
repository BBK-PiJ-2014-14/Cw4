package Cw4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class ContactManagerImpl implements ContactManager {
	private List<FutureMeetingImpl> future;
	private List<PastMeetingImpl> past;
	private List<ContactImpl> contacts;
	
	
	/**
	 * Constructor
	 */
	public ContactManagerImpl() {
		future = new ArrayList<FutureMeetingImpl>();
		past = new ArrayList<PastMeetingImpl>();
		contacts = new ArrayList<ContactImpl>();	
	}
	

	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		Meeting newMeeting = new FutureMeetingImpl(date, contacts);
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PastMeeting getPastMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FutureMeeting getFutureMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Meeting getMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date,
			String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addMeetingNotes(int id, String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addNewContact(String name, String notes) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Contact> getContacts(int... ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Contact> getContacts(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

}
