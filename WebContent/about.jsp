<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>About</title>
		 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 		 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  		 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="../css/ourcss.css">
 		</head>
	<body>
		<div class="container-fluid">
			<h1 class="text-center">Who Developed This?</h1>
			<br>
			<ul class="nav nav-pills">
			  <li role="presentation">
			  	<a href="menu">Homepage </a>
			  </li>
			   <li class="dropdown">
			  	<a class="dropdown-toggle" data-toggle = "dropdown" href="taskslist">Task list
			  		 <span class="caret"></span>
			  	</a>
			  	<ul class="dropdown-menu">
			  		<li><a href="taskslist">Tasks</a></li>
        	    	<li><a href="addTask">Add  task</a></li>
            		<li><a href="removetask">Delete task</a></li>
            		<li><a href="edittask">Modify task</a></li> 
        	 	</ul>
			  </li>
			  <li role="presentation" class="active">
			  	<a href="about">Who Developed This?</a>
			  </li>
			  <li role="presentation">
			  	<a href="logout">Sign Out </a>
			  </li>
			  <li role="presentation">
			  	<a href="ContactUS">Feedback </a>
			  </li>
			</ul>
			<br>
			<p>
				This project was developed as part of the JavaEE course in Shenkar College.
				This application can maintain tasks, modify, add and remove them. We used MySQL server to hold the data
				and Bootstrap for the view. The project is written under MVC design pattern and uses Hibernate's ORM.
				<br>
				All design rights go to whoever made this Bootstrap template. He was First on google.
				<br><br><br><br>
				<h3>Cheers, Shay Rubach & Idan Lazimi & Ran Shoshan.</h3>
			</p>

		</div>
	</body>
</html>