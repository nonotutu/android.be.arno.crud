package be.arno.crud.items;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class ItemContentProvider extends ContentProvider {

	private static final String authority = "be.arno.crud";
	private static final String uriItems = "content://" + authority + "/items";
	
	// TODO : en cours
	
	private static final Uri CONTENT_URI
	
	private static final UriMatcher uriMatcher; static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(authority, "items", TOUS_ITEMS);
		uriMatcher.addURI(authority, "items/#", ITEM_UNIQUE);
	}
	
	
	
	@Override
	public boolean onCreate() {
		itemDBAdapter = new ItemDBAdapter(this.getContext());
		itemDBAdapter
		return false;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		switch (uriMatcher.match(uri)) {
			case TOUS_ITEMS:
				  
			break;
			case ITEM_UNIQUE:
			break;
		}
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
