package com.raven.drivenotext.database;

import com.raven.drivenotext.database.MissedMessageReaderContract.MissedMessageEntry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MissedMessageSQLHelper extends SQLiteOpenHelper {

	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_MISSED_MESSAGES =
			"CREATE TABLE " + MissedMessageEntry.TABLE_NAME + " (" +
			MissedMessageEntry._ID + " INTEGER PRIMARY KEY," +
			MissedMessageEntry.COLUMN_PHONE_NUMBER + TEXT_TYPE + COMMA_SEP+
			MissedMessageEntry.COLUMN_MESSAGE_BODY + TEXT_TYPE + COMMA_SEP + ")";
	
	private static final String SQL_DELETE_MISSED_MESSAGES =
			"DROP TABLE IF EXISTS " + MissedMessageEntry.TABLE_NAME;
	
	private static final String DATABASE_NAME = "Messages.db";
	private static final int DATABASE_VERSION = 1;
	
	public MissedMessageSQLHelper(Context context, String name,CursorFactory factory, int version){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_MISSED_MESSAGES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_MISSED_MESSAGES);
	}

}
