<%@ page language="java" 
		contentType="text/html; charset=UTF-8"
    	pageEncoding="UTF-8"
    	import="java.util.* , com.shenkar.hibernate.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Modify Tasks</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="../css/ourcss.css">
 		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
 		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
	</head>
	<body>
		<div class="container">
			<h1 class="text-center">Modify Task</h1>
			<br>
			<a href="tasklist"><span class="glyphicon glyphicon-menu-left"></span></a>
			<br><br>
			
			<!-- edit task and keep database integrity -->
			<%
				HashMap<Integer, Integer> idHash = new HashMap();
				//get the list of tasks
				List <Task> tasks = (List<Task>)request.getAttribute("tasks");
				out.println("<table class='table table-striped table-hover'>");
				out.println("<thead><tr><th>Task ID</th><th>Task title</th><th>Task description</th><th>Staus of the task</th></tr></thead>");
				//iterate over the list
				for(int i=0; i<tasks.size(); i++) {
					
					int colNum = i+1;
					idHash.put(colNum, tasks.get(i).getTaskID());
					out.println("<tr><td>" + colNum + "</td><td>"+
							tasks.get(i).getTitle() + "</td><td>"+ 
							tasks.get(i).getTaskDescription() + "</td><td>"+
							tasks.get(i).getStatus() + "</td></tr>");
				}
				
				//inject information onto the web page
				session.setAttribute("HASH", idHash);
				out.println("</table>"); 
			%>
			<br><br>
			
			
			
			<form action="edit" method="POST">
				<label>ID <input type="text" class="form-control" name="id"/></label>
				<br>
				<label>Title  <input type="text" class="form-control" name="title"/></label>
				<br>
				<label>Description  <input type="text" class="form-control" name="taskbody"/></label>
				<br>
				<label>Status<input type="text" class="form-control" name="status"/></label>
				<br><br>
				<div class="input-group">
				<button type="submit" name="edit" class="btn btn-primary">Apply</button>
				</div>
				
			</form>
			<hr>
			<%
				String result = (String) request.getAttribute("RESULT");
				if(result != null) {
					
				%>
					<div class="alert alert-danger">
	  					<strong>BAD VALUE ERROR</strong>
	  				<%	
						out.println(" " + result);	
						}
					%>
  				
					</div>
		</div>
	
	</body>
</html>