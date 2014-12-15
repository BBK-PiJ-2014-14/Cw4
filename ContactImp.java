package Cw4;

import java.util.List;

public class ContactImp implements Contact {
	private String name;
	private int iD;
	private List<String> note;
	private static int contactNum = 1;
	
	
	// Constructor 
	public ContactImp(String name) {
		this.name = name;
		this.iD = contactNum;
		ContactImp.contactNum++;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNotes(String note) {
		// TODO Auto-generated method stub

	}

}
