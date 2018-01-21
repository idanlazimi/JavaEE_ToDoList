package com.shenkar.hibernate.model;

import java.io.Serializable;

public class Contact implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 42L;
	
	private int contactID;
	private String name;
	private String email;
	private String comment;
	

	/**
	 * Constructor 
	 */
	public Contact() {}

public int getContactID() {
		return contactID;
	}

	public void setContactID(int contactID) {
		this.contactID = contactID;
	}

/**
 * Constructor
 * @param name
 * @param email
 * @param comment
 */
	public Contact(String name, String email, String comment) {
		super();
		this.name = name;
		this.email = email;
		this.comment = comment;
	}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getComment() {
	return comment;
}

public void setComment(String comment) {
	this.comment = comment;
}
	

}


