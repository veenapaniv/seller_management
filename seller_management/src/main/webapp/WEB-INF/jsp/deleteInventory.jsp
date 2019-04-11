<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Delete Product</title>
		<link rel="stylesheet" href="../css/dashboard.css" />
		<link rel="stylesheet" href="../css/bootstrap-theme.min.css" />
		<link rel="stylesheet" href="../css/bootstrap.min.css" />
		<script src="../js/third-party/jquery-2.2.4.min.js"></script>
		<script src="../js/third-party/bootstrap.min.js"></script>
		<link rel="stylesheet" href="../css/inventory.css"/>
		<script type="text/javascript" src="../js/products.js"></script>
	</head>
	<body>
		<div class="seller-header">
        <div class="seller-container">
            <nav class="seller-nav">
                <span class="seller-heading">SELLER MANAGEMENT</span>
                <span class="seller-hamburger" id="openHamburger">&#9776;</span>
            </nav>
        </div>
        <div id="hamburgerClass" class="hamburger">
            <a href="javascript:void(0)" class="closebtn" id="closeHamburger">&times;</a>
            <a href="dashboard.html">Dashboard</a>
            <a href="channels.html">Channels</a>
            <a href="inventory.html">Inventory</a>
            <a href="catalogue.html">Catalog</a>
            <a href="contact.html">Contact Us</a>
        </div>
    </div>
		<div  class="inventory-container edit-container">
			<form action="/deleteInventory" method="post" id="productForm">
				<div class="form-group">
					<label>Do you really want to delete this product?</label>
				</div>
				<input type="SUBMIT" class="btn btn-default" value="Submit" />
				<input type="button" id="resetBtn" class="btn btn-default" value="Cancel"/>
			</form>
		</div>
		<div class="seller-footer">
			<div class="seller-container" style="padding-top: 20px;">Copyright &copy; 2018 Seller Management Cooperation</div>
		</div>
	</body>
</html>