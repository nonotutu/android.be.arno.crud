package be.arno.crud.items;

import android.content.Context;
//import be.arno.crud.items.ItemDBAdapter;

public class Item {

	private int id;
	private String name;
	private String date;

	public Item() {}
	
	public Item(String name, String date) {
		this.name = name;
		this.date = date;
	}
	
	public Item(int id, String name, String date) {
		this.id = id;
		this.name = name;
		this.date = date;		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getDatePart(String part) {
		String s = null;
		int i;
		if ( part == "yyyy" ) {
			s = date.substring(0, 4);
		} else if ( part == "MM" ) {
			s = date.substring(5, 7);
		} else if ( part == "dd" ) {
			s = date.substring(8, 10);
		}
		
		i = Integer.parseInt(s);
		return i;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		String s = this.name;
		if ( this.date != null ) s += " :: " + this.date;
		return s;
	}
	
	public boolean isValid() {
		if ( this.name.isEmpty() || this.name == null )
			return false;
	return true;
	}	
}
