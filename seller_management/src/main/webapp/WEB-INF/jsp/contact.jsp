<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Contact Us</title>
		<link rel="stylesheet" href="../css/dashboard.css" />
		<link rel="stylesheet" href="../css/bootstrap-theme.min.css" />
		<link rel="stylesheet" href="../css/bootstrap.min.css" />
		<link rel="stylesheet" href="../css/contact.css" />
		<script src="../js/third-party/jquery-2.2.4.min.js"></script>
		 <style>              
        .seller-contactus{
        	background-image:url(../images/contactus.jpg);
        	background-repeat : no-repeat;
			background-size: cover;
        }
    </style>
	</head>
	<body>
		<jsp:include page="hamburger.jsp" />
		<div class="container seller-contactus">
			<div style="text-align:center">
				<h2 id="header"></h2>
				<p></p>
			</div>
			<div class="row contact">
				<h3 class="text-center">
					<a href="#">
						
					</a>Address
				</h3>
					<div id="address" class="text-center">
						<h4>${contact.address1}</h4>
						<h4>${contact.address2}</h4>
						<h4>${contact.address3}</h4>
					</div>
				<br/>
				
				<h3 class="text-center">
					Call us
				</h3>
				<div class="text-center" id="contactInfo">
				<c:forEach var="contactDetails" items="${contact.contact}">
					<div class="column">
						<h4>${contactDetails.name}</h4>
						<h5>${contactDetails.position}</h5>
						<h5>${contactDetails.phone}</h5>
					</div>
				</c:forEach>
				</div>
				<br/>
				<div class="seller-email">
					<h4 class="text-center">
						<span class="glyphicon glyphicon-envelope"></span> Email us at
					</h4>
					<h4 class="text-center">
						<a id="email" href="mailTo:${contact.email}">${contact.email}</a>
					</h4>
				</div>		
			</div>
		</div>
		<div class="seller-footer">
			<div class="seller-container" style="padding-top: 20px;">Copyright &copy; 2018 Seller Management Cooperation</div>
		</div>
	</body>
</html>
