package be.arno.crud.items;

import be.arno.crud.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ItemIndexActivity extends Activity {

	private TextView txvwCount;
	
	@Override
	protected void onRestart() {
		super.onRestart();
		setCount();
	}
	
	private void setCount() {
		ItemDBAdapter itemAdapter = new ItemDBAdapter(getApplicationContext());
		itemAdapter.openReadable();
		int i = itemAdapter.getCount();
		itemAdapter.close();
		txvwCount.setText(""+i);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_index);

		txvwCount = (TextView)findViewById(R.id.itemIndex_txvwCount);
	
		setCount();
			
		// VFEC
		Button bttnNew = (Button)findViewById(R.id.itemIndex_bttnNew);
		bttnNew.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(), ItemNewActivity.class);
					startActivity(i);
				}
			}
		);
	
		// VFEC
		Button bttnList = (Button)findViewById(R.id.itemIndex_bttnList);
		bttnList.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(), ItemListActivity.class);
					startActivity(i);
				}
			}
		);
	
		// VFEC
		Button bttnSearch = (Button)findViewById(R.id.itemIndex_bttnSearch);
		bttnSearch.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(), ItemSearchActivity.class);
					startActivity(i);
				}
			}
		);
			
	}
}
