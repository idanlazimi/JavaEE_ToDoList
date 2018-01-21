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
			<h1 class="text-center">About</h1>
			<br>
			<ul class="nav nav-pills">
			  <li role="presentation">
			  	<a href="menu">Menu <span class="glyphicon glyphicon-menu-hamburger"></a>
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
			  <li role="presentation" class="active">
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
			<br>
			<p>Hi, this is an easy and fun way to manage, edit and maintain your daily, weekly or even monthly tasks, the app allows you to add, edit and delete task and keep tracking them as you go further on your TODO list</p><br>
			<p>We are Shlomi, Ben and Oree, we are students on our third year at the Software Engineering department at Shenkar, this application developing is part of our final project at Java EE course under the escort of the instructor Life Michael.</p>
			
			<p>Here is all the files needed to make your own ToDo LIST.</p>
			
		<p>
			Features:
			<br>
				Welcome message includes user name presented to the user 
				User interface developing using bootstrap and Java Bean
			<br>
			Explaining the use of the system:
			<br> 
			1. sign-in- type in a valid email address and choose a unique yet memorable password.
			<br>
			2. navigation to a task in the menu- you can add, edit or remove a task.
			<br>
    			# Add new task- you can add a new task which will get a unique ID and you will be ask to provide a title and description for your new test- now you have another thing to do!
    		<br>	
    			# Edit- here you would be able to edit and rename one of the above fields (title, description)
    		<br>	
    			# Remove- delete task while using its unique identifier.
			<br>	
				Things you can’t do:
			<br>  
    			Delete or edit a task that does not exist.
    		<br>
    		* you will receive a green notification which indicates that task was added successfully.
    		<br>	 
    		* you can view a list showing all your current tasks.
    		<br>
			4. logout - you can logout and login with another user or with the same one while your tasks are saved.  
			<br>
			Enjoy and track your tasks.
			<br> 
		</p>

		</div>
	</body>
</html>