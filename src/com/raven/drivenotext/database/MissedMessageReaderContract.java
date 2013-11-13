package com.raven.drivenotext.database;

import android.provider.BaseColumns;
/**
 * 
 * @author Tendai T.T. Mudyiwa
 * @version November 12 2013
 *
 */
public final class MissedMessageReaderContract {

	public MissedMessageReaderContract(){}
	
	public static abstract class MissedMessageEntry implements BaseColumns{
		//define the table and its columns
		public static final String TABLE_NAME = "missed_messages";
		public static final String _ID = "id";
		public static final String COLUMN_MESSAGE_BODY = "message_body";
		public static final String COLUMN_PHONE_NUMBER = "phone_number";
	}
	
}
