<%@ page language="java" 
	import="java.util.* , com.shenkar.hibernate.model.*"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Add Task</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="../css/ourcss.css">
 		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
 		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
	</head>
	<body>
		<div class="container">
			<h1 class="text-center">Add Task</h1>
			<br>
			<a href="tasklist"><span class="glyphicon glyphicon-menu-left"></span></a>
			<br><br>
			<form action="insert" method="POST">
				<label>Title <input type="text" class="form-control" name="title"></label>
				<br>
				<label>Description <input type="text" class="form-control" name="taskBody"/></label>
				<br>
				<label>Status of the task <input type="text" class="form-control" name="status"/></label>
				<br>
				<br>
				
				<div class="input-group">
				<button type="submit" name="insert" class="btn btn-primary">Apply</button> 
				</div>
			</form>
			
		</div>
		
	</body>
</html>