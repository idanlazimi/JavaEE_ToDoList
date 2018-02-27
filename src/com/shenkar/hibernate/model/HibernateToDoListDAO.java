package com.shenkar.hibernate.model;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import javassist.bytecode.Descriptor.Iterator;
import junit.framework.Test;

import org.hibernate.SessionFactory;

public class HibernateToDoListDAO implements IToDoListDao
{	
	//sql query
	public static final String FAILED_TO_DISCONNECT = "disconnection failed";
	private static final String QUERY_GET_USERS = "SELECT * FROM Java.USERS";

	// Singleton
	private static HibernateToDoListDAO instance;	
	private static SessionFactory factory; //user
	private static SessionFactory taskFactory; //task
	private static SessionFactory contactFactory; //feedback


	//def constructor
	private HibernateToDoListDAO()
	{
	}

	public static HibernateToDoListDAO getInstance()
	{
		//sigleton logic
		if (null == instance)
		{
			instance = new HibernateToDoListDAO();
			factory = new AnnotationConfiguration().configure().buildSessionFactory();
			taskFactory = new AnnotationConfiguration().configure("hibernateTask.cfg.xml").buildSessionFactory();
			contactFactory = new AnnotationConfiguration().configure("hibernateContact.cfg.xml").buildSessionFactory();
		}
		return instance;
	}


	@Override
	public User addNewUser(User user) throws UserExceptionHandler
	{
		Session session = factory.openSession();
		int sessionId = 0;
		try
		{
			session.beginTransaction();

			List<Object> users = (List<Object>) session.createSQLQuery(QUERY_GET_USERS).list();
			java.util.Iterator i = users.iterator();

			while (i.hasNext())
			{
				Object items[] = (Object[]) i.next();
				String mail = (String) items[4];
				if (mail.equals(user.getMail()))
					throw new UserExceptionHandler("already exist");
			}
			session.beginTransaction();
			sessionId = (Integer) session.save(user);
			session.getTransaction().commit();
		} catch (HibernateException e)
		{
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw new UserExceptionHandler("no connection");
		} finally
		{
			try
			{
				session.close();
			} catch (HibernateException e)
			{
				throw new UserExceptionHandler(FAILED_TO_DISCONNECT);
			}
		}
		if (0 != sessionId)
		{
			return user;
		}
		return null;
	}



	//create feedback
	@Override
	public Contact addNewComment(Contact contact) throws ContactException
	{
		//get the session
		Session session = contactFactory.openSession();
		
		//get session id
		int sessionId = 0;
		try
		{
			session.beginTransaction();
			sessionId = (Integer) session.save(contact);
			session.getTransaction().commit();
		} catch (HibernateException e)
		{
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw new ContactException("error. try again.");
		} finally
		{
			try
			{
				session.close();
			} catch (HibernateException e)
			{
				throw new ContactException(FAILED_TO_DISCONNECT);
			}
		}
		if (sessionId != 0)
		{
			return contact;
		}
		return null;
	}


	@Override
	public void deleteUser(int userId)
	{
		//get the session
		Session session = factory.openSession();
		
		try
		{
			session = factory.openSession();
			Object user = session.load(User.class, new Integer(userId));
			
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
		} finally
		{
			session.close();
		}
	}


	@Override
	public void addNewTask(Task task) throws TaskExceptionHandler
	{
		//get session
		Session session = taskFactory.openSession();
		try
		{

			java.util.List<Task> tasks = HibernateToDoListDAO.
										 getInstance().
										 getTasks(task.getTaskUserName());
			for (int i = 0; i < tasks.size(); i++)
			{
				if (tasks.get(i).getTitle().equals(task.getTitle()))
					throw new TaskExceptionHandler("duplicated task");
			}
			session.beginTransaction();
			session.save(task);
			session.getTransaction().commit();
			
		} catch (HibernateException e)
		{
			if (session.getTransaction() != null) {				
				session.getTransaction().rollback();
			}
			throw new TaskExceptionHandler("no connection" + e.getMessage());
		} 
		finally
		{
			try
			{
				session.close();
			} 
			catch (HibernateException e)
			{
				throw new TaskExceptionHandler(FAILED_TO_DISCONNECT + e.getMessage());
			}
		}
	}


	@Override
	public void deleteTask(String userName, int taskId) throws TaskExceptionHandler
	{

		Session session = taskFactory.openSession();
		try
		{
			session.beginTransaction();
			Task task = (Task) session.get(Task.class, taskId);
			
			//check if task matches the user
			if (userName.equals(task.getTaskUserName()))
			{
				session.delete(task);
				session.getTransaction().commit();
			} else
			{
				throw new TaskExceptionHandler("this task isnt yours");
			}
		} catch (HibernateException e)
		{
			if (session.getTransaction() != null) {				
				session.getTransaction().rollback();
			}
			throw new TaskExceptionHandler("no connection" + e.getMessage());
		} 
		finally
		{
			try
			{
				session.close();
			} catch (HibernateException e)
			{
				throw new TaskExceptionHandler(FAILED_TO_DISCONNECT + e.getMessage());
			}
		}
	}

	@Override
	public List<Task> getTasks(String userName) throws TaskExceptionHandler
	{
		//get session
		Session session = taskFactory.openSession();
		List<Task> tasks = null;
		
		try
		{
			session.beginTransaction();
			//query the task
			tasks = session.createQuery("FROM " + Task.class.getName() + 
					" TASK WHERE TASK.taskUserName='" + 
					userName + "'").list();
			
			session.getTransaction().commit();
		} catch (HibernateException e)
		{
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw new TaskExceptionHandler("unreachable" + e.getMessage());
		} finally
		{
			try
			{
				session.close();
			} catch (HibernateException e)
			{
				throw new TaskExceptionHandler(FAILED_TO_DISCONNECT + e.getMessage());
			}
		}
		return tasks;
	}

	/**
	 * implements the getUser (from IToDoListDao)
	 */
	public User getUser(String mail, String password) throws UserExceptionHandler
	{
		Session session = factory.openSession();
		List<User> users = null;
		try
		{
			session.beginTransaction();
			
			users = session.createQuery(
					"FROM " + User.class.getName() +
					" USER WHERE USER.mail ='" + mail + "'").list();
			
			session.getTransaction().commit();
		} catch (HibernateException e)
		{
			if (session.beginTransaction() != null)
				session.beginTransaction().rollback();
			throw new UserExceptionHandler("no connection");
		} finally
		{
			try
			{
				session.close();
			} catch (HibernateException e)
			{
				throw new UserExceptionHandler(FAILED_TO_DISCONNECT);
			}
		}
		if (users.size() > 0)
		{
			return users.get(0);
		}
		return null;
	}


	@Override
	public void updateTask(int taskID, Task modifiedTask) throws TaskExceptionHandler
	{

		//get session
		Session session = taskFactory.openSession();
		try
		{
			session.beginTransaction();
			Task task = (Task) session.get(Task.class, taskID);

			if (modifiedTask.getTaskUserName().equals(task.getTaskUserName()))
			{

				task.setTitle(modifiedTask.getTitle());
				task.setTaskDescription(modifiedTask.getTaskDescription());
				task.setStatus(modifiedTask.getStatus());
				session.update(task);
				session.getTransaction().commit();
			}

		} catch (HibernateException e)
		{
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw new TaskExceptionHandler("no connection");
		} 
		finally
		{
			try
			{
				session.close();
			} 
			catch (HibernateException e)
			{
				throw new TaskExceptionHandler(FAILED_TO_DISCONNECT);
			}
		}
	}

}