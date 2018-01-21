<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.* , com.shenkar.hibernate.model.* , com.shenkar.hibernate.controller.* "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Main Menu</title>
		 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 		 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="../css/ourcss.css">
		<style>
		</style>
	</head>
	<body background-image: url(../images/menupage.jpg); >
	
			<div class="container-fluid">
		
			<%
				Cookie[] menuCookie = request.getCookies();	
				User user = (User)session.getAttribute("user");
				String fname = user.getFirstname();
				out.write("<h1 class='text-center'> Welcome  " + fname + " !</h1>");
				request.setAttribute("firstname", fname);
			%>
			
			<br>
						
			
			<ul class="nav nav-pills dropdown">
			  <li role="presentation" class="active">
			  	<a href="menu">Menu <span class="glyphicon glyphicon-menu-hamburger"></span></a>
			  </li>
			  
			  <li class="dropdown">
			  	<a class="dropdown-toggle" data-toggle = "dropdown" href="taskslist">Task list
			  		 <span class="caret"></span>
			  	</a>
			  	<ul class="dropdown-menu">
			  		<li><a href="taskslist">View your task list</a></li>
        	    	<li><a href="addTask">Add new task</a></li>
            		<li><a href="removetask">Remove task</a></li>
            		<li><a href="edittask">Edit task</a></li> 
        	 	</ul>
			  </li>

			  <li role="presentation">
			  	<a href="about">About <span class="glyphicon glyphicon-comment"></span></a>
			  </li>
			  <li role="presentation">
			  	<a href="logout">Logout <span class="glyphicon glyphicon-log-out"></span></a>
			  </li>
  			  <li role="presentation">
			  	<a href="ContactUS">Contact US <span class="glyphicon glyphicon-envelope"></span></a>
			  </li>
			  
			</ul>
			<br><br>
			
			<div class="text-center">
			
			<%	
				String mail = user.getMail();
				String lanme = user.getLastname();
				out.write("<h4 class='text-center'> Full name: " + fname +" " + lanme + " </h4>");
				out.write("<h4 class='text-center'> Your mail: " + mail + " </h4>");
				request.setAttribute("firstname", fname);
			%>
								
			</div>
			
		</div>
							<img >
	
	</body>
</html>