package Cw4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the interface Contact.
 * @author Noam
 */
public class ContactImpl implements Contact, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int iD;
	private List<String> notes;
	private static int contactNum = 1;
	
	
	/** 
	 * Constructor 
	 */
	public ContactImpl(String name) {
		this.name = name;
		this.iD = contactNum;
		contactNum++;
		this.notes = new ArrayList<String>();
	}

	/**0
	 *  This method return the ID of the contact.
	 *  @return the Id of the contact.
	 */
	@Override
	public int getId() {
		return iD;
		
	}

	/** 
	 * This method return the name of the contact.
	 * @return the name of the contact.
	 */
	@Override
	public String getName() {
		return name;
	}
   
	/** 
	 * This method return a string of notes refers to the contact or return an empty string if there aren't any. 
	 * @return string of notes or empty string if there aren't any.
	 */
	@Override
	public String getNotes() {
		String result = "";
		if (!notes.isEmpty()) {
			for (int i = 0; i < notes.size(); i++) {
				result = result + "[" + notes.get(i) + "]";
			}
		}
		return result;

	}

	/**
	 * This method add a note about the contact.
	 */
	@Override
	public void addNotes(String note) {
		notes.add(note);
	}

}
