<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="css/login.css">
  <script src="js/login.js"></script>
  <script src="js/signup.js"></script>
</head>
<body>
	<div class="container login-form">
	    
	 <div class="panel panel-default">
	    <div class="panel-body">
	            <div class="logo-div" align="center"><img src="images/logo.png" class="img-rounded" id="logo" alt="SellerManagement"></div>
	            <p id="error-message">${errorMessage}</p>
	            <form action="/login" method="POST">
		            <div class="form-group">
		              <label for="email">Email:</label>
		              <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
		            </div>
		            <div class="form-group">
		              <label for="pwd">Password:</label>
		              <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
		            </div>
		            <div class="form-group">
		            	<p><span><a href="#" id="signup">Sign Up</a></span> </p>
		                <!-- <p><span><a href="#">Forgot Password</a></span> </p> -->
		            </div>
		            <div class="col-md-12 text-center"> 
		                <button id="login-submit" name="login-submit" class="btn btn-primary">Submit</button> 
		            </div> 
		         </form>           
	    </div>
	  </div>  
	</div>
</body>
</html>