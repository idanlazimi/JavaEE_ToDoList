package com.shenkar.hibernate.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shenkar.hibernate.model.Contact;
import com.shenkar.hibernate.model.ContactException;
import com.shenkar.hibernate.model.HibernateToDoListDAO;
import com.shenkar.hibernate.model.Task;
import com.shenkar.hibernate.model.TaskExceptionHandler;
import com.shenkar.hibernate.model.User;
import com.shenkar.hibernate.model.UserExceptionHandler;

/**
 * Servlet implementation class ToDoProgramController
 */

@WebServlet("/usercontroller/*")
public class ToDoProgramController extends HttpServlet
{
	// another test aaa
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private RequestDispatcher dispatcher;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToDoProgramController()
	{
		super();
		dispatcher = null;
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		session = request.getSession(true);
		StringBuffer requestBuffer = request.getRequestURL();
		String url = requestBuffer.toString();
		String tempURL = null;
		
		
		if (url.endsWith("login"))
		{
			tempURL = loginProcess(request, response);
		} else if (url.endsWith("ContactUS"))
		{
			tempURL = "ContactUS.html";

			// New sign up
		} else if (url.endsWith("newSignup"))
		{
			tempURL = signupProcess(request, response);

			// Insert new Task
		} else if (url.endsWith("insert"))
		{
			tempURL = addNewTask(request, response);

			// Remove the requested Task
		} else if (url.endsWith("delete"))
		{
			tempURL = deleteTask(request, response);

			// Edit the requested Task
		} else if (url.endsWith("edit"))
		{
			tempURL = updateTask(request, response);

			// GET a comment from the login page
		} else if (url.endsWith("contact"))
		{
			try
			{
				tempURL = insertComment(request, response);
			} catch (ContactException e)
			{
				e.printStackTrace();
			}
		} else
		{
			tempURL = "contactbean.jsp";
		}
		// Forward to the next page
		nextState(request, response, "/" + tempURL);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		StringBuffer buf;
		String url;
		
		buf = request.getRequestURL();
		url = buf.toString();
		session = request.getSession(true);
		User user = (User) session.getAttribute("user");

		if (user == null)
		{
			{
				pagingManager(request, response, "/" + "login");
			}
		} else
		{
			pagingManager(request, response, url);
		}

	}
	
	/**
	 * getting the current user and return his Todo list
	 * 
	 * @param userName
	 * @return List<Task>
	 */
	public List<Task> viewTaskList(String userName)
	{
		List<Task> tasksList = null;
		try
		{
			tasksList = HibernateToDoListDAO.getInstance().getTasks(userName);
		} catch (TaskExceptionHandler e)
		{
			tasksList = null;
		}
		return tasksList;
	}

