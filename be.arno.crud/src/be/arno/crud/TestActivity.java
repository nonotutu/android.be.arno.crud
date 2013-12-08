package be.arno.crud;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TestActivity extends Activity {

	private TextView txvw;
	private Button bttn;
	private ArrayAdapter<ListFilter> arrayAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		txvw = (TextView)findViewById(R.id.itemIndex_txvwCount);
		bttn = (Button)findViewById(R.id.button1);

		bttn.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				initArray();
				dialogChoice();
			}
		});
	}


	public void initArray() {

		arrayAdapter = new ArrayAdapter<ListFilter>(
                TestActivity.this,
                android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add(new ListFilter("Tous", 0));
        arrayAdapter.add(new ListFilter("Avec date", 1));
	}


	public void dialogChoice() {

		AlertDialog.Builder builderSingle = new AlertDialog.Builder(TestActivity.this);
        builderSingle.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builderSingle.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    	txvw.setText(arrayAdapter.getItem(which).getName());
                    }
                });
        builderSingle.show();
        }
	
}
