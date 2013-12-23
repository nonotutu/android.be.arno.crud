package be.arno.crud.items;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ItemDBHelper extends SQLiteOpenHelper {

	private static final String CREATE_REQUEST = " create table " +
			ItemDBAdapter.TABLE_ITEMS   + " ( " +
			ItemDBAdapter.COLUMN_ID     + " integer primary key autoincrement, " +
			ItemDBAdapter.COLUMN_NAME   + " text not null, " +
			ItemDBAdapter.COLUMN_DATE   + " text, " +
			ItemDBAdapter.COLUMN_RATING + " text, " +
			ItemDBAdapter.COLUMN_BOOL   + " int, " +
			ItemDBAdapter.COLUMN_IMAGE  + " blob ); ";
	
	private static final String UPGRADE_REQUEST_2 = " alter table " +
			ItemDBAdapter.TABLE_ITEMS +
			" ADD COLUMN " +
			ItemDBAdapter.COLUMN_RATING + " text " + " ; ";
	
	private static final String UPGRADE_REQUEST_3 = " alter table " +
			ItemDBAdapter.TABLE_ITEMS +
			" ADD COLUMN " +
			ItemDBAdapter.COLUMN_BOOL   + " int "  + " ; ";
	
	private static final String UPGRADE_REQUEST_4 = " alter table " +
			ItemDBAdapter.TABLE_ITEMS +
			" ADD COLUMN " +
			ItemDBAdapter.COLUMN_IMAGE  + " blob " + " ; ";
	
	public ItemDBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_REQUEST);
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i("DB", "onUpgrade");
		if ( oldVersion < 2 )
			db.execSQL(UPGRADE_REQUEST_2);
		if ( oldVersion < 3 )
			db.execSQL(UPGRADE_REQUEST_3);
		if ( oldVersion < 4 )
			db.execSQL(UPGRADE_REQUEST_4);
	}
	
}
