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
			ItemDBAdapter.COLUMN_DATE + " text, " +
			ItemDBAdapter.COLUMN_RATING + " text " + " ); ";
	
	private static final String UPGRADE_REQUEST_2 = " alter table " +
			ItemDBAdapter.TABLE_ITEMS +
			" ADD COLUMN " +
			ItemDBAdapter.COLUMN_RATING + " text " + " ; ";
	
	
	public ItemDBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_REQUEST);
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if ( oldVersion == 1 && newVersion == 2 )
			db.execSQL(UPGRADE_REQUEST_2);
	}
	
}
