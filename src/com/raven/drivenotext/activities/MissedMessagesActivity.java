package com.raven.drivenotext.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.raven.drivenotext.R;
import com.raven.drivenotext.database.DBToMissedMessageConverter;
import com.raven.drivenotext.database.MissedMessageReaderContract.MissedMessageEntry;
import com.raven.drivenotext.database.MissedMessageSQLHelper;
import com.raven.drivenotext.helpers.MissedMessage;
/**
 * 
 * @author Tendai T.T. Mudyiwa
 * @version November 12 2013
 * 
 * Implements the code for the view that displays all the
 * missed messages
 *
 */
public class MissedMessagesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_missed_messages);
		// Show the Up button in the action bar.
		setupActionBar();
		
		
		DBToMissedMessageConverter missedMessageConverter = new DBToMissedMessageConverter(getApplicationContext());
		ArrayList<MissedMessage> missedMessages = missedMessageConverter.convertToMissedMessage();
		ListView missedMessageListView = (ListView) findViewById(R.id.missedMessageListView);
		ArrayAdapter<MissedMessage> arrayAdapter = new 
							ArrayAdapter<MissedMessage>(this,android.R.layout.simple_list_item_1, missedMessages);
		         missedMessageListView.setAdapter(arrayAdapter); 
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.missed_messages, menu);
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

	public void listSeen(View view){
		Intent goBackHome = new Intent(this, HomeActivity.class);
		startActivity(goBackHome);
	}
	
	public void clearList(View view){
		//clear db
		MissedMessageSQLHelper dbHelper = new MissedMessageSQLHelper(getApplicationContext(), null, null, 0);
		SQLiteDatabase missedMessagesDb = dbHelper.getReadableDatabase();
		missedMessagesDb.delete(MissedMessageEntry.TABLE_NAME, null, null);
		
		//clear screen
		ArrayList<String> blank = new ArrayList<String>();
		ListView missedMessageListView = (ListView) findViewById(R.id.missedMessageListView);
		ArrayAdapter<String> arrayAdapter = new 
							ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, blank);
		         missedMessageListView.setAdapter(arrayAdapter); 
	}
}
