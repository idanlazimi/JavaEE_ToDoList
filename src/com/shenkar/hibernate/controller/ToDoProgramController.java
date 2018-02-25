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
import com.shenkar.hibernate.model.ContactExceptionHandler;
import com.shenkar.hibernate.model.HibernateToDoListDAO;
import com.shenkar.hibernate.model.Task;
import com.shenkar.hibernate.model.TaskExceptionHandler;
import com.shenkar.hibernate.model.User;
import com.shenkar.hibernate.model.UserExceptionHandler;

/**
 * Servlet implementation class ToDoProgramController
 */

@WebServlet("/usercontroller/*")
public class ToDoProgramController extends HttpServlet {
	//whattttttttttttttttttttt
	private static final long serialVersionUID = 1L;
    private HttpSession session; 
    private RequestDispatcher dispatcher;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToDoProgramController() {
        super();
        dispatcher  = null;
    }
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuffer buf = request.getRequestURL();
		String url = buf.toString();
		
		//checking if the user is login
		this.session = request.getSession(true); 
		User user = (User) session.getAttribute("user");
		
		if (user == null) {
			{
				pagingManager(request, response, "/"+"login");
			}
		} else {
			pagingManager(request, response, url);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.session = request.getSession(true);
		StringBuffer buf = request.getRequestURL();
		String url = buf.toString();
		String tempURL = null;
		
		//Login
		if (url.endsWith("login")) {
			tempURL = loginProcess(request, response);
		}else if(url.endsWith("ContactUS"))
		{
			tempURL = "ContactUS.html";

		//New sign up
		} else if (url.endsWith("newSignup")) {
			tempURL = signupProcess(request, response);
			
		//Insert new Task
		} else if(url.endsWith("insert")){
			tempURL = addNewTask(request, response);
			
		//Remove the requested Task
		} else if (url.endsWith("delete")) {
			tempURL = deleteTask(request, response);
			
		//Edit the requested Task	
        } else if (url.endsWith("edit")) {
        	tempURL = updateTask(request, response);

        //GET a comment from the login page	
	 	} else if (url.endsWith("contact")) {
	 		try {
				tempURL = insertComment(request, response);
			} catch (ContactExceptionHandler e) {
				e.printStackTrace();
			}
	 	}
	 	else
	 	{
	 		tempURL = "contactbean.jsp";
	 	}
		//Forward to the next page
		nextState(request, response,"/"+tempURL);
	}

