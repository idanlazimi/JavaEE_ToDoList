package com.shenkar.hibernate.model;

import java.util.List;

/**
 * ToDoList DAO
 * represents the DAO Interface for ToDoList
 */
public interface IToDoListDao {

	public Contact addNewComment(Contact contact) throws ContactExceptionHandler;
	
	
	/**
	 * Add Task to database
	 * @param Task
	 * @return Integer 
	 * @throws TaskExceptionHandler
	 */
	
	/*********Tasks Methods list ************/
	
	public void addNewTask(Task task) throws TaskExceptionHandler;
	

	/**
	 * Delete requested Task from database
	 * @param String, Integer
	 * @return void
	 * @throws TaskExceptionHandler
	 */
	public void deleteTask(String userName, int taskID) throws TaskExceptionHandler;
	
	
	/**
	 * Update requested task from database
	 * @param Integer, Task
	 * @return void 
	 * @throws TaskExceptionHandler
	 */
	public void updateTask(int taskID, Task updatedTask) throws TaskExceptionHandler;
	
	/**
	 * Get all Tasks from database
	 * @param Integer 
	 * @return List<Task> 
	 * @throws TaskExceptionHandler
	 */
	public List<Task> getTasks(String userName) throws TaskExceptionHandler;
	
	
	
	/*********Users Methods list ************/
	
	
	/**
	 * Sign up new User and insert to database
	 * @param User
	 * @return User
	 * @throws TaskExceptionHandler
	 */
	public User addNewUser(User user) throws UserExceptionHandler;
	
	/**
	 * Get the User that login to the system from database
	 * @param String, String
	 * @return User
	 * @throws TaskExceptionHandler
	 */
	public User getUser(String mail, String password) throws UserExceptionHandler;
	
	public void deleteUser (int idUserTodelete) throws UserExceptionHandler;

}

