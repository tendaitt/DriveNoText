package com.raven.drivenotext.helpers;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.raven.drivenotext.database.DefaultMessageFileReader;
import com.raven.drivenotext.database.MissedMessageReaderContract.MissedMessageEntry;
import com.raven.drivenotext.database.MissedMessageSQLHelper;

/**
 * @author Tendai T.T. Mudyiwa
 * @version November 12 2013
 * 
 *          SmsInterceptor intercepts incoming text messages and saves them to a
 *          database
 * 
 */
public class SmsInterceptor extends BroadcastReceiver {
	final SmsManager sms = SmsManager.getDefault();
	public static boolean INTERCEPT = false;

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

					if(INTERCEPT){
						storeMessage(senderNum, message, context);
						respondToMessage(senderNum, context);
					}
				
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param senderNum - the number to send message to
	 * @param context - the application context
	 */
	private void respondToMessage(String senderNum, Context context) {
		DefaultMessageFileReader reader = new DefaultMessageFileReader(context);
		String response = reader.retrieveFile();
		Log.i("MESSAGE", response);
		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(senderNum, null, response, null, null);
			Log.i("Message Sent", "I sent it!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * storeMessage Stores the message and number to the database
	 * 
	 * @param phoneNumber
	 *            - the phone number
	 * @param message
	 *            - the message body of the text
	 * @param context
	 *            - the context of the application
	 */
	public void storeMessage(String phoneNumber, String message, Context context) {

		// update database
		MissedMessageSQLHelper dbHelper = new MissedMessageSQLHelper(context,
				message, null, 0);
		SQLiteDatabase missedMessageDb = dbHelper.getWritableDatabase();
		// map of values
		ContentValues values = new ContentValues();
		values.put(MissedMessageEntry.COLUMN_MESSAGE_BODY, message);
		values.put(MissedMessageEntry.COLUMN_PHONE_NUMBER, phoneNumber);

		// Insert the new row
		@SuppressWarnings("unused")
		long newRowId;
		newRowId = missedMessageDb.insert(MissedMessageEntry.TABLE_NAME, null,
				values);

	}

}
