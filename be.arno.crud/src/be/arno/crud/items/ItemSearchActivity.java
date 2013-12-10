package be.arno.crud.items;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import be.arno.crud.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

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
						// uniquement lorsque la touche est lachée
						if ( event.getAction() ==  KeyEvent.ACTION_UP ) {
							// uniquement si la recherche en live est activée et que l'EditText n'est pas vide
							if ( swchLive.isChecked() == true && edtxSearch.getText().length() != 0)
								fillList();
						}
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
				}
			);
		
		lsvwList.setOnItemClickListener(
				new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
						Item item = (Item)lsvwList.getItemAtPosition(position);
						Intent i = new Intent(getApplicationContext(), ItemShowActivity.class);
						i.putExtra("ID", "" + item.getId());
						startActivity(i);
					}
				}
			);
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

		// Découpe la recherche à chaque espace
		String[] searchs = search.split(" ");

		List<Item> items = new ArrayList<Item>();
		ItemDBAdapter itemAdapter = new ItemDBAdapter(getApplicationContext());
		itemAdapter.openReadable();

		int i = 0;
		// Boucle sur les 3 premiers mots
		while ( i < searchs.length && i <= 3 ) {
			String s = searchs[i].toString();
			// Si le mot fait 4 caractères ...
			if ( s.length() == 4 ) {
				Pattern pattern = Pattern.compile("^\\d\\d\\d\\d$");
				Matcher matcher = pattern.matcher(s);
				// ... et qu'il correspond à un nombre de 4 chiffres ...
				if ( matcher.matches() ) {
				    // ... va chercher les items dont l'année correspond dans la DB et les ajoute à la liste en cours
					items.addAll(itemAdapter.getSearchOnYear(s));
					Log.i("searchs", "annay !");					
				}
			}
			// Si le mot fait au moins 2 caractères ...
			if ( s.length() >= 2 )
				// ... va chercher les items contenant le mot dans la DB et jes ajoute à la liste en cours 
				items.addAll(itemAdapter.getSearchOnName(s));
			i = i + 1;
		}

		itemAdapter.close();
		return items;		
	}
}
