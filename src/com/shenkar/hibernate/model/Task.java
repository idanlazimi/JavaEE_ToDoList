package com.shenkar.hibernate.model;

/**
 * Task Class
 * TASK table in the Database
 */

public class Task {
	
	   	private int taskID;
	   	private String title;
		private String taskUserName;
		private String status;
		private String taskDescription;

	   /**
	   * constructor
	   */
	   public Task() {}

	 /**
	  * constructor  
	  * @param taskUserName
	  * @param title
	  * @param taskDescription
	  * @param status
	  */
	  public Task(String taskUserName, String title, String taskDescription, String status) {
		this.title = title;
		this.taskUserName = taskUserName;
		this.status = status;
		this.taskDescription = taskDescription;
	}


/**
 * getter to taskID field
 * @return taskID
 */
	public int getTaskID() {
		return taskID;
	}
/**
 * setter to taskID field
 * @param taskID
 */

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

/**
 * getter to title field
 * @return title
 */
	public String getTitle() {
		return title;
	}

	/**
	 * setter to title field
	 * @param title
	 */

	public void setTitle(String title) {
		this.title = title;
	}

/**
 * getter to the user that own the task
 * @return
 */
	public String getTaskUserName() {
		return taskUserName;
	}


	/**
	 * setter to the user that own the task
	 * @param taskUserName
	 */
	public void setTaskUserName(String taskUserName) {
		this.taskUserName = taskUserName;
	}

/**
 * getter to status field
 * @return status
 */
	public String getStatus() {
		return status;
	}

/**
 * setter to status field
 * @param status
 */
	public void setStatus(String status) {
		this.status = status;
	}

/**
 * getter to taskDescription field
 * @return taskDescription
 */
	public String getTaskDescription() {
		return taskDescription;
	}

/**
 * setter to taskDescription field
 * @param taskDescription
 */
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}	   

}
