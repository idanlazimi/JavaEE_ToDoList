<%@ page language="java" 
		contentType="text/html; charset=UTF-8"
    	pageEncoding="UTF-8"
    	import="java.util.* , com.shenkar.hibernate.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Delete task</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="../css/ourcss.css">
 		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
 		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
	</head>
	<body>
	
		<div class="container">
			<h1 class="text-center">Delete Task</h1>
			<a href="tasklist"><span class="glyphicon glyphicon-menu-left"></span></a>
			<br><br>
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
			<br><br>
			<form action="delete" method="POST">
				<label>Task ID  <input type="text" class="form-control" name="id"></label>
				<br><br>
				
				<div class="input-group">
				<button type="submit" name="delete" class="btn btn-primary">Go</button>
				</div>
	
			</form>
			<hr>
			
			
			<%
				String result = (String) request.getAttribute("RESULT");
				if(result != null) {
					
				%>
					<div class="alert alert-danger">
	  					<strong>ERROR!</strong>
	  				<%	
						out.println(" " + result);	
						}
					%>
  				
					</div>
		</div>
	</body>
</html>