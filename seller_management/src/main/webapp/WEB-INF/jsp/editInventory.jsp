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
      <jsp:include page="hamburger.jsp" />
      <div class="inventory-container edit-container">
         <div>
            <form action="inventory">
               <button type="submit" id="backBtn" class="btn btn-default">Go back to Inventory</button>
            </form>
            <h3><span class="glyphicon glyphicon-pencil"></span> &nbsp;Edit Product</h3>
         </div>
         <form action="/editInventory" method="post"
            modelAttribute="inventory" id="productForm">
            <c:forEach var="inventory" items="${inventories}">
               <div class="form-group">
                  <label for="productId">Product ID</label>
                  <input type="text" class="form-control" name = "productId" id="productId" path="productId" value="${inventory.productId}" >
               </div>
               <div class="form-group">
                  <label for="productName">Product Image</label>
                  <img width=100 height=100 border = 3 src="data:image/jpg;base64,${inventory.image}"/>
                  <!-- <input type="text" class="form-control" id="stock" required>-->
               </div>
               <div class="form-group">
                  <form:label path = "id">New Product Image:<span class="asterisk">*</span></form:label>
                  <input type="file" class="" id="file" name="file" path="file">
               </div>
               <div class="form-group">
                  <label for="productName">Product Name <span class="asterisk">*</span></label>
                  <input type="text" class="form-control" class="" value="${inventory.productName}" name = "productName" id="productName" path="productName">
                  <!-- <input type="text" class="form-control" id="stock" required>-->
               </div>
               <div class="form-group">
                  <label for="stock">Stock <span class="asterisk">*</span></label>
                  <input type="text" class="form-control" name = "quantity" id="quantity" path="quantity" value="${inventory.quantity}">
               </div>
               <div class="form-group">
                  <label for="selected">Selected Channels</label>
                  <ul>
                     <c:forEach var="channel" items="${inventory.channels}">
                        <li>${channel}</li>
                     </c:forEach>
                  </ul>
               </div>
               <div class="form-group">
                  <label for="channels">Choose Channels</label>
                  <select multiple class="form-control" name = "channels" id="channels" path="channels">
                     <option>Amazon</option>
                     <option>Wayfair</option>
                     <option>Etsy</option>
                     <option>Shipt</option>
                     <option>Walmart</option>
                  </select>
               </div>
               <div class="form-group">
                  <label for="sold">Sold</label>
                  <input type="text" class="form-control" id="sold" disabled = "true" value="${inventory.sold}">
               </div>
               <div class="form-group">
                  <label for="returned">Returned</label>
                  <input type="text" class="form-control" id="returned" disabled = "true" value="${inventory.returned}">
               </div>
               <div class="form-group">
                  <label for="amount">Amount <span class="asterisk">*</span></label>
                  <input type="text" class="form-control" name="amount" id="amount" path="amount" value="${inventory.amount}">
               </div>
               <div class="form-group">
                  <label for="shipping">Shipping <span class="asterisk">*</span></label>
                  <input type="text" class="form-control" name = "shipping" id="shipping" path="shipping" value="${inventory.shippingRate}">
               </div>
               <button type="submit" id="submitBtn" class="btn btn-info" >Submit</button>
            </c:forEach>
         </form>
      </div>
      <div class="seller-footer">
         <div class="seller-container" style="padding-top: 20px;">Copyright &copy; 2018 Seller Management Cooperation</div>
      </div>
   </body>
</html>