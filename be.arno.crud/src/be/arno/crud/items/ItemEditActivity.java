package be.arno.crud.items;

import be.arno.crud.Helper;
import be.arno.crud.R;
import be.arno.crud.R.id;
import be.arno.crud.R.layout;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.app.Activity;


public class ItemEditActivity extends Activity {

	private Item item;
	
	private EditText edtxName;
	private DatePicker dtpkDate;
	private Switch swchDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_edit);

		edtxName = (EditText)findViewById(R.id.itemForm_edtxName);
		dtpkDate = (DatePicker)findViewById(R.id.itemForm_dtpkDate);
		swchDate = (Switch)findViewById(R.id.itemForm_swchDate);
		
		Button bttnUpdate = (Button)findViewById(R.id.itemEdit_bttnUpdate);
		bttnUpdate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				long l = updateItemInDB();
				Log.i("Item/Edit", "Updated: " + l);
				if ( l == 1 ) {
					Toast.makeText(getApplicationContext(), "Item updated", Toast.LENGTH_SHORT).show();
					setResult(RESULT_OK);
					finish();
				} else {
					Toast.makeText(getApplicationContext(), "Item not updated", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		Button bttnCancel  = (Button)findViewById(R.id.itemEdit_bttnCancel);
		bttnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setResult(RESULT_CANCELED);
				finish();
			}
		});

		// récupérer l'ID dans le Bundle
		int id = getIdFromParams();
		
		// récupérer l'item de la DB
		getItemFromDB(id);
		
		// afficher les informations
		fillFields();
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
	

	private long updateItemInDB() {

		String date = null;
		Long l;

		if ( swchDate.isChecked() )		
			date = Helper.dateInts2String(dtpkDate.getYear(), dtpkDate.getMonth(), dtpkDate.getDayOfMonth());

		Item i = new Item(item.getId(), edtxName.getText().toString(), date);

		ItemDBAdapter itemAdapter = new ItemDBAdapter(getApplicationContext());
		itemAdapter.openWritable();
		l = itemAdapter.update(i);
		itemAdapter.close();

		return l;
	}

	
	
	private void fillFields() {
		if ( item != null ) {		
			edtxName.setText(item.getName());
			if ( item.getDate() != null ) {
					swchDate.setChecked(true);
					dtpkDate.updateDate(item.getDatePart("yyyy"), item.getDatePart("MM")-1, item.getDatePart("dd"));
			}
		} else {
			Toast.makeText(getApplicationContext(), "Item doesn't exist", Toast.LENGTH_LONG).show();
			finish();
		}
	}
}
