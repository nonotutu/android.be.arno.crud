package be.arno.crud.items;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemDBHelper extends SQLiteOpenHelper {

	private static final String CREATE_REQUEST = " create table " +
			ItemDBAdapter.TABLE_ITEMS + " ( " +
			ItemDBAdapter.COLUMN_ID + " integer primary key autoincrement, " +
			ItemDBAdapter.COLUMN_NAME + " text not null, " +
			ItemDBAdapter.COLUMN_DATE + " text " + " ); ";
	
	// TODO : alter table
	private static final String UPGRADE_REQUEST = "DROP TABLE";
	
	public ItemDBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_REQUEST);
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(UPGRADE_REQUEST);
		onCreate(db);
	}
	
	/* apparemment, ça ne va pas là
	public Item getItemById(int id){
		Item i;
		String[] ColonnesASelectionner = new String[] {COLONNE_ID, COLONNE_NAME, COLONNE_DATE};
		Cursor c = db.query(TABLE_ITEMS, ColonnesASelectionner, COLONNE_ID + " = " + id, null, null, null, null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			return cursorToItem(c);
		}
		else {
			return null;
		}
	}*/
}
