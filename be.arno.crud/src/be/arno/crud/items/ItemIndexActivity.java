package be.arno.crud.items;

import be.arno.crud.R;
import be.arno.crud.TestActivity;
import be.arno.crud.R.layout;
import be.arno.crud.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
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

	Button bttnList = (Button)findViewById(R.id.itemIndex_bttnList);
	Button bttnNew = (Button)findViewById(R.id.itemIndex_bttnNew);
	Button bttnClose = (Button)findViewById(R.id.itemIndex_bttnClose);
	txvwCount = (TextView)findViewById(R.id.itemIndex_txvwCount);

	setCount();
		
	bttnClose.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	});
	
	bttnNew.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(getApplicationContext(), ItemNewActivity.class);
			startActivity(i);
		}
	});

	bttnList.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(getApplicationContext(), ItemListActivity.class);
			startActivity(i);
		}
	});
}
	
}
