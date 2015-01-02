package Cw4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactManagerImpl implements ContactManager {
	private List<FutureMeeting> future;
	private List<PastMeeting> past;
	private List<Contact> contacts;
	
	
	/**
	 * Constructor
	 */
	public ContactManagerImpl() {
		future = new ArrayList<FutureMeeting>();
		past = new ArrayList<PastMeeting>();
		contacts = new ArrayList<Contact>();	
	}
	

	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date)throws IllegalArgumentException {
		Calendar now =Calendar.getInstance();
		if (date.before(now)|| !this.contacts.containsAll(contacts)) {
			throw new IllegalArgumentException();
		}
		FutureMeeting newMeeting = new FutureMeetingImpl(date, contacts);
		future.add(newMeeting);
		return newMeeting.getId();
	}

	@Override
	public PastMeeting getPastMeeting(int id) {
		PastMeeting result = null;
		for(int i=0; i<past.size(); i++) {
			if(past.get(i).getId()==id){
				result = past.get(i);
				break;
			}	
		}
		return result;
	}

	@Override
	public FutureMeeting getFutureMeeting(int id) {
		FutureMeeting result = null;
		for(int i=0; i<future.size(); i++) {
			if(future.get(i).getId()==id) {
				result = future.get(i);
				break;
			}
		}

		return result;
	}

	@Override
	public Meeting getMeeting(int id) {
		Meeting result = getFutureMeeting(id);
		if (result == null) {
			result = getPastMeeting(id);
		}
		return result;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		if (!contacts.contains(contact)) {
			throw new IllegalArgumentException();
		}
		List<Meeting> result= new ArrayList<Meeting>();
		for(int i=0; i<future.size(); i++) {
			if (future.get(i).getContacts().contains(contact)) {
				result.add(future.get(i));
			}
		}
		
		
		//Collections.sort(result);
		return result;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		List<Meeting> result = new ArrayList<Meeting>();
		for(int i=0; i<future.size(); i++) {
			if (future.get(i).getDate() == date) {
				result.add(future.get(i));
			}
		}
		return result;
	}

	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) throws IllegalArgumentException{
		if (!this.contacts.contains(contact)) {
			throw new IllegalArgumentException();		
		}
		List<PastMeeting> result = new ArrayList<PastMeeting>();
		for (int i=0; i<past.size(); i++) {
			if (past.get(i).getContacts().contains(contact)) {
				result.add(past.get(i));
			}
		}
		
		return result;
	}

	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) 
			throws IllegalArgumentException, NullPointerException {
		if(contacts == null || date == null || text ==null) {
			throw new NullPointerException();
		}
		if(contacts.isEmpty() || !this.contacts.containsAll(contacts)) {
			throw new IllegalArgumentException();
		}
		PastMeetingImpl newPast = new PastMeetingImpl(date, contacts);
		newPast.addNotes(text);
		past.add(newPast);
	}

	@Override
	public void addMeetingNotes(int id, String text) 
			throws IllegalArgumentException, IllegalStateException, NullPointerException {
		if (getMeeting(id)==null) {
			throw new IllegalArgumentException();
		}
		if (text == null) {
			throw new NullPointerException();
		}
		Calendar now = Calendar.getInstance();
		if (getMeeting(id).getDate().after(now)) {
			throw new IllegalStateException();
		}
		if (getPastMeeting(id)!=null) {
			PastMeetingImpl newPast = (PastMeetingImpl)getPastMeeting(id);
			newPast.addNotes(text);
			for(int i=0; i<past.size(); i++) {
				if(getPastMeeting(id)==past.get(i)) {
					past.remove(i);
					break;
				}
			}
			past.add(newPast);
		} else {
			Set<Contact> con = getFutureMeeting(id).getContacts();
			Calendar date = getFutureMeeting(id).getDate();
			PastMeetingImpl newPast = new PastMeetingImpl(date, con);
			newPast.addNotes(text);
			past.add(newPast);
			for(int i=0; i<future.size(); i++) {
				if(getFutureMeeting(id)==future.get(i)) {
					future.remove(i);
					break;
				}
			}
		}
	}

	@Override
	public void addNewContact(String name, String notes) throws NullPointerException {
		if(name == null || notes == null) {
			throw new NullPointerException();
		}
		ContactImpl newCont = new ContactImpl(name);
		newCont.addNotes(notes);
		contacts.add(newCont);
	}

	@Override
	public Set<Contact> getContacts(int... ids) {
		Set<Contact> result = new HashSet<Contact>();
		for(int n=0; n<ids.length; n++){
			for (int i=0; i<contacts.size(); i++) {
				if(contacts.get(i).getId() == ids[n]) {
					result.add(contacts.get(i));
					
				} 
			}   
		}
		if (result.size() != ids.length ) {
			throw new IllegalArgumentException();
		}
		
		return result;
	}

	@Override
	public Set<Contact> getContacts(String name) throws NullPointerException {
		if (name == null) {
			throw new NullPointerException();
		}
		Set<Contact> result = new HashSet<Contact>();
		for(int n=0; n<contacts.size();n++) {
			if(contacts.get(n).getName() == name) {
				result.add(contacts.get(n));
			}	
		}
		return result;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

}
