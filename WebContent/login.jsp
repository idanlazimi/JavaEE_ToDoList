<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ToDoList Login</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="../css/ourcss.css">
 		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
 		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
	</head>
	<body background-image:url("../images/bg.png") !important;>
	
<nav class="navbar-default navbar navbar-fixed-top">
  <div class="collapse navbar-collapse">
    
  <div class="form-group">
					<form  class="form-inline" action="login" method="POST">
					    <button type="submit" name="login" class="login-btn btn btn-primary">LOGIN</button>	
					
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							<input id = "email"  type="text" name="mail" size="50" class= "form-control"  placeholder="example@gmail.com" required autofocus >
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
							<input id="password" type="password" size="50" class="form-control" name="pass" placeholder="Password" required>
						</div>
					</form>
				</div>  
    </div>
</nav>

		<div class="jumbotron text-center">
				<div class="form-group">
					
				<h1>Sign up</h1><br>
						<form action="newSignup" method="POST">
						
							<div class="form-group">
								<label>First name<input type="text" name="firstname" class="form-row form-control" placeholder="First name" required autofocus ></label>
							</div>
							
							<div class="form-group">
								<label>Last name<input type="text" name="lastname" class="form-row form-control" placeholder="Last name" required autofocus ></label>
							</div>
							
							
							<div class="form-group">
								<label>E-mail<input type="text" name="mail" class="form-row form-control" placeholder="example@gamil.com" required autofocus ></label>
							</div>
							<div class="form-group">
								<label>Password<input type="password" class="form-row form-control" name="pass" placeholder="Password" required></label>
							</div>
							
							<div class="form-group">
							<button type="submit" name=signUp class="login-btn btn btn-primary">SIGNUP</button>
							</div>
							
						</form>
					</div>
					
			<%
				String result = (String) request.getAttribute("RESULT");
				if(result != null) {
					out.println("<h2 style='color:#e75853'>" + result + "</h2>");	
				}
			%>	
<div class="jumbotron text-center">
  <h3 class="text-center">Feedback?</h3>
  
  <div class="row test">
      <div class="row">
        <div class="col-sm-6 form-group">
        <form action= "contact" method="POST">
          <input class="form-control" id="name" name="loginname" placeholder="Name" type="text" required>
        </div>
        <div class="col-sm-6 form-group">
          <input class="form-control" id="email" name="loginemail" placeholder="Email" type="email" required>
        </div>
      </div>
      <textarea class="form-control" id="comments" name="logincomments" placeholder="Comment" rows="5"></textarea>
      <div class="row">
      <br>
        <div class="col-md-12 form-group">
          <button class="btn pull-right" name = "loginsend" type="submit">Launch</button>
        </div>
      </div>
      </form>
    </div>
  </div>
</div>
		
	</div>		
			

		
	</body>
</html>