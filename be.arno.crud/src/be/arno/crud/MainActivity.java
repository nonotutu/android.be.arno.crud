package be.arno.crud;

import be.arno.crud.R;
import be.arno.crud.items.ItemIndexActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button bttnItems = (Button)findViewById(R.id.main_bttnItems);
		Button bttnTest = (Button)findViewById(R.id.main_bttnTest);
		Button bttnClose = (Button)findViewById(R.id.main_bttnClose);
				
		bttnItems.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), ItemIndexActivity.class);
				startActivity(i);
			}
		});

		bttnTest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Intent i = new Intent(getApplicationContext(), TestTabSwipeActivity.class);
				//startActivity(i);
			}
		});

		bttnClose.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});		
	}
}
