package be.arno.crud.items;

import android.content.Context;
//import be.arno.crud.items.ItemDBAdapter;

public class Item {

	private int id;
	private String name;
	private String date;
	private float rating;

	public Item() {}
	
	public Item(String name, String date, float rating) {
		this.name = name;
		this.date = date;
		this.rating = rating;
	}
	
	public Item(int id, String name, String date, float rating) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.rating = rating;
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

	public float getRating() {
		return this.rating;
	}
	public void setRating(float rating) {
		if ( rating > 5 ) rating = 5;
		if ( rating < 0 ) rating = 0;
		this.rating = rating;
	}

	public String getCharedRating() {
		int i = (int) (this.rating * 2);
		switch(i) {
		case 0:
			return "_____";
		case 1:
			return "=____";
		case 2:
			return "#____";
		case 3:
			return "#=___";
		case 4:
			return "##___";
		case 5:
			return "##=__";
		case 6:
			return "###__";
		case 7:
			return "###=_";
		case 8:
			return "####_";
		case 9:
			return "####=";
		case 10:
			return "#####";
		default:
			return "XXXXX";
		}
	}
	
	public String toString() {
		String s = this.name + " :: " + this.getCharedRating();
		return s;
	}
	
	public boolean isValid() {
		if ( this.name.isEmpty() || this.name == null )
			return false;
	return true;
	}	
}
