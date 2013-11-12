package com.raven.drivenotext.helpers;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.raven.drivenotext.database.MissedMessageReaderContract.MissedMessageEntry;
import com.raven.drivenotext.database.MissedMessageSQLHelper;

public class SmsInterceptor extends BroadcastReceiver {
	final SmsManager sms = SmsManager.getDefault();

	@Override
	public void onReceive(Context context, Intent intent) {
		// Retrieves a map of extended data from the intent.
		final Bundle bundle = intent.getExtras();

		try {

			if (bundle != null) {

				final Object[] pdusObj = (Object[]) bundle.get("pdus");

				for (int i = 0; i < pdusObj.length; i++) {

					SmsMessage currentMessage = SmsMessage
							.createFromPdu((byte[]) pdusObj[i]);
					String phoneNumber = currentMessage
							.getDisplayOriginatingAddress();

					String senderNum = phoneNumber;
					String message = currentMessage.getDisplayMessageBody();

					// Show alert
					int duration = Toast.LENGTH_LONG;
					Toast toast = Toast.makeText(context, "senderNum: "
							+ senderNum + ", message: " + message, duration);
					toast.show();
					
					storeMessage(senderNum,message,context);

				}
			} 

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	
	public void storeMessage(String phoneNumber, String message,Context context){
		
				//update database
				MissedMessageSQLHelper dbHelper = new MissedMessageSQLHelper(context, message, null, 0);
				SQLiteDatabase missedMessageDb = dbHelper.getWritableDatabase();
				//map of values
				ContentValues values = new ContentValues();
				values.put(MissedMessageEntry.COLUMN_MESSAGE_BODY, message);
				values.put(MissedMessageEntry.COLUMN_PHONE_NUMBER, phoneNumber);

				//Insert the new row
				@SuppressWarnings("unused")
				long newRowId;
				newRowId = missedMessageDb.insert(MissedMessageEntry.TABLE_NAME, null, values);
		
	}
	

}
