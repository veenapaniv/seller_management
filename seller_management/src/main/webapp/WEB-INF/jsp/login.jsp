<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
   	  <%@ page isELIgnored="false" %>	
      <title>Seller Management</title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="css/dashboard.css" />
      <link rel="stylesheet" href="css/bootstrap-theme.min.css" />
      <link rel="stylesheet" href="css/bootstrap.min.css" />
      <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
      <link rel="stylesheet" href="css/login.css">
      <script src="js/login.js"></script>
      <script src="js/signup.js"></script>
   </head>
   <body>
      <div class="seller-header">
         <div class="seller-container">
            <nav class="seller-nav">
               <span class="seller-heading">SELLER MANAGEMENT</span>
            </nav>
         </div>
      </div>
      <div class="container login-form">
         <div class="panel panel-default">
            <div class="panel-body">
               <div class="logo-div" align="center"><img src="images/logo.png" class="img-rounded" id="logo" alt="SellerManagement"></div>
               <p >
				   ${errorMsg}  
               </p>
               <form action="/login" method="POST">
                  <div class="form-group">
                     <label for="email">Email:</label>
                     <input type="email" class="form-control" required id="email" placeholder="Enter email" name="email" value="${username}">
                  </div>
                  <div class="form-group">
                     <label for="pwd">Password:</label>
                     <input type="password" class="form-control" required id="pwd" placeholder="Enter password" name="pwd" value="${password }">
                  </div>
                  <div class="form-group">
                  	 <input type="checkbox"  name="remember">
                     <label for="pwd">Remember Me:</label>
                  </div>
                  <div class="col-md-12 text-center"> 
                     <button id="login-submit" name="login-submit" class="btn btn-primary">Login</button> 
                  </div>
               </form>
               <form id="signUpForm" action="sign_up_action" method="GET">
               	 <div class="form-group">
                     <p><span><a href="sign_up_action" id="signup">Not registered yet? Click here to register!</a></span> </p>                   
                  </div>
               </form>
            </div>
         </div>
      </div>
   </body>
</html>