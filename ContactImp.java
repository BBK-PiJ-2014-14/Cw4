package Cw4;

import java.util.List;

public class ContactImp implements Contact {
	private String name;
	private int iD;
	private List<String> note;
	private static int contactNum = 1;
	
	
	
	public ContactImp(String name) {
		this.name = name;
		this.iD = contactNum;
		ContactImp.contactNum++;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
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
