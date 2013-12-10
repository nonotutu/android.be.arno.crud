package be.arno.crud.items;

import be.arno.crud.R;
import be.arno.crud.myApp;
import be.arno.crud.R.id;
import be.arno.crud.R.layout;
import be.arno.crud.R.menu;
import be.arno.crud.R.string;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ItemShowActivity extends Activity {
	
	private Item item;
	
	private TextView txvwId;
	private TextView txvwName;
	private TextView txvwDate;

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode) {
		case myApp.CODE_ACTIVITY_EDIT_ITEM:
			switch(resultCode) {
			case RESULT_OK:
				// refresh if updated
				getItemFromDB(item.getId());
				fillFields();
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_show);

		txvwId = (TextView)findViewById(R.id.itemShow_txvwId);
		txvwName = (TextView)findViewById(R.id.itemShow_txvwName);
		txvwDate = (TextView)findViewById(R.id.itemShow_txvwDate);
		
		Button bttnDelete = (Button)findViewById(R.id.itemShow_bttnDelete);
		bttnDelete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog d = askConfirmation();
				d.show();
			}
		});
		
		Button bttnEdit = (Button)findViewById(R.id.itemShow_bttnEdit);
		bttnEdit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), ItemEditActivity.class);
				i.putExtra("ID", txvwId.getText().toString());
				startActivityForResult(i, myApp.CODE_ACTIVITY_EDIT_ITEM);
			}
		});

		// récupérer l'ID dans le Bundle
		int id = getIdFromParams();
		
		// récupérer l'item de la DB
		getItemFromDB(id);
		
		// afficher les informations
		fillFields();
	}


	private void deleteItem() {
		ItemDBAdapter itemAdapter = new ItemDBAdapter(getApplicationContext());
		itemAdapter.openWritable();
		itemAdapter.delete(item);
		itemAdapter.close();
		// TODO : vérifier si supprimé
		finish();
		//Toast.makeText(getApplicationContext(), "Supprimé ?", Toast.LENGTH_LONG).show();
	}


	private int getIdFromParams() {

		String strId = null;
		int intId;
		
		Bundle extra = this.getIntent().getExtras();
		if ( extra != null ) {
			strId = extra.getString("ID");
		}
		try {
			intId = Integer.parseInt(strId);
			return intId;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "Not an integer", Toast.LENGTH_LONG).show();
			finish();
		}
		return -1;
	}


	private void getItemFromDB(int id) {
		Item i;
		ItemDBAdapter itemAdapter = new ItemDBAdapter(getApplicationContext());
		itemAdapter.openReadable();
		i = itemAdapter.getItemById(id);
		itemAdapter.close();
		item = i;
	}


	private void fillFields() {
		if ( item != null ) {		
			// TODO : "" + int ?
			txvwId.setText(""+item.getId());
			txvwName.setText(item.getName());
			txvwDate.setText(item.getDate());
		} else {
			Toast.makeText(getApplicationContext(), "Item doesn't exist", Toast.LENGTH_LONG).show();
			finish();
		}
	}


	private Dialog askConfirmation() {
		
		Dialog d = new AlertDialog.Builder(this)
		.setMessage(R.string.sureDelete)
		.setNegativeButton(android.R.string.no, null)
		.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					deleteItem();
					//Toast.makeText(getApplicationContext(), "Cliqué", Toast.LENGTH_LONG).show();
				}
			})
		.create();
		return d;
	    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show, menu);
		return true;
	}

}
