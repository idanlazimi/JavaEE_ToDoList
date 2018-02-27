package com.shenkar.hibernate.model;

import java.util.List;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import javassist.bytecode.Descriptor.Iterator;
import junit.framework.Test;

import org.hibernate.SessionFactory;

/**
 * IToDoListDAO implementation
 */
public class HibernateToDoListDAO implements IToDoListDao{

	/**
	 * singleton HibernateToDoListDAO instance 
	 * factory is initialized only once by the HibernateToDoListDAO instance 
	 */
	private static HibernateToDoListDAO instance;
	//Factory for the user
	private static SessionFactory factory;
	//Factory for the task
	private static SessionFactory taskFactory;
	//Factory for the contact Us
	private static SessionFactory contactFactory;
	
	private static final String QUERY_GET_USERS = "SELECT * FROM Java.USERS";

	/** GET_USERS
	 * constructor
	 */
	private HibernateToDoListDAO() {}
	
	/** 
	 * getInstance method
	 */
	public static HibernateToDoListDAO getInstance() {
		
		if (instance == null) {
			instance = new HibernateToDoListDAO();
			
			factory = new AnnotationConfiguration().configure().buildSessionFactory();
			taskFactory	= new AnnotationConfiguration().configure("hibernateTask.cfg.xml").buildSessionFactory();
			contactFactory = new AnnotationConfiguration().configure("hibernateContact.cfg.xml").buildSessionFactory();	
		}
		return instance;
	}
	
	/**
	 * implements the addNewUser (from IToDoListDao)
	 */	
	@Override
	public User addNewUser(User user) throws UserExceptionHandler{
	Session session = factory.openSession();
		int id = 0;
		try{
			session.beginTransaction();
			List <Object> users = (List <Object>) session.createSQLQuery(QUERY_GET_USERS).list();
			java.util.Iterator i = users.iterator();
			
			while(i.hasNext())
			{
				Object obj[] = (Object[])i.next();
				String mail = (String) obj[4];
				if (mail.equals(user.getMail()))
					throw new UserExceptionHandler("Unable to signup with that User name because it is already exist");
			}			
			session.beginTransaction();
			id = (Integer) session.save(user); 
			session.getTransaction().commit();
		}catch (HibernateException e) {
			if (session.getTransaction()!=null) session.getTransaction().rollback();
			throw new UserExceptionHandler("Unable to signup, duplicate User name or no network connection"); 
		}finally {
			try {
				session.close();
			} catch (HibernateException e){
				throw new UserExceptionHandler("Warnning!! connection did'nt close properly");
			} 
		}
		if (id != 0) {
			return user;	
		}
		return null;
	}	
	
	/**
	 * implements addNewComment from the DB
	 */
	
	@Override
	public Contact addNewComment(Contact contact) throws ContactException{
	Session session = contactFactory.openSession();
		int id = 0;
		try{
			session.beginTransaction();
			id = (Integer) session.save(contact); 
			session.getTransaction().commit();
		}catch (HibernateException e) {
			if (session.getTransaction()!=null) session.getTransaction().rollback();
				throw new ContactException("Unable to signup your comment,please try again"); 
		}finally {
			try {
				session.close();
			} catch (HibernateException e){
				throw new ContactException("Warnning!! connection did'nt close properly");
			} 
		}
		if (id != 0) {
			return contact;	
		}
		return null;
	}	
	
	
	/**
	 * implements deleteUser from the DB
	 */
	@Override
	public void deleteUser (int idUserTodelete)
	{
		Session session = factory.openSession();

		try
		{
			session = factory.openSession();
			Object ob = session.load(User.class, new Integer (idUserTodelete));
			session.beginTransaction();
			session.delete(ob);
			session.getTransaction().commit();
		}
		catch  (HibernateException e) 
		{
			if ( session.getTransaction() != null )
		    	session.getTransaction().rollback();
		}
		finally
		{
			session.close();
		}
	}
	
	
	/**
	 * implements addNewTask 
	 * (from IToDoListDao) 
	 */	
	@Override
	public void addNewTask(Task task) throws TaskExceptionHandler {
		
		Session session = taskFactory.openSession();
		try{
			
		   java.util.List<Task> tasks = HibernateToDoListDAO.getInstance().getTasks(task.getTaskUserName());
		   for (int i=0; i<tasks.size(); i++)
		   {
			   if (tasks.get(i).getTitle().equals(task.getTitle()))
					  throw new TaskExceptionHandler("You have alredy this task in the list");
		   }
		   session.beginTransaction();
		   session.save(task); 
		   session.getTransaction().commit();
		}catch (HibernateException e) {
		   if (session.getTransaction() != null) session.getTransaction().rollback();
		   throw new TaskExceptionHandler("Can'nt insert task at moment, please check your connection" + e.getMessage());
		}finally {
			try {
				session.close();
			} catch (HibernateException e){
				throw new TaskExceptionHandler("Warnning!! connection did'nt close properly" + e.getMessage());
			}
		}
	}

