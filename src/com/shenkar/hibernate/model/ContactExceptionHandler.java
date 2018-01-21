package com.shenkar.hibernate.model;

public class ContactExceptionHandler extends Exception {

	
private static final long serialVersionUID = 1L;
	
	/**
	 * constructor
	 * @param msg
	 */
	public ContactExceptionHandler(String e) {
		super(e);
	}
	
	/**
	 * constructor
	 * @param e
	 * @param throwMsg
	 */
	public ContactExceptionHandler(String e, Throwable throwMsg) {
		super(e,throwMsg);
	}
}
