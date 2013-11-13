package com.raven.drivenotext.activities;

import com.raven.drivenotext.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.telephony.SmsManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
/**
 * 
 * @author Tendai T.T. Mudyiwa
 * @version November 12 2013
 *
 */
public class SendMessageActivity extends Activity {
	private EditText messageEditText;
	private EditText phoneNumberEditText;
	private String sms;
	private String phoneNumber;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_message);
		// Show the Up button in the action bar.
		setupActionBar();
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
		getMenuInflater().inflate(R.menu.send_message, menu);
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

	@SuppressLint("ShowToast")
	public void sendMessage(View view){
		messageEditText = (EditText) findViewById(R.id.messageToBeSentTextView);
		phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
		sms = messageEditText.getText().toString();
		phoneNumber = phoneNumberEditText.getText().toString();
		
		
		try{
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(phoneNumber, null, sms, null, null);
		Toast toast = Toast.makeText(getApplicationContext(), "Message Sent!", 3);
		toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
		toast.show(); 
		}
		catch(Exception e){
			Toast toast = Toast.makeText(getApplicationContext(), "Message Sending Failed!", 3);
			toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
			toast.show(); 
			e.printStackTrace();
		}
		
		
		Intent goBackHome = new Intent(this, HomeActivity.class);
		startActivity(goBackHome);
		
	}
}