	/**
	 * implements deleteTask (from IToDoListDAO)
	 */	
	@Override
	public void deleteTask(String userName, int taskID) throws TaskExceptionHandler {
		
	      Session session = taskFactory.openSession();
	      try{
	    	 session.beginTransaction();
	         Task task = (Task)session.get(Task.class, taskID); 
	         if (userName.equals(task.getTaskUserName())) {
	        	 session.delete(task); 
	 			 session.getTransaction().commit();
	         } else {
	        	 throw new TaskExceptionHandler("Error, You don't have access to this task");
	         }
	      }catch (HibernateException e) {
	         if (session.getTransaction() != null) session.getTransaction().rollback();
	         throw new TaskExceptionHandler("Can'nt delete task at moment, please check the connection" + e.getMessage());
	      }finally {
	    	 try {
	    		 session.close();
	    	 } catch (HibernateException e){
	    		 throw new TaskExceptionHandler("Warnning!! connection did'nt close properly" + e.getMessage());
	    	 } 
	      }
	}

	/**
	 * implements getTasks (from IToDoListDao)
	 */	
	@Override
	public List<Task> getTasks(String userName) throws TaskExceptionHandler {
		
		  Session session = taskFactory.openSession();
		  List<Task> tasks = null;
	      try{
	         session.beginTransaction();
	         tasks = session.createQuery("from "+ Task.class.getName() + " task where task.taskUserName='" + userName +"'").list(); 
			 session.getTransaction().commit();
	      }catch (HibernateException e) {
	         if (session.getTransaction() != null) session.getTransaction().rollback();
	         throw new TaskExceptionHandler("Tasks list not avilable at the moment" + e.getMessage());
	      }finally {
	    	 try {
	    		 session.close();
	    	 } catch (HibernateException e){
	    		 throw new TaskExceptionHandler("Warnning!! connection did'nt close properly" + e.getMessage());
	    	 } 
	      }
	      return tasks;
	}

	/**
	 * implements the getUser (from IToDoListDao) 
	 */	
	public User getUser(String mail, String password) throws UserExceptionHandler{
		  Session session = factory.openSession();
	      List<User> users = null;
	      try{
	    	  session.beginTransaction();
	          users = session.createQuery("from " + User.class.getName() + " user where user.mail ='" + mail +"'").list();
	          session.getTransaction().commit();
	      }catch (HibernateException e) {
	         if (session.beginTransaction() != null) session.beginTransaction().rollback();
	         	throw new UserExceptionHandler("Sorry, connection problem was detected, login denied");
	      }finally {
	    	 try {
	    		session.close();
	    	 } catch(HibernateException e){
	    		 throw new UserExceptionHandler("Warnning!! connection did'nt close properly");
	    	 }
	      }
	      if (users.size() > 0) {
		      return users.get(0);	
	      }
	      return null;
	}


	/**
	 * implements the updateTask (from IToDoListDao)
	 */	
	@Override
	public void updateTask(int taskID, Task updatedTask) throws TaskExceptionHandler {
		
		  Session session = taskFactory.openSession();
	      try{
	    	  session.beginTransaction();
	         Task task = (Task)session.get(Task.class, taskID); 
	         
	         if ( updatedTask.getTaskUserName().equals(task.getTaskUserName()) ) {
	
	        	 task.setTitle(updatedTask.getTitle());
		         task.setTaskDescription(updatedTask.getTaskDescription());
		         task.setStatus(updatedTask.getStatus());
				 session.update(task); 
		    	 session.getTransaction().commit();
	         }
	         
	      }catch (HibernateException e) {
	         if (session.getTransaction()!=null) session.getTransaction().rollback();
	         	throw new TaskExceptionHandler("Can'nt update task at the moment, please check your connection");
	      }finally {
	    	 try {
	    		 session.close();
	    	 } catch (HibernateException e){
	    		 throw new TaskExceptionHandler("Warnning!! connection did'nt close properly");
	    	 } 
	      }
	}
	
}