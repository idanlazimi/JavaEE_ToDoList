<%@ page language="java" 
		contentType="text/html; charset=UTF-8"
    	pageEncoding="UTF-8"
    	import="java.util.* , com.shenkar.hibernate.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Manage Your Tasks</title>
		 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 		 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="../css/ourcss.css">
		</head>
		
	<body>
		<div class="container-fluid">
			<h1 class="text-center"> Task List </h1>
			<br>
			<ul class="nav nav-pills">
			  <li role="presentation">
			  	<a href="menu">Menu 
			  		<span class="glyphicon glyphicon-menu-hamburger"> </span>
			  	</a>
			  </li>
			   <li class="dropdown active"  class="active">
			  	<a  class="dropdown-toggle" data-toggle="dropdown" href="taskslist">Task list
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
			<br>
			<%
				HashMap<Integer, Integer> idHash = new HashMap();
				List <Task> tasks = (List<Task>)request.getAttribute("tasks");
				out.println("<table class='table table-striped table-hover'>");
				out.println("<thead><tr><th>Task ID</th><th>Task title</th><th>Task description</th><th>Staus of the task</th></tr></thead>");
				for(int i=0; i<tasks.size(); i++) {
					
					int colNum = i+1;
					idHash.put(colNum, tasks.get(i).getTaskID());
					out.println("<tr><td>" + colNum + "</td><td>"+
							tasks.get(i).getTitle() + "</td><td>"+ 
							tasks.get(i).getTaskDescription() + "</td><td>"+
							tasks.get(i).getStatus() + "</td></tr>");
				}
				
				session.setAttribute("HASH", idHash);
				out.println("</table>"); 
			%>
			
			<%
				String result = (String) request.getAttribute("RESULT");
				if(result != null){
					
					if (result.equals("You have alredy this task in the list"))
					{
						%>
						<div class="alert alert-danger">
		  				<strong>Oops..! </strong> 
		  				<br>
		  				<%
							out.println("Unable to insert the task since it already exists in your TODO list");
						%>
						</div>
				<% 
					}else {
				%>
					<div class="alert alert-success">
						<strong>Success!</strong>
						<%
	  						out.println("<h6>" + result + "</h6>");	
						}
				}	
			%>
					</div>
		</div>
	
	</body>
</html>