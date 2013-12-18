package be.arno.crud;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TestActivity extends Activity {

	private TextView txvw;
	private Button bttnThread1, bttnThread2, bttnThread3;
	private ProgressBar pgbr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		pgbr = (ProgressBar)findViewById(R.id.test_pgbr);
		txvw = (TextView)findViewById(R.id.test_txvw);

		bttnThread1 = (Button)findViewById(R.id.test_bttnThread1);
		bttnThread1.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
			}
		});

		bttnThread2 = (Button)findViewById(R.id.test_bttnThread2);
		bttnThread2.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				pgbr.setProgress(0);
				txvw.setText("");
				CounterAsyncTask cat = new CounterAsyncTask();
				cat.execute("");
			}
		});

		bttnThread3 = (Button)findViewById(R.id.test_bttnThread3);
		bttnThread3.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
			}
		});
		
	}
													// paramètre d'entrée
															// paramètre de progression
																	// type du paramètre de retour
	private class CounterAsyncTask extends AsyncTask<String, Integer, String>{
	
		@Override
		protected String doInBackground(String... url) { //  paramètre d'entrée
			int progress = 0;
			
			while ( progress < 100 )
				{
				try { Thread.sleep(100); } catch (Exception e) { Log.i("sleep", "catché"); }
				Log.i("thread2", "publish");
				publishProgress(progress);
				progress += 8;
			}	
			return null; // paramètre de retour
		}
		
		@Override
		protected void onProgressUpdate(Integer... values){ // param§tre de progression
			Log.i("thread2", "published");
			pgbr.setProgress(values[0]);
			txvw.setText(txvw.getText().toString() + " " + values[0] + " || ");
		}
	
	
	}
}