	/**
	 * Called from inside the get Method, directing to the correct JSP file
	 * @param url
	 * @param mail
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void pagingManager(HttpServletRequest request, HttpServletResponse response, 
			String url) throws ServletException, IOException {
		
		User currentUser = (User) session.getAttribute("user");
		
		//The user chose the logout option
		if (url.endsWith("logout")) {
			session.invalidate();
			nextState(request, response, "/login.jsp");
		
		}
		else if(url.endsWith("ContactUS"))
		{
			nextState(request, response, "/ContactUS.html");
			
		//Go to LOGIN JSP file
		} else if (url.endsWith("login")) {
			nextState(request, response, "/login.jsp");
			
		//Go to ABOUT JSP file
		} else if (url.endsWith("about")) {
			nextState(request, response, "/about.jsp");
		
		//Go to Task JSP file
		} else if (url.endsWith("list")) {
			String email = currentUser.getMail();
			List<Task> tasks = viewTaskList(email);
			if (tasks != null) {
				request.setAttribute("tasks", tasks);
				nextState(request, response, "/tasklist.jsp");
			} else {
				request.setAttribute("ERROR", "");
				nextState(request, response, "/error.jsp");
			}
		}
		//editing exist task
		else if (url.endsWith("edittask")) {
			String email = currentUser.getMail();
			List<Task> tasks = viewTaskList(email);
			if (tasks != null) {
				request.setAttribute("tasks", tasks);
				nextState(request, response, "/edittask.jsp");
			}else{
				request.setAttribute("ERROR", "");
				nextState(request, response, "/error.jsp");
			}
			
		//adding new task
		} else if (url.endsWith("addTask")) {
			nextState(request, response, "/addTask.jsp");
			
		//removing exist task
		} else if (url.endsWith("removetask")) {
			String email = currentUser.getMail();
			List<Task> tasks = viewTaskList(email);
			if (tasks != null) {
				request.setAttribute("tasks", tasks);
				nextState(request, response, "/removetask.jsp");
			}else{
				request.setAttribute("ERROR", "");
				nextState(request, response, "/error.jsp");
			}
				
			
		//Go to MENU JSP file
		} else {
			nextState(request, response, "/menu.jsp");
		}
	}
	
	/**
	 * Go to the next state requested page (URL)
	 * @param request
	 * @param response
	 * @param url
	 */
	private void nextState(HttpServletRequest request, 
			HttpServletResponse response, String url) throws ServletException, IOException {
		dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	
	/**
	 * getting the current user and return his Todo list
	 * @param userName
	 * @return List<Task>
	 */
	public List<Task> viewTaskList(String userName){
		List<Task> tasks = null;
		try {
			tasks = HibernateToDoListDAO.getInstance().getTasks(userName);
		} 
		catch (TaskExceptionHandler e) {
			tasks = null;			
		}
		return tasks;
	}
	
	/**
	 * Validation check of user input
	 * @param ... String
	 * @return boolean
	 */
	public boolean verification(String... strings){
		boolean flag = true;
	    for(String str : strings){
	    	if(str.isEmpty()) flag = false;
	    }
	    return flag;
	}
	
	/**
	 * Validation checked of email address
	 * @param emailAddress
	 * @return boolean
	 */
	public boolean validtionCheck(String emailAddress) {
	    return emailAddress.contains(" ") == false && emailAddress.matches(".+@.+\\.[a-z]+");
	} 
	
	
	
	/******The following Methods are called from the doPost method******/
	
	
	/**
	 * Make a connect with the DB with the values from the user 
	 * @param request
	 * @param response
	 */
	private String loginProcess(HttpServletRequest request, HttpServletResponse response) {
		
		String tempURL = null;
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		if(!verification(mail, pass)) {
			request.setAttribute("RESULT", "Please fill all The fields");
			tempURL = "/login.jsp";
		} else {
			User currentUser = null;
			try {
				currentUser = HibernateToDoListDAO.getInstance().getUser(mail, pass);
				if (currentUser != null && pass.equals(currentUser.getPassword())) {
					session.setAttribute("user", currentUser);
					tempURL = "/menu.jsp";
				}else {
					request.setAttribute("RESULT", "unable to login, Please signup first or verify mail and password");
					tempURL = "/login.jsp";
				}
			} catch (UserExceptionHandler e) {
				request.setAttribute("RESULT", e.getMessage());
				tempURL = "/login.jsp";
			}
		}
		return tempURL;
	}
	
	private String insertComment(HttpServletRequest request, HttpServletResponse response) throws ContactExceptionHandler {
		String tempURL=null;
		String name = request.getParameter("loginname");
		String mail = request.getParameter("loginemail");		
		String comment = request.getParameter("logincomments");
		if(!verification(name, mail,comment)) {
			request.setAttribute("RESULT", "Please fill all The fields for correctly Sign up");
			tempURL = "/login.jsp";
		} else if(!validtionCheck(mail)){
			request.setAttribute("RESULT", "Please check again the Your Email address");
			tempURL = "/login.jsp";
		}
		else
		{
			Contact contact = new Contact(name, mail, comment);
			Contact newComment = null;
			newComment = HibernateToDoListDAO.getInstance().addNewComment(contact);
			if (newComment != null) {
				session.setAttribute("newcomment", newComment);
				tempURL = "/comment.jsp";
			} else {
				tempURL = "/login.jsp";
			}
		}
		
		return tempURL;
	}	
	
	/**
	 * Sign Up for a new account with the values from the user 
	 * @param request
	 * @param response
	 */
	private String signupProcess(HttpServletRequest request, HttpServletResponse response) {
		String tempURL = null;
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		
		if(!verification(mail, pass, fname, lname)) {
			request.setAttribute("RESULT", "Please fill all The fields for correctly Sign up");
			tempURL = "/login.jsp";
		} else if(!validtionCheck(mail)){
			request.setAttribute("RESULT", "Please check again the Your Email address");
			tempURL = "/login.jsp";
		} else{
			User newUser = new User(mail, pass, fname, lname);
			User currentUser = null;
			try {
				currentUser = HibernateToDoListDAO.getInstance().addNewUser(newUser);
				if (currentUser != null) {
					session.setAttribute("user", currentUser);
					//implements for cookie
					Cookie fnameCookie = new Cookie("userName", currentUser.getFirstname());
					fnameCookie.setMaxAge(-1);
					response.addCookie(fnameCookie);
					tempURL = "/menu.jsp";
					request.setAttribute("firstname", fname);
				} else {
					tempURL = "/login.jsp";
				}
			} catch (UserExceptionHandler e) {
				request.setAttribute("RESULT", e.getMessage());
				tempURL = "/login.jsp";
			}
		}
		return tempURL;
	}
	
	/**
	 * checking task existence, if yes - edit it 
	 * @param request
	 * @param response
	 */
	private String updateTask(HttpServletRequest request, HttpServletResponse response) {
		String tempURL = null;
		String taskId = request.getParameter("id"); 
		String title = request.getParameter("title");
		String sts = request.getParameter("status");
		String description = request.getParameter("taskbody");
		User currentUser = (User) session.getAttribute("user");
		String mail = currentUser.getMail();
		List<Task> tasks = viewTaskList(mail);
		
		if(!verification(taskId, title, description, sts)) {
			request.setAttribute("RESULT", "Please fill all the fields");
			request.setAttribute("tasks", tasks);
			tempURL = "/edittask.jsp";
		} else {
			
			HashMap<Integer, Integer> tasksId = (HashMap<Integer, Integer>) session.getAttribute("HASH");
			//check if taskId is a valid integer
			int id = 0;
			try {
				id = Integer.parseInt(taskId);
			} catch(NumberFormatException e) {
				request.setAttribute("RESULT", "ID field must be a Number");
				request.setAttribute("tasks", tasks);
				tempURL = "/edittask.jsp";
				return tempURL;
			}
			if ( tasksId.containsKey(id)) {
				id = tasksId.get(id);
				try {
					Task updatedTask = new Task(mail, title, description, sts);
					HibernateToDoListDAO.getInstance().updateTask(id, updatedTask);
					List<Task> newtask = viewTaskList(mail);
					request.setAttribute("RESULT", "Task No " + taskId + " :  was Edited");
					if (newtask != null){
						request.setAttribute("tasks", newtask);
						tempURL = "/tasklist.jsp";
						return tempURL;
					}
				} 
				catch (TaskExceptionHandler e) {
					request.setAttribute("RESULT", e.getMessage());
					request.setAttribute("tasks", tasks);
					tempURL = "/edittask.jsp";
					return tempURL;
				}
			} else {
				request.setAttribute("RESULT", "Task No " + taskId + " :  not Exist");
				request.setAttribute("tasks", tasks);
				tempURL = "/edittask.jsp";
			}

		}	
		return tempURL;
	}

	/**
	 * checking task existence, if yes - delete it
	 * @param request
	 * @param response
	 */
	private String deleteTask(HttpServletRequest request, HttpServletResponse response) {
		
		String tempURL = null;
		String taskId = request.getParameter("id");
		User currentUser = (User) session.getAttribute("user");
		String mail = currentUser.getMail();
		List<Task> tasks = viewTaskList(mail);
		if(!verification(taskId)) {
			request.setAttribute("tasks", tasks);
			request.setAttribute("RESULT", "Please fill all the fields");
			tempURL = "/removetask.jsp";
		} else {
			HashMap<Integer, Integer> tasksId = (HashMap<Integer, Integer>) session.getAttribute("HASH");
			int id = 0;
			try {
				id = Integer.parseInt(taskId);
			} catch(NumberFormatException e) {
				request.setAttribute("RESULT", "ID field must be a Number");
				request.setAttribute("tasks", tasks);
				tempURL = "/removetask.jsp";
				return tempURL;
			}
			if(tasksId.containsKey(id)){
				id = tasksId.get(id);
				try {
					HibernateToDoListDAO.getInstance().deleteTask(mail, id);
					List<Task> newtask = viewTaskList(mail);
					request.setAttribute("RESULT", "Task No " + taskId + " :  was Removed");
					if (newtask != null){
						request.setAttribute("tasks", newtask);
						tempURL = "/tasklist.jsp";
						return tempURL;

					}
				} 
				catch (TaskExceptionHandler e) {
					if (tasks != null){
						request.setAttribute("RESULT", e.getMessage());
						request.setAttribute("tasks", tasks);
						tempURL = "/removetask.jsp";
						return tempURL;
					}	
				}

			} else {

				request.setAttribute("RESULT", "There is no such an ID : " + taskId);
				request.setAttribute("tasks", tasks);
				tempURL = "/removetask.jsp";
			}
		}
		
		return tempURL;
	}


	/**
	 * Adding new Task to the DB
	 * @param request
	 * @param response
	 */
	private String addNewTask(HttpServletRequest request, HttpServletResponse response) {
		String tempURL = null;
		String title = request.getParameter("title");
		String sts = request.getParameter("status");
		String description = request.getParameter("taskBody");
		if(!verification(title, description, sts)) {
			request.setAttribute("RESULT", "Please fill all the fields");
			tempURL = "/addTask.jsp";
		} else {
			User currentUser = (User) session.getAttribute("user");
			String mail = currentUser.getMail();
			Task newTask = new Task(mail, title, description, sts);
			try {
				HibernateToDoListDAO.getInstance().addNewTask(newTask);
				request.setAttribute("RESULT", "Task  : "  + title + " was Added");
			} 
			catch (TaskExceptionHandler e) {
				request.setAttribute("RESULT", e.getMessage());
				tempURL = "/addTask.jsp";
			}
			
			List<Task> tasks = viewTaskList(mail);
			if (tasks != null) {
				request.setAttribute("tasks", tasks);
				tempURL = "/tasklist.jsp";
			} else {
				request.setAttribute("RESULT", "Sorry, it seems that there is an error in the list of your assignments, please try again");
				tempURL = "/menu.jsp";
			}
		}
		return tempURL;
	}
		
}