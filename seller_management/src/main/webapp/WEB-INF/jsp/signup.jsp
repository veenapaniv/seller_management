<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Registration</title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
      <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
      <link rel="stylesheet" href="css/login.css">
      <link rel="stylesheet" href="css/dashboard.css">
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
      <div class="container signup-form">
         <h4 class="registration-heading">Please complete the registration to login!</h4>
         <div class="panel panel-default">
            <div class="panel-body">
               <form action="/signup" method="POST">
                  <div class="form-group">
                     <label for="emailId">Username:</label>
                     <input type="email" class="form-control" id="emailId" placeholder="Enter your email" name="emailId">
                  </div>
                  <div class="form-group">
                     <label for="password">Password:</label>
                     <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
                  </div>
                  <div class="form-group">
                     <label for="firstname">FirstName:</label>
                     <input type="text" class="form-control" id="firstname" placeholder="Enter firsname" name="firstname">
                  </div>
                  <div class="form-group">
                     <label for="lastname">LastName:</label>
                     <input type="text" class="form-control" id="lastname" placeholder="Enter lastname" name="lastname">
                  </div>
                  <div class="form-group">
                     <label for="phone">Phone:</label>
                     <input type="tel" class="form-control" id="phone" placeholder="Enter phone number" name="phone">
                  </div>
                  <div class="form-group">
                     <label for="address">Address:</label>
                     <input type="text" class="form-control" id="address" placeholder="Enter your address" name="address">
                  </div>
                  <div class="col-md-12 text-center"> 
                     <button id="signup-submit" name="signup-submit" class="btn btn-primary">Register</button> 
                  </div>
               </form>
            </div>
         </div>
      </div>
   </body>
</html>