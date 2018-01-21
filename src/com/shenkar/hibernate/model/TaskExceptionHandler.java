package com.shenkar.hibernate.model;

/**
 * TaskExceptionHandler Handles Tasks errors  
 */
public class TaskExceptionHandler extends Exception{

	private static final long serialVersionUID = 1L;
	
	/**
	 * constructor
	 * @param msg
	 */
	public TaskExceptionHandler(String e) {
		super(e);
	}
	
	/**
	 * constructor
	 * @param e
	 * @param throwMsg
	 */
	public TaskExceptionHandler(String e, Throwable throwMsg) {
		super(e,throwMsg);
	}
}
