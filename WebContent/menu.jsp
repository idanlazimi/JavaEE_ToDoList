<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.* , com.shenkar.hibernate.model.* , com.shenkar.hibernate.controller.* "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ToDoList</title>
		 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 		 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="../css/ourcss.css">
		<style>
		</style>
	</head>
	<body>
			<div class="container-fluid">
		
			<%
				Cookie[] menuCookie = request.getCookies();	
				User user = (User)session.getAttribute("user");
				String fname = user.getFirstname();
				out.write("<h1 class='text-center'> Homepage</h1>");
				//out.write("<h1 class='text-center'> Greetings  " + fname + " ! Good to have you here..</h1>");
				//request.setAttribute("firstname", fname);
			%>
			
			<br>
						
			
			<ul class="nav nav-pills dropdown">
			  <li role="presentation" class="active">
			  	<a href="menu">Homepage </span></a>
			  </li>
			  
			  <li class="dropdown">
			  	<a class="dropdown-toggle" data-toggle = "dropdown" href="taskslist">Tasks
			  		 <span class="caret"></span>
			  	</a>
			  	
			  	<ul class="dropdown-menu">
			  		<li><a href="taskslist">Your Tasks</a></li>
        	    	<li><a href="addTask">Add Task</a></li>
            		<li><a href="removetask">Delete task</a></li>
            		<li><a href="edittask">Modify task</a></li> 
        	 	</ul>
        	 	
			  </li>

			  <li role="presentation">
			  	<a href="about">Who Developed This? </a>
			  </li>
			  <li role="presentation">
			  	<a href="logout">Sign Out</a>
			  </li>
  			  <li role="presentation">
			  	<a href="ContactUS">Feedback</a>
			  </li>
			  
			</ul>
			<br><br>
			
			<div class="text-center">
			
			<%	
				String mail = user.getMail();
				String lanme = user.getLastname();
				out.write("<h4 class='text-center'> " + fname +" " + lanme + " </h4>");
				out.write("<h4 class='text-center'> " + mail + " </h4>");
				request.setAttribute("firstname", fname);
			%>
								
			</div>
			
		</div>
		<img >
	
	</body>
</html>