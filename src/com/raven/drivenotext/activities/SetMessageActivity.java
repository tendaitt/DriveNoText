package com.raven.drivenotext.activities;

import com.raven.drivenotext.R;
import com.raven.drivenotext.database.DefaultMessageFileReader;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SetMessageActivity extends Activity {
	private Spinner messageSpinner;
	private EditText messageTextView;
	private String message;
	private String defaultMessage;

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_message);
		// Show the Up button in the action bar.
		setupActionBar();

		messageSpinner = (Spinner) findViewById(R.id.defaultMessagesSpinner);
		messageTextView = (EditText) findViewById(R.id.customMessageTextView);

		if (ViewMessageActivity.EDIT_ACTIVATED) {

			intent = getIntent();
			String editMessage = intent.getExtras().getString("editMessage");
			if (!editMessage.equalsIgnoreCase("You haven't set a message yet!")) {
				messageTextView.setText(editMessage);
			}
		}
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.default_messages_array,
				android.R.layout.simple_spinner_item);

		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Apply the adapter to the spinner
		messageSpinner.setAdapter(adapter);
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
		getMenuInflater().inflate(R.menu.set_message, menu);
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
	public void setMessage(View view) {

		message = messageTextView.getText().toString().trim();
		defaultMessage = messageSpinner.getSelectedItem().toString().trim();

		DefaultMessageFileReader fileReader;
		if (!message.isEmpty()) {
			fileReader = new DefaultMessageFileReader(getApplicationContext(),
					message);
			fileReader.storeFile();
		} else if (!defaultMessage.isEmpty()) {
			fileReader = new DefaultMessageFileReader(getApplicationContext(),
					defaultMessage);
			fileReader.storeFile();
		} else {
			fileReader = new DefaultMessageFileReader(getApplicationContext(),
					"You haven't set a message yet!");
			fileReader.storeFile();
		}
		Toast toast = Toast
				.makeText(getApplicationContext(), "Message Set!", 3);
		toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
		toast.show();
		Intent goBackHome = new Intent(this, HomeActivity.class);
		startActivity(goBackHome);

	}

}
