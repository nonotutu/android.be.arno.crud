package be.arno.crud.items;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

public class Item {

	private int id;
	private String name;
	private String date;
	private float rating;
	private int bool;
	private Bitmap image;

	public Item() {}

	
	public void setImage(Bitmap image) {
		this.image = image;
	}
	
	public Bitmap getImage() {
		return this.image;
	}
	
	public byte[] getByteArrayImage() {
		byte[] byteArray = null;
		if ( this.image != null ) {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			// TODO : comprendre
			this.image.compress(Bitmap.CompressFormat.PNG, 100, stream);
			byteArray = stream.toByteArray();
		}
		return byteArray;
	}
	
	public void setByteArrayImage(byte[] byteArray) {
		if ( byteArray != null ) {
			// TODO : comprendre
			Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
			this.image = bitmap;
		}
	}
	
	
	public int getBool() {
		return this.bool;
	}
	
	public void setBool(int bool) {
		this.bool = bool;
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
		if ( this.bool < 0 || this.bool > 1 )
			return false;
	return true;
	}	
}
