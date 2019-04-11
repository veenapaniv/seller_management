<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Add Products</title>
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
			<div>
				<button type="button" id="backBtn" class="btn btn-default">Go back to Inventory</button>
				<h3>Add Product</h3>
			</div>
			<form action="/addProduct" method="post"
			modelAttribute="inventory" id="productForm">
				<div class="form-group">
					<form:label path = "id">Product :<span class="asterisk">*</span></form:label>
					<input type="file" class="" id="file" name="file" path="file">
				</div>
				<div class="form-group">
					<label for="stock">Stock <span class="asterisk">*</span></label>
					<input type="text" class="form-control" class="" name = "quantity" id="quantity" path="quantity">
					<!-- <input type="text" class="form-control" id="stock" required>-->
				</div>
				<div class="form-group">
					<label for="stock">Product Name <span class="asterisk">*</span></label>
					<input type="text" class="form-control" class="" name = "productName" id="productName" path="productName">
					<!-- <input type="text" class="form-control" id="stock" required>-->
				</div>
			    <div class="form-group">
				<label for="channels">Choose channels <span class="asterisk">*</span></label>
						<select multiple class="form-control" id="channels" required name="channels" path="channels">
							<option>Amazon</option>
							<option>Wayfair</option>
							<option>Etsy</option>
							<option>Shipt</option>
							<option>Walmart</option>
						</select>
			    </div>
				<div class="form-group">
					<label for="amount">Amount <span class="asterisk">*</span></label>
					<input type="text" class="form-control" id="amount" required name="amount" path="amount">
				</div>
   			    <div class="form-group">
					<label for="shipping">Shipping <span class="asterisk">*</span></label>
					<input type="text" class="form-control" id="shipping" required name="shipping" path="shipping"/>
				</div>
				<input type="SUBMIT" class="btn btn-default" value="Submit" />
				<input type="button" id="resetBtn" class="btn btn-default" value="Reset"/>
			</form>
		</div>
		<div class="seller-footer">
			<div class="seller-container" style="padding-top: 20px;">Copyright &copy; 2018 Seller Management Cooperation</div>
		</div>
	</body>
</html>