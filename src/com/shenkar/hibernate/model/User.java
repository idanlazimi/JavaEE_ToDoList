package com.shenkar.hibernate.model;


/**
 * User class
 * USER table in the Database
 */

public class User {

	   private int userID;
	   private String firstname;
	   private String lastname;
	   private String mail;
	   private String password;


	   /**
	   * constructor
	   */
	   public User() {}
	   
	   /**
	   * constructor
	   * @param firstname
	   * @param mail
	   * @param password
	   */
	   public User(String mail, String password, String firstname, String lastname) {
		   this.setFirstname(firstname);
		   this.setLastname(lastname);
		   this.setMail(mail);
		   this.setPassword(password);
	   }
	 
	   
	   /**
		* getter to firstname field
		* @return firstname
		*/
	   public String getFirstname() {
		return firstname;
	   }

	   /**
		* setter to firstname field
		*
		*/
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	 /**
	* getter to lasttname field
	* @return lastname
	*/
	public String getLastname() {
		return lastname;
	}

	 /**
	* setter to lastname field
	*
	*/
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	
	/**
	   * getter to userID field
	   * @return userID
	   */
	   public int getUserID() {
		return userID;
	   }

	 /**
	  * setter to userID field
	  * @param userID
	  */
	   public void setUserID(int userID) {
		this.userID = userID;
	   }

	   /**
	   * getter to mail field
	   * @return mail
	   */
	   public String getMail() {
	      return mail;
	   }
	   
	   /**
	   * setter to mail field
	   * @param mail
	   */
	   public void setMail( String mail ) {
	      this.mail = mail;
	   }
	   
	   
	   /**
	   * getter to password field
	   * @return password
	   */
	   public String getPassword() {
	      return password;
	   }
	   
	   /**
	   * setter to password field
	   * @param password
	   */
	   public void setPassword( String password ) {
	      this.password = password;
	   }
}
