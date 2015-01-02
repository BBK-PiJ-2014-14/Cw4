package Cw4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
	

	/**
	 * Add a new meeting to be held in the future.
     *
     * @param contacts a list of contacts that will participate in the meeting
     * @param date the date on which the meeting will take place
     * @return the ID for the meeting
     * @throws IllegalArgumentException if the meeting is set for a time in the past,
     * of if any contact is unknown / non-existent
	 */
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date)throws IllegalArgumentException {
		Calendar now = Calendar.getInstance();
		if (date.before(now)|| !this.contacts.containsAll(contacts)) {
			throw new IllegalArgumentException("Past date or unknown contact");
		}
		FutureMeeting newMeeting = new FutureMeetingImpl(date, contacts);
		future.add(newMeeting);
		return newMeeting.getId();
	}

    /**
     * Returns the PAST meeting with the requested ID, or null if there is none.
     *
	 * @param id the ID for the meeting
	 * @return the meeting with the requested ID, or null if there is none.
	 * @throws IllegalArgumentException if there is a meeting with that ID happening in the future
	 */
	@Override
	public PastMeeting getPastMeeting(int id) throws IllegalArgumentException {
		try {
			if (getFutureMeeting(id) != null) {
				throw new IllegalArgumentException("This is a future meeting");
			}
		} catch (IllegalArgumentException e) {
	}
		PastMeeting result = null;
		for(int i=0; i<past.size(); i++) {
			if(past.get(i).getId()==id){
				result = past.get(i);
				break;
			}	
		}
		return result;
	}

   /**
	* Returns the FUTURE meeting with the requested ID, or null if there is none.
	*
	* @param id the ID for the meeting
	* @return the meeting with the requested ID, or null if it there is none.
	* @throws IllegalArgumentException if there is a meeting with that ID happening in the past
	*/
	@Override
	public FutureMeeting getFutureMeeting(int id) throws IllegalArgumentException {
		FutureMeeting result = null;
		for(int i=0; i<future.size(); i++) {
			if(future.get(i).getId()==id) {
				result = future.get(i);
				break;
			}
		}
		if (result == null) {
			for(int i=0; i<past.size(); i++) {
				if(past.get(i).getId()==id){
					throw new IllegalArgumentException("This is a past Meeting");
				}	
			}
		}
		return result;
	}

	/**
	* Returns the meeting with the requested ID, or null if it there is none.
	*
	* @param id the ID for the meeting
	* @return the meeting with the requested ID, or null if it there is none.
	*/
	@Override
	public Meeting getMeeting(int id) {
		Meeting result = null;
		try {
		    result =  getFutureMeeting(id);
		} catch (IllegalArgumentException e) {
			try {
				result = getPastMeeting(id);
			} catch (IllegalArgumentException d) {
				d.getStackTrace();
			}
		}
		return result;
	}

	/**
	* Returns the list of future meetings scheduled with this contact.
	*
	* If there are none, the returned list will be empty. Otherwise,
	* the list will be chronologically sorted and will not contain any
	* duplicates.
	*
	* @param contact one of the user’s contacts
	* @return the list of future meeting(s) scheduled with this contact (maybe empty).
	* @throws IllegalArgumentException if the contact does not exist
	*/
	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) throws IllegalArgumentException {
		if (!contacts.contains(contact)) {
			throw new IllegalArgumentException("Contact does not exist");
		}
		List<Meeting> result= new ArrayList<Meeting>();
		for(int i=0; i<future.size(); i++) {
			if (future.get(i).getContacts().contains(contact)) {
				result.add(future.get(i));
			}
		}
		Collections.sort(result, dateComp);
		return result;
	}

	/**
	* Returns the list of meetings that are scheduled for, or that took
	* place on, the specified date
	*
	* If there are none, the returned list will be empty. Otherwise,
	* the list will be chronologically sorted and will not contain any
	* duplicates.
	*
	* @param date the date
	* @return the list of meetings
	*/
	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		List<Meeting> result = new ArrayList<Meeting>();
		for(int i=0; i<future.size(); i++) {
			if (future.get(i).getDate() == date) {
				result.add(future.get(i));
			}
		}
		Collections.sort(result, dateComp);
		return result;	
	}

	/**
	* Returns the list of past meetings in which this contact has participated.
	*
	* If there are none, the returned list will be empty. Otherwise,
	* the list will be chronologically sorted and will not contain any
	2* duplicates.
	*
	* @param contact one of the user’s contacts
	* @return the list of future meeting(s) scheduled with this contact (maybe empty).
	* @throws IllegalArgumentException if the contact does not exist
	*/
	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) throws IllegalArgumentException{
		if (!this.contacts.contains(contact)) {
			throw new IllegalArgumentException("Contact does not exist");		
		}
		List<PastMeeting> result = new ArrayList<PastMeeting>();
		for (int i=0; i<past.size(); i++) {
			if (past.get(i).getContacts().contains(contact)) {
				result.add(past.get(i));
			}
		}
		Collections.sort(result, dateComp);
		return result;
	}

	/**
	* Create a new record for a meeting that took place in the past.
	*
	* @param contacts a list of participants
	* @param date the date on which the meeting took place
	* @param text messages to be added about the meeting.
	* @throws IllegalArgumentException if the list of contacts is
	* empty, or any of the contacts does not exist
	* @throws NullPointerException if any of the arguments is null
	*/
	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) 
			throws IllegalArgumentException, NullPointerException {
		if(contacts == null || date == null || text == null) {
			throw new NullPointerException("Missing arguments");
		}
		if(contacts.isEmpty() || !this.contacts.containsAll(contacts)) {
			throw new IllegalArgumentException("Contact not found");
		}
		PastMeetingImpl newPast = new PastMeetingImpl(date, contacts);
		newPast.addNotes(text);
		past.add(newPast);
	}

	/**
	* Add notes to a meeting.
	*
	* This method is used when a future meeting takes place, and is
	* then converted to a past meeting (with notes).
	*
	* It can be also used to add notes to a past meeting at a later date.
	*
	* @param id the ID of the meeting
	* @param text messages to be added about the meeting.
	* @throws IllegalArgumentException if the meeting does not exist
	* @throws IllegalStateException if the meeting is set for a date in the future
	* @throws NullPointerException if the notes are null
	*/
	@Override
	public void addMeetingNotes(int id, String text) 
			throws IllegalArgumentException, IllegalStateException, NullPointerException {
		if (getMeeting(id)==null) {
			throw new IllegalArgumentException("Meeting does not exists");
		}
		if (text == null) {
			throw new NullPointerException("No notes added");
		}
		Calendar now = Calendar.getInstance();
		if (getMeeting(id).getDate().after(now)) {
			throw new IllegalStateException("Date is not in the past");
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

	/**
	* Create a new contact with the specified name and notes.
	*
	* @param name the name of the contact.
	* @param notes notes to be added about the contact.
	* @throws NullPointerException if the name or the notes are null
	*/
	@Override
	public void addNewContact(String name, String notes) throws NullPointerException {
		if(name == null || notes == null) {
			throw new NullPointerException("Missing argument");
		}
		ContactImpl newCont = new ContactImpl(name);
		newCont.addNotes(notes);
		contacts.add(newCont);
	}

	/**
	* Returns a list containing the contacts that correspond to the IDs.
	*
	* @param ids an arbitrary number of contact IDs
	* @return a list containing the contacts that correspond to the IDs.
	* @throws IllegalArgumentException if any of the IDs does not correspond to a real contact
	*/
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
			throw new IllegalArgumentException("wrong id, Can't find contact");
		}
		
		return result;
	}

	/**
	* Returns a list with the contacts whose name contains that string.
	3*
	* @param name the string to search for
	* @return a list with the contacts whose name contains that string.
	* @throws NullPointerException if the parameter is null
	*/
	@Override
	public Set<Contact> getContacts(String name) throws NullPointerException {
		if (name == null) {
			throw new NullPointerException("Name not initialized");
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


		
	/**
	 * A comparator to compare meeting by their date.
	 */
	}
	public Comparator<Meeting> dateComp = new Comparator<Meeting>() {

		@Override
		public int compare(Meeting o1, Meeting o2) {
			if (o1.getId() == o2.getId()) {
				return 0;
			}
			int result = o1.getDate().compareTo(o2.getDate());
			if (result != 0 ) {
				return result;
			}
			return o1.getId() > o2.getId() ? 1 : -1;
		}
		
	};

}
