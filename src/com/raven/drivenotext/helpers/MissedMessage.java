package com.raven.drivenotext.helpers;


/**
 * 
 * @author Tendai T.T. Mudyiwa
 * @version November 12 2013
 * 
 * MissedMessage defines a missed message object
 *
 */
public class MissedMessage {

	private String phoneNumber;
	private String messageBody;
	
	
	public MissedMessage(String phoneNumber, String messageBody) {
		this.phoneNumber = phoneNumber;
		this.messageBody = messageBody;
		
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getMessageBody() {
		return messageBody;
	}
	
	@Override
	public String toString(){
		String missedMesssage = "";
		 missedMesssage = "Phone Number: "+ phoneNumber +'\n'+ messageBody;
		
		return missedMesssage;
	}

}
