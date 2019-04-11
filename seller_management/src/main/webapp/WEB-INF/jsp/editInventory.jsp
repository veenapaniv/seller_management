<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Edit Inventory</title>
		<link rel="stylesheet" href="css/dashboard.css" />
		<link rel="stylesheet" href="css/bootstrap-theme.min.css" />
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/inventory.css"/>
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
		<div class="inventory-container edit-container">
			<div>
				<button type="button" id="backBtn" class="btn btn-default">Go back to Inventory</button>
				<h3><span class="glyphicon glyphicon-pencil"></span> &nbsp;Edit Product</h3>
			</div>
			<form>
				<div class="form-group">
					<label for="product">Product ID</label>
					<input type="text" class="form-control" id="productId" disabled = "true">
				</div>
				<div class="form-group">
					<label for="stock">Stock <span class="asterisk">*</span></label>
					<input type="text" class="form-control" id="stock">
				</div>
			    <div class="form-group">
				<label for="channels">Choose Channels <span class="asterisk">*</span></label>
						<select multiple class="form-control" id="channels">
							<option>Amazon</option>
							<option>Wayfair</option>
							<option>Etsy</option>
							<option>Shipt</option>
							<option>Walmart</option>
						</select>
			    </div>
				<div class="form-group">
					<label for="sold">Sold</label>
					<input type="text" class="form-control" id="sold" disabled = "true">
				</div>
   			    <div class="form-group">
					<label for="returned">Returned</label>
					<input type="text" class="form-control" id="returned" disabled = "true">
				</div>
				<div class="form-group">
					<label for="amount">Amount <span class="asterisk">*</span></label>
					<input type="text" class="form-control" id="amount">
				</div>
			    <div class="form-group">
					<label for="shipping">Shipping <span class="asterisk">*</span></label>
					<input type="text" class="form-control" id="shipping">
				</div>
				<button type="button" id="submitBtn" class="btn btn-info" disabled ="false">Submit</button>
			</form>
		</div>
		<div class="seller-footer">
			<div class="seller-container" style="padding-top: 20px;">Copyright &copy; 2018 Seller Management Cooperation</div>
		</div>
	</body>
</html>