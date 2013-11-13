package com.raven.drivenotext.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.raven.drivenotext.R;
import com.raven.drivenotext.database.DefaultMessageFileReader;
/**
 * 
 * @author Tendai T.T. Mudyiwa
 * @version November 12 2013
 * 
 * Implements the class for viewing the set message
 *
 */
public class ViewMessageActivity extends Activity {
	
	private TextView setMessageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_message);
		// Show the Up button in the action bar.
		setupActionBar();
		
		DefaultMessageFileReader messageReader = new DefaultMessageFileReader(getApplicationContext());
		Log.i("FILE NAME", messageReader.getFileName());
		String setMessage = messageReader.retrieveFile();
		
		setMessageView = (TextView) findViewById(R.id.setMessageView);
		setMessageView.setText(setMessage);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
