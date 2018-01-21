<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
     import="java.util.* , com.shenkar.hibernate.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Thank you!!</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="../css/ourcss.css">
 		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
 		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
	</head>
	<body>

<body>

	<h1>Thank you!</h1>
	<br>
		<a href="login">Go Back to Login Page <span class="glyphicon glyphicon-home"></span></a>
		<br><br>
		<div class="container">
			<%
				Contact con = (Contact)session.getAttribute("newcomment");
				String fname = con.getName();
				out.write("<h1 class='text-center'> Thank you " + fname + " for your comment" + "</h1>");
			%>
				
		</div>

</body>
</html>