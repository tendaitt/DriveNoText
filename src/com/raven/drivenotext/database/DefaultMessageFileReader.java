package com.raven.drivenotext.database;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
/**
 * 
 * @author Tendai T.T. Mudyiwa
 * @version November 12 2013
 *
 * DefaultMessageFileReader provides the methods that enable
 * reading and writing files in the local storage
 */
public class DefaultMessageFileReader {

	private FileOutputStream fos;
	private String fileName;
	private String file;
	private Context context;
	private FileInputStream fis;

	public DefaultMessageFileReader(Context context, String file) {
		this.fos = null;
		this.fileName = "custom_message";
		this.file = file;
		this.context = context;
	}

	public DefaultMessageFileReader(Context applicationContext) {
		this.context = applicationContext;
		this.fileName = "custom_message";
	}

	public String getFileName() {
		return fileName;
	}

	public void storeFile() {
		try {
			fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			fos.write(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String retrieveFile() {
		try {
			fis = context.openFileInput(fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader bufferedReader = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			while ((line = bufferedReader.readLine()) != null) {

				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String msg = sb.toString();
		return msg;

	}
}
