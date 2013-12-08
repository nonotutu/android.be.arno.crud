package be.arno.crud.items;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class ItemDBAdapter {

	public static final String TABLE_ITEMS = "items";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_DATE = "date";
	public static final String DB_NAME = "itemsDB";
	public static final int VERSION_NUMBER = 1;
	
	private ItemDBHelper itemDBHelper;
	private Context context;
	private SQLiteDatabase db;


	public ItemDBAdapter(Context context) {
		this.context = context;
	}


	public ItemDBAdapter openWritable() {
		itemDBHelper = new ItemDBHelper(context, DB_NAME, null, VERSION_NUMBER);
		db = itemDBHelper.getWritableDatabase();
		return this;
	}


	public ItemDBAdapter openReadable() {
		itemDBHelper = new ItemDBHelper(context, DB_NAME, null, VERSION_NUMBER);
		db = itemDBHelper.getReadableDatabase();
		return this;
	}	


	public void close() {
		db.close();
		itemDBHelper.close();
	}


	public long insert(Item item) {
		if ( item.isValid() ) {
			ContentValues valeurs = new ContentValues();
			valeurs.put(COLUMN_NAME, item.getName());
			valeurs.put(COLUMN_DATE, item.getDate());
			return db.insert(TABLE_ITEMS, null, valeurs);
		}
		return -1;
	}


	public long update(Item item) {
		if ( item.isValid() ) {
			ContentValues valeurs = new ContentValues();
			valeurs.put(COLUMN_NAME, item.getName());
			valeurs.put(COLUMN_DATE, item.getDate());
			return db.update(TABLE_ITEMS, valeurs, COLUMN_ID + " = " + item.getId(), null);
		}
		return -1;
	}


	// return Item si trouvé, null si non trouvé
	public Item getItemById(int id) {
		//Item i;
		String[] Columns = new String[] {COLUMN_ID, COLUMN_NAME, COLUMN_DATE};
		Cursor c = db.query(TABLE_ITEMS, Columns, COLUMN_ID + " = " + id, null, null, null, null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			return cursorToItem(c);
		}
		else {
			return null;
		}
	}


	private Item cursorToItem(Cursor c) {
		Item item = new Item();
		item.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
		item.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
		item.setDate(c.getString(c.getColumnIndex(COLUMN_DATE)));
		return item;
	}


	// retourne List<Items> vide si 
	public List<Item> getAll() {
		List<Item> items = new ArrayList<Item>();
		String[] columns = new String[] {COLUMN_ID, COLUMN_NAME, COLUMN_DATE};
		Cursor c = db.query(TABLE_ITEMS, columns, null, null, null, null, null);
		int i = 0;
		c.moveToFirst();
		while ( i < c.getCount() ) {
			items.add(cursorToItem(c));
			c.moveToNext();
			i = i + 1;
		}
		return items;
	}


	public List<Item> getOnlyWithDate() {
		List<Item> items = new ArrayList<Item>();
		String[] columns = new String[] {COLUMN_ID, COLUMN_NAME, COLUMN_DATE};
		Cursor c = db.query(TABLE_ITEMS, columns, "DATE IS NOT NULL", null, null, null, null);
		int i = 0;
		c.moveToFirst();
		while ( i < c.getCount() ) {
			items.add(cursorToItem(c));
			c.moveToNext();
			i = i + 1;
		}
		return items;
	}


	public void delete(Item item) {
		int i = 0;
		i = db.delete(TABLE_ITEMS, COLUMN_ID + " = " + item.getId(), null);
		Log.i("db.delete", Integer.toString(i));
	}


	public Item getFirst() {
		//Item i;
		String[] ColonnesASelectionner = new String[] {COLUMN_ID, COLUMN_NAME, COLUMN_DATE};
		Cursor c = db.query(TABLE_ITEMS, ColonnesASelectionner, null, null, null, null, null, " 1 ");
		if (c.getCount() != 0) {
			c.moveToFirst();
			return cursorToItem(c);
		}
		else {
			return null;
		}
	}
}
