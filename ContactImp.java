package Cw4;

import java.util.ArrayList;
import java.util.List;


public class ContactImp implements Contact {
	private String name;
	private int iD;
	private List<String> notes;
	private static int contactNum = 1;
	
	
	// Constructor 
	public ContactImp(String name) {
		this.name = name;
		this.iD = contactNum;
		ContactImp.contactNum++;
		this.notes = new ArrayList<String>();
	}

	// this method return the ID of the contact.
	@Override
	public int getId() {
		return iD;
		
	}

	// This method return the name of the contact.
	@Override
	public String getName() {
		return name;
	}

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

	// This method add a note about the contact.
	@Override
	public void addNotes(String note) {
		notes.add(note);
	}

}
