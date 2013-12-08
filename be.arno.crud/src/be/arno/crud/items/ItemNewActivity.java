package be.arno.crud.items;

import be.arno.crud.R;
import be.arno.crud.Helper;
import be.arno.crud.R.id;
import be.arno.crud.R.layout;
import be.arno.crud.R.menu;
import be.arno.crud.R.string;

/*
import java.text.SimpleDateFormat;
import java.util.Date;
*/

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class ItemNewActivity extends Activity {

	private EditText edtxName;
	private DatePicker dtpkDate;
	private Switch swchDate;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_new);

		edtxName = (EditText)findViewById(R.id.new_edtxName);
		dtpkDate = (DatePicker)findViewById(R.id.new_dtpkDate);
		swchDate = (Switch)findViewById(R.id.new_swchDate);
		
		
		Button bttnSave = (Button)findViewById(R.id.new_bttnUpdate);
		bttnSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				long l = insertItemInDB();
				Log.i("Item/New", "Created: " + l);
				// TODO : vérifier tous les codes de retour
				if ( l == -1 ) {
					Toast.makeText(getApplicationContext(), R.string.item_not_created, Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(), R.string.item_created, Toast.LENGTH_SHORT).show();
					finish();
				}
			}
		});


		Button bttnClose = (Button)findViewById(R.id.new_bttnClose);
		bttnClose.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private long insertItemInDB() {
		
		String date = null;
		Long l;
		
		if ( swchDate.isChecked() )		
			date = Helper.dateInts2String(dtpkDate.getYear(), dtpkDate.getMonth(), dtpkDate.getDayOfMonth());
		
		Item item = new Item(edtxName.getText().toString(), date);
		
		ItemDBAdapter itemAdapter = new ItemDBAdapter(getApplicationContext());
		itemAdapter.openWritable();
		l = itemAdapter.insert(item);
		itemAdapter.close();
		
		return l;
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create, menu);
		return true;
	}

	
	
	
}
