package com.raven.drivenotext;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class HomeActivity extends Activity {

	private  Switch activationSwitch;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	public void sendAMessage(View view){
		Intent goToSendMessage = new Intent(this, SendMessageActivity.class);
		startActivity(goToSendMessage);
	}
	
	public void setAMessage(View view){
		Intent gotToSetMessage = new Intent(this, SetMessageActivity.class);
		startActivity(gotToSetMessage);
	}

	public void viewCurrentMessage(View view){
		Intent gotToViewMessage = new Intent(this, ViewMessageActivity.class);
		startActivity(gotToViewMessage);
	}
	
	public void switchOnOff(View view){
		activationSwitch = (Switch) findViewById(R.id.driveNoTextSwitch);
		if(activationSwitch.isChecked()){
			Toast toast = Toast.makeText(getApplicationContext(), "DriveNoText Has Been Activated", 3);
			toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
			toast.show(); 
		}
		else{
			Toast toast = Toast.makeText(getApplicationContext(), "DriveNoText Has Been Deactivated", 3);
			toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
			toast.show(); 
		}
		
		
	}
}
