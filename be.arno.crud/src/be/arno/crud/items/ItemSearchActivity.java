package be.arno.crud.items;

import java.util.List;

import be.arno.crud.R;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

public class ItemSearchActivity extends Activity {

	// Adapter de la liste des _Item_
	private ArrayAdapter<Item> itemArrayAdapter;

	private EditText edtxSearch; // champ de recherche
	private ListView lsvwList;   // liste des résultats
	private TextView txvwCount;  // nombre d'_Item_ dans la liste
	//private Switch swchLive;

	@Override
	protected void onRestart() {
		super.onRestart();
		fillList();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_search);

		edtxSearch = (EditText)findViewById(R.id.itemSearch_edtxSearch);
		lsvwList = (ListView)findViewById(R.id.itemSearch_lsvwList);
		txvwCount = (TextView)findViewById(R.id.itemSearch_txvwCount);
		final Switch swchLive = (Switch)findViewById(R.id.itemSearch_swchLive);

		edtxSearch.setOnKeyListener(
				new OnKeyListener() {
					@Override
					public boolean onKey(View v, int keyCode, KeyEvent event) {
						if ( swchLive.isChecked() == true )
							fillList();
							
						return false;
					}
				}
			);

		Button bttnSearch = (Button)findViewById(R.id.itemSearch_bttnSearch);
		bttnSearch.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					fillList();
				}
			});	
	}


	// Get et affiche la liste dans le ListView
	private void fillList() {
		List<Item> items = getList(edtxSearch.getText().toString());
		itemArrayAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, items);
		lsvwList.setAdapter(itemArrayAdapter);
		txvwCount.setText(getString(R.string.items_found) + ": " + items.size());
	}

	// Récupère la liste selon le champ de recherche depuis la DB via l'Adapter
	private List<Item> getList(String search) {

		List<Item> items = null;
		ItemDBAdapter itemAdapter = new ItemDBAdapter(getApplicationContext());
		itemAdapter.openReadable();

		items = itemAdapter.getSearchOnName(search);

		itemAdapter.close();
		return items;		
	}
}