	/**
	 * Called from inside the get Method, directing to the correct JSP file
	 * 
	 * @param url
	 * @param mail
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	private void pagingManager(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String url)
			throws ServletException, IOException
	{

		User currentUser = (User) session.getAttribute("user");

		// The user chose the logout option
		if (url.endsWith("logout"))
		{
			session.invalidate();
			nextState(httpServletRequest, httpServletResponse, "/login.jsp");

		} else if (url.endsWith("ContactUS"))
		{
			nextState(httpServletRequest, httpServletResponse, "/ContactUS.html");

			// Go to LOGIN JSP file
		} else if (url.endsWith("login"))
		{
			nextState(httpServletRequest, httpServletResponse, "/login.jsp");

			// Go to ABOUT JSP file
		} else if (url.endsWith("about"))
		{
			nextState(httpServletRequest, httpServletResponse, "/about.jsp");

			// Go to Task JSP file
		} else if (url.endsWith("list"))
		{
			String email = currentUser.getMail();
			List<Task> tasks = viewTaskList(email);
			if (tasks != null)
			{
				httpServletRequest.setAttribute("tasks", tasks);
				nextState(httpServletRequest, httpServletResponse, "/tasklist.jsp");
			} else
			{
				httpServletRequest.setAttribute("ERROR", "");
				nextState(httpServletRequest, httpServletResponse, "/error.jsp");
			}
		}
		// editing exist task
		else if (url.endsWith("edittask"))
		{
			String email = currentUser.getMail();
			List<Task> tasks = viewTaskList(email);
			if (tasks != null)
			{
				httpServletRequest.setAttribute("tasks", tasks);
				nextState(httpServletRequest, httpServletResponse, "/edittask.jsp");
			} else
			{
				httpServletRequest.setAttribute("ERROR", "");
				nextState(httpServletRequest, httpServletResponse, "/error.jsp");
			}

			// adding new task
		} else if (url.endsWith("addTask"))
		{
			nextState(httpServletRequest, httpServletResponse, "/addTask.jsp");

			// removing exist task
		} else if (url.endsWith("removetask"))
		{
			String email = currentUser.getMail();
			List<Task> tasks = viewTaskList(email);
			if (tasks != null)
			{
				httpServletRequest.setAttribute("tasks", tasks);
				nextState(httpServletRequest, httpServletResponse, "/removetask.jsp");
			} else
			{
				httpServletRequest.setAttribute("ERROR", "");
				nextState(httpServletRequest, httpServletResponse, "/error.jsp");
			}

			// Go to MENU JSP file
		} else
		{
			nextState(httpServletRequest, httpServletResponse, "/menu.jsp");
		}
	}

	/**
	 * next state requested page (URL)
	 * 
	 * @param request
	 * @param response
	 * @param url
	 */
	private void nextState(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException
	{
		dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}



	/**
	 * check user input
	 * 
	 * @param ...
	 *            String
	 * @return boolean
	 */
	public boolean verification(String... strings)
	{
		boolean flag = true;
		for (String str : strings)
		{
			if (str.isEmpty())
				flag = false;
		}
		return flag;
	}

	/**
	 * Validation checked of email address
	 * 
	 * @param userMail
	 * @return boolean
	 */
	public boolean validtionCheck(String userMail)
	{
		if(!userMail.contains(" ") && userMail.matches(".+@.+\\.[a-z]+") ) return true;
		else return false;
	}

	/**
	 * connect to DB 
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 */
	private String loginProcess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	{

		String tempURL = null;
		User currentUser = null;
		String emailAddress = httpServletRequest.getParameter("mail"), password = httpServletRequest.getParameter("pass");
		
		if (!verification(emailAddress, password))
		{
			httpServletRequest.setAttribute("RESULT", "Please fill all The fields");
			tempURL = "/login.jsp";
		} else
		{
			try
			{
				currentUser = HibernateToDoListDAO.getInstance().getUser(emailAddress, password);
				if (currentUser != null && password.equals(currentUser.getPassword()))
				{
					session.setAttribute("user", currentUser);
					tempURL = "/menu.jsp";
				} else
				{
					httpServletRequest.setAttribute("RESULT", "unable to login, Please signup first or verify mail and password");
					tempURL = "/login.jsp";
				}
			} catch (UserExceptionHandler e)
			{
				httpServletRequest.setAttribute("RESULT", e.getMessage());
				tempURL = "/login.jsp";
			}
		}
		return tempURL;
	}

	private String insertComment(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ContactException
	{
		String tempURL = null;
		String name = httpServletRequest.getParameter("loginname"), mail = httpServletRequest.getParameter("loginemail"), comment = httpServletRequest.getParameter("logincomments");
		if (!verification(name, mail, comment))
		{
			httpServletRequest.setAttribute("RESULT", "Please fill all The fields for correctly Sign up");
			tempURL = "/login.jsp";
		} else if (!validtionCheck(mail))
		{
			httpServletRequest.setAttribute("RESULT", "Please check again the Your Email address");
			tempURL = "/login.jsp";
		} else
		{
			Contact contact = new Contact(name, mail, comment);
			Contact newComment = null;
			newComment = HibernateToDoListDAO.getInstance().addNewComment(contact);
			if (newComment != null)
			{
				session.setAttribute("newcomment", newComment);
				tempURL = "/comment.jsp";
			} else
			{
				tempURL = "/login.jsp";
			}
		}

		return tempURL;
	}

	/**
	 * Sign Up
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 */
	private String signupProcess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	{
		String tempURL = null;
		String firstName = httpServletRequest.getParameter("firstname");
		String lastName = httpServletRequest.getParameter("lastname");
		String mail = httpServletRequest.getParameter("mail");
		String pass = httpServletRequest.getParameter("pass");

		if (!verification(mail, pass, firstName, lastName))
		{
			httpServletRequest.setAttribute("RESULT", "Please fill all The fields for correctly Sign up");
			tempURL = "/login.jsp";
		} else if (!validtionCheck(mail))
		{
			httpServletRequest.setAttribute("RESULT", "Please check again the Your Email address");
			tempURL = "/login.jsp";
		} else
		{
			User newUser = new User(mail, pass, firstName, lastName);
			User currentUser = null;
			try
			{
				currentUser = HibernateToDoListDAO.getInstance().addNewUser(newUser);
				if (currentUser != null)
				{
					session.setAttribute("user", currentUser);
					// implements for cookie
					Cookie fnameCookie = new Cookie("userName", currentUser.getFirstname());
					fnameCookie.setMaxAge(-1);
					httpServletResponse.addCookie(fnameCookie);
					tempURL = "/menu.jsp";
					httpServletRequest.setAttribute("firstname", firstName);
				} else
				{
					tempURL = "/login.jsp";
				}
			} catch (UserExceptionHandler e)
			{
				httpServletRequest.setAttribute("RESULT", e.getMessage());
				tempURL = "/login.jsp";
			}
		}
		return tempURL;
	}
	
	/**
	 * Adding new Task to the DB
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 */
	private String addNewTask(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	{
		String tempURL = null;
		String title = httpServletRequest.getParameter("title");
		String sts = httpServletRequest.getParameter("status");
		String description = httpServletRequest.getParameter("taskBody");
		if (!verification(title, description, sts))
		{
			httpServletRequest.setAttribute("RESULT", "Field completion is required");
			tempURL = "/addTask.jsp";
		} else
		{
			User currentUser = (User) session.getAttribute("user");
			String mail = currentUser.getMail();
			Task newTask = new Task(mail, title, description, sts);
			try
			{
				HibernateToDoListDAO.getInstance().addNewTask(newTask);
				httpServletRequest.setAttribute("RESULT", "Task: " + title + " Added Successfully");
			} catch (TaskExceptionHandler e)
			{
				httpServletRequest.setAttribute("RESULT", e.getMessage());
				tempURL = "/addTask.jsp";
			}

			List<Task> tasks = viewTaskList(mail);
			if (tasks != null)
			{
				httpServletRequest.setAttribute("tasks", tasks);
				tempURL = "/tasklist.jsp";
			} else
			{
				httpServletRequest.setAttribute("RESULT", "Error occured");
				tempURL = "/menu.jsp";
			}
		}
		return tempURL;
	}

	/**
	 * edit task if exists
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 */
	@SuppressWarnings("unchecked")
	private String updateTask(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	{
		String tempURL = null;
		String taskId = httpServletRequest.getParameter("id");
		String title = httpServletRequest.getParameter("title");
		String status = httpServletRequest.getParameter("status");
		String desc = httpServletRequest.getParameter("taskbody");
		User currentUser = (User) session.getAttribute("user");
		String mail = currentUser.getMail();
		List<Task> tasks = viewTaskList(mail);

		if (!verification(taskId, title, desc, status))
		{
			httpServletRequest.setAttribute("RESULT", "Please fill all the fields");
			httpServletRequest.setAttribute("tasks", tasks);
			tempURL = "/edittask.jsp";
		} else
		{

			HashMap<Integer, Integer> tasksId = ((HashMap<Integer, Integer>) session.getAttribute("HASH"));
			int id = 0;
			try
			{
				id = Integer.parseInt(taskId);
			} catch (NumberFormatException e)
			{
				httpServletRequest.setAttribute("RESULT", "ID field must be a Number");
				httpServletRequest.setAttribute("tasks", tasks);
				tempURL = "/edittask.jsp";
				return tempURL;
			}
			if (tasksId.containsKey(id))
			{
				id = tasksId.get(id);
				try
				{
					Task updatedTask = new Task(mail, title, desc, status);
					HibernateToDoListDAO.getInstance().updateTask(id, updatedTask);
					List<Task> newtask = viewTaskList(mail);
					httpServletRequest.setAttribute("RESULT", "Task No " + taskId + " :  was Edited");
					if (newtask != null)
					{
						httpServletRequest.setAttribute("tasks", newtask);
						tempURL = "/tasklist.jsp";
						return tempURL;
					}
				} catch (TaskExceptionHandler e)
				{
					httpServletRequest.setAttribute("RESULT", e.getMessage());
					httpServletRequest.setAttribute("tasks", tasks);
					tempURL = "/edittask.jsp";
					return tempURL;
				}
			} else
			{
				httpServletRequest.setAttribute("RESULT", "Task No " + taskId + " :  not Exist");
				httpServletRequest.setAttribute("tasks", tasks);
				tempURL = "/edittask.jsp";
			}

		}
		return tempURL;
	}

	/**
	 * delete task if exists
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 */
	private String deleteTask(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	{

		String tempURL = null;
		String taskId = httpServletRequest.getParameter("id");
		User currentUser = (User) session.getAttribute("user");
		String mail = currentUser.getMail();
		List<Task> tasks = viewTaskList(mail);
		if (!verification(taskId))
		{
			httpServletRequest.setAttribute("tasks", tasks);
			httpServletRequest.setAttribute("RESULT", "Please fill all the fields");
			tempURL = "/removetask.jsp";
		} else
		{
			HashMap<Integer, Integer> tasksId = (HashMap<Integer, Integer>) session.getAttribute("HASH");
			int id = 0;
			try
			{
				id = Integer.parseInt(taskId);
			} catch (NumberFormatException e)
			{
				httpServletRequest.setAttribute("RESULT", "ID field must be a Number");
				httpServletRequest.setAttribute("tasks", tasks);
				tempURL = "/removetask.jsp";
				return tempURL;
			}
			if (tasksId.containsKey(id))
			{
				id = tasksId.get(id);
				try
				{
					HibernateToDoListDAO.getInstance().deleteTask(mail, id);
					List<Task> newtask = viewTaskList(mail);
					httpServletRequest.setAttribute("RESULT", "Task No " + taskId + " :  was Removed");
					if (newtask != null)
					{
						httpServletRequest.setAttribute("tasks", newtask);
						tempURL = "/tasklist.jsp";
						return tempURL;

					}
				} catch (TaskExceptionHandler e)
				{
					if (tasks != null)
					{
						httpServletRequest.setAttribute("RESULT", e.getMessage());
						httpServletRequest.setAttribute("tasks", tasks);
						tempURL = "/removetask.jsp";
						return tempURL;
					}
				}

			} else
			{

				httpServletRequest.setAttribute("RESULT", "There is no such an ID : " + taskId);
				httpServletRequest.setAttribute("tasks", tasks);
				tempURL = "/removetask.jsp";
			}
		}

		return tempURL;
	}



}