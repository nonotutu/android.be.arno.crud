package be.arno.crud.items;

import be.arno.crud.items.ItemDBAdapter;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;


/**

   --------------
	\ Appelant /
	 ----------
	
	2.  Content Resolver (query, insert, update, delete)
		idéal Async
	
	4.	Traitement Cursor pour en faire un objet
		idéal Async
	
   --------------
	\  Appelé  /
	 ----------

	1. 	Définir le Content Provider : classe qui extends ContentProvider
		L'enregistrer dans le Manifest
		URI matcher
	
	3.	CP <--> DBAdap <--> DB
		idéal Async

*/

public class ItemContentProvider extends ContentProvider {

	private static final String authority = "be.arno.crud.ItemProvider";
	private static final String uriItems = "content://" + authority + "/items";
	public static final Uri CONTENT_URI = Uri.parse(uriItems);
	public static final int COLONNE_ID = 0;
	public static final int COLONNE_NAME = 1;
	public static final int COLONNE_DATE = 2;
	private ItemDBAdapter itemDBAdapter;
	
	@Override
	public boolean onCreate() {
		itemDBAdapter = new ItemDBAdapter(this.getContext());
		itemDBAdapter.openReadable();
		return false;
	}
	
	private static final int TOUS_ITEMS = 0;
	private static final int ITEM_UNIQUE = 1;
	private static final UriMatcher uriMatcher; static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(authority, "items", TOUS_ITEMS);
		uriMatcher.addURI(authority, "items/#", ITEM_UNIQUE);
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

			    Cursor c = itemDBAdapter.getCursorAll();
			    c.setNotificationUri(getContext().getContentResolver(), uri);
			    return c;
				  
			case ITEM_UNIQUE:
				
				int id = 0;
				try {
					id = Integer.parseInt(uri.getPathSegments().get(1)); }
				catch (Exception e) {
					return null;
				}

				Cursor d = itemDBAdapter.getCursorItemById(id);
				d.setNotificationUri(getContext().getContentResolver(), uri);
			    return d;
			    
			default:
				return null;
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
