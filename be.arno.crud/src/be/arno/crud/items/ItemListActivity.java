package be.arno.crud.items;

import be.arno.crud.ListFilter;
import be.arno.crud.R;
import be.arno.crud.R.id;
import be.arno.crud.R.layout;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class ItemListActivity extends Activity {

	private int filter;
	private ArrayAdapter<ListFilter> filterListArrayAdapter;
	private ListView lsvwList;
	private ArrayAdapter<Item> itemArrayAdapter;
	ArrayList<Item> items = null;
	private Button bttnFilter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("Item/List", "onCreate");
		setContentView(R.layout.activity_item_list);
		
		filter = 0;
		initFilter();

		bttnFilter = (Button)findViewById(R.id.list_bttnFilter);
		Button bttnClose = (Button)findViewById(R.id.list_bttnClose);

		lsvwList = (ListView)findViewById(R.id.list_lsvwList);

		bttnFilter.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				filterChoice();
			}
		});
		
		bttnClose.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		lsvwList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
				Item item = (Item)lsvwList.getItemAtPosition(position);
				Log.i("Item/List/ListView", "OnItemClick - item id: " + item.getId());
				Intent i = new Intent(getApplicationContext(), ItemShowActivity.class);
				i.putExtra("ID", "" + item.getId());
				startActivity(i);
			}
		});

		lsvwList.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long arg) {
				Item item = (Item)lsvwList.getItemAtPosition(position);
				Log.i("Item/List/ListView", "OnItemLongClick - item id: " + item.getId());
				new AlertDialog.Builder(ItemListActivity.this).setMessage("ID: "+item.getId()).show();
				return true;
			}
		});
		
		List<Item> items = getList();
		fillList(items);
	}

	
	// refresh on back
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("Item/List", "onRestart");
		fillList(getList());
	}

	
	private List<Item> getList() {

		List<Item> items = null;
		ItemDBAdapter itemAdapter = new ItemDBAdapter(getApplicationContext());
		itemAdapter.openReadable();
		
		switch(filter) {
			case 1:
				items = itemAdapter.getOnlyWithDate();
				break;
			default:
				items = itemAdapter.getAll();
				break;
			}
			
		itemAdapter.close();
		return items;
		
	}


	private void fillList(List<Item> items) {
		itemArrayAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, items);
		lsvwList.setAdapter(itemArrayAdapter);
	}
	

	public void filterChoice() {

		AlertDialog.Builder builderSingle = new AlertDialog.Builder(ItemListActivity.this);
        builderSingle.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builderSingle.setAdapter(filterListArrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    	filter = filterListArrayAdapter.getItem(which).getRsql();
                    	// TODO : écrire "Filter" correctement
                    	bttnFilter.setText(R.string.filter + ": "
                    			           + filterListArrayAdapter.getItem(which).getName());
                		fillList(getList());
                    }
                });
        builderSingle.show();
        }


	public void initFilter() {
		filterListArrayAdapter = new ArrayAdapter<ListFilter>(
                ItemListActivity.this,
                android.R.layout.select_dialog_singlechoice);
        filterListArrayAdapter.add(new ListFilter("Tous", 0));
        filterListArrayAdapter.add(new ListFilter("Avec date", 1));
	}
}
