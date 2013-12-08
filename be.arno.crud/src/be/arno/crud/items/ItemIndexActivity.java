package be.arno.crud.items;

import be.arno.crud.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ItemIndexActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_index);

	Button bttnList = (Button)findViewById(R.id.itemIndex_bttnList);
	Button bttnNew = (Button)findViewById(R.id.itemIndex_bttnNew);
	Button bttnClose = (Button)findViewById(R.id.itemIndex_bttnClose);
	
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
