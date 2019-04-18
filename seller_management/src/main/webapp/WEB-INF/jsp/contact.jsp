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
		<script src="../js/contact.js"></script>
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
					<div id="address" class="text-center"></div>
				<br/>
				
				<h3 class="text-center">
					Call us
				</h3>
				<div class="text-center" id="contactInfo"></div>
				<br/>
				<div class="seller-email">
					<h4 class="text-center">
						<span class="glyphicon glyphicon-envelope"></span> Email us at
					</h4>
					<h4 class="text-center">
						<a id="email"></a>
					</h4>
				</div>		
			</div>
		</div>
		<div class="seller-footer">
			<div class="seller-container" style="padding-top: 20px;">Copyright &copy; 2018 Seller Management Cooperation</div>
		</div>
	</body>
</html>
