package com.raven.drivenotext.database;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.raven.drivenotext.database.MissedMessageReaderContract.MissedMessageEntry;
import com.raven.drivenotext.helpers.MissedMessage;
/**
 * 
 * @author Tendai T.T. Mudyiwa
 * @version November 12 2013
 * 
 * DBToMissedMessageConverter converts the rows in the database
 * tables into MissedMessage objects
 */
public class DBToMissedMessageConverter {

	private MissedMessageSQLHelper missedMessageDbHelper;
	private SQLiteDatabase missedMessageDb;
	private ArrayList<MissedMessage> missedMessagesList;
	
	
	public DBToMissedMessageConverter(Context context){
		this.missedMessageDbHelper = new MissedMessageSQLHelper(context,null,null,0);
		this.missedMessageDb = missedMessageDbHelper.getReadableDatabase();
		this.missedMessagesList = new ArrayList<MissedMessage>();
	}
	
	public ArrayList<MissedMessage> convertToMissedMessage(){
		//Required Columns
		String[] projection = {
				MissedMessageEntry._ID,
				MissedMessageEntry.COLUMN_MESSAGE_BODY,
				MissedMessageEntry.COLUMN_PHONE_NUMBER
		};
		
		String sortOrder = MissedMessageEntry._ID + " DESC";
		
		Cursor c = missedMessageDb.query(MissedMessageEntry.TABLE_NAME,
											projection, 
											null, null, null, null, sortOrder);
		c.moveToFirst();
		
		while(!c.isAfterLast()){
			MissedMessage message = new MissedMessage(c.getString(c.getColumnIndexOrThrow(MissedMessageEntry.COLUMN_PHONE_NUMBER)), c.getString(c.getColumnIndexOrThrow(MissedMessageEntry.COLUMN_MESSAGE_BODY)));
			missedMessagesList.add(message);
			c.moveToNext();
		}
		
		return missedMessagesList;
	}
	
	@Override
	public String toString(){
		String messages = "";
		
		for(int i = 0; i<missedMessagesList.size(); i++){
			messages.concat(missedMessagesList.get(i).toString());
		}
		
		return messages;
	}
}
