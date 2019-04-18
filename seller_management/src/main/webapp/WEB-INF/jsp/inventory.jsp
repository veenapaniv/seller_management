<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8">
      <title>Inventory</title>
      <link rel="stylesheet" href="css/dashboard.css" />
      <link rel="stylesheet" href="css/bootstrap-theme.min.css" />
      <link rel="stylesheet" href="css/bootstrap.min.css" />
      <link rel="stylesheet" href="css/inventory.css"/>
   </head>
   <body>
      <jsp:include page="hamburger.jsp" />
      <div style="overflow-x:auto;" class="inventory-container">
         <form action="addProduct">
            <button type="submit" class="btn btn-info" id="addProdBtn"> Wanna add a product?</button>
         </form>
         <table>
            <thead>
               <tr>
                  <th>Product ID</th>
                  <th>Product Name</th>
                  <th>Product Image</th>
                  <th>Stock</th>
                  <th>Channels</th>
                  <th>Sold</th>
                  <th>Returned</th>
                  <th>Amount</th>
                  <th>Shipping</th>
                  <th>Last Updated</th>
                  <th>Action</th>
               </tr>
            </thead>
            <tbody id="tBody">
               <c:forEach var="inventory" items="${inventories}">
                  <tr>
                     <td>
                        <c:out value="${inventory.productId}"/>
                     </td>
                     <td>
                        <c:out value="${inventory.productName}"/>
                     </td>
                     <td><img width=100 height=100 border = 3 src="data:image/jpg;base64,${inventory.image}"/></td>
                     <td>
                        <c:out value="${inventory.quantity}"/>
                     </td>
                     <td>
                        <ul>
                           <c:forEach var="channel" items="${inventory.channels}">
                              <li>${channel}</li>
                           </c:forEach>
                        </ul>
                     </td>
                     <td>
                        <c:out value="${inventory.sold}"/>
                     </td>
                     <td>
                        <c:out value="${inventory.returned}"/>
                     </td>
                     <td>
                        <c:out value="${inventory.amount}"/>
                     </td>
                     <td>
                        <c:out value="${inventory.shippingRate}"/>
                     </td>
                     <td>
                        <c:out value="${inventory.lastUpdated}"/>
                     </td>
                     <td>
                        <c:url var="editUrl" value="/editInventory" />
                        <a href="${editUrl}?id=${inventory.productId}">Edit</a>
                        <c:url var="deleteUrl" value="/deleteInventory" />
                        <a href="${deleteUrl}?id=${inventory.productId}">Delete</a>
                     </td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
      </div>
      <!-- Trigger the modal with a button -->
      <!-- Modal -->
      <div id="myModal" class="modal fade" role="dialog">
         <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
               <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                  <h4 class="modal-title">Delete product?</h4>
               </div>
               <div class="modal-body">
                  <p>Do you really want to delete the product?</p>
               </div>
               <div class="modal-footer">
                  <button type="button" class="btn btn-default" id="deleteProduct"data-dismiss="modal">Yes</button>
                  <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
               </div>
            </div>
         </div>
      </div>
      <div class="seller-footer">
         <div class="seller-container" style="padding-top: 20px;">Copyright &copy; 2018 Seller Management Cooperation</div>
      </div>
   </body>
</html>