<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="false"%>


<html>
   <head>
      <title>Seller Management</title>
      <link rel="stylesheet" href="css/dashboard.css" />
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
      <script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
       <script type="text/javascript" src="js/dashboard.js"></script> 
   </head>
   <body>
        
      <div id="loggedInUser" style="display:none">${userID}</div>
        
      <jsp:include page="hamburger.jsp" />
      <div class="row">
         <div class="col col-md-12 seller-statistics">
            <div class="container">
               <ul class="seller_statistics_nav nav nav-tabs">
                  <li class="active">
                     <a data-toggle="tab" href="#today">Today</a>
                  </li>
                  <li>
                     <a data-toggle="tab" href="#week">Last Week</a>
                  </li>
                  <li>
                     <a data-toggle="tab" href="#month">Last Month</a>
                  </li>
               </ul>
               <div class="tab-content">
                  <div id="today" class="tab-pane fade in active">
                     <h3>Today</h3>
                     <div class="row">
                        <div class="col-md-4 confirmed">
                           <div class="panel panel-default">
                              <div class="panel-heading"> Confirmed</div>
                              <div class="panel-body">
                                 <ul>
                                    <li class="sales">Sales: <c:out value="${todaysConfirmed}"/></li>
                                    <li class="sites">Channels: <c:out value="${todaysConfirmedChannels}"/></li>
                                 </ul>
                              </div>
                           </div>
                        </div>
                        <div class="col-md-4 cancelled">
                           <div class="panel panel-default">
                              <div class="panel-heading">Cancelled</div>
                              <div class="panel-body">
                                 <ul>
                                     <li class="sales">Sales: <c:out value="${todaysCancelled}"/></li>
                                    <li class="sites">Channels: <c:out value="${todaysCancelledChannels}"/></li>
                                 </ul>
                              </div>
                           </div>
                        </div>
                        <div class="col-md-4 return">
                           <div class="panel panel-default">
                              <div class="panel-heading">Returns</div>
                              <div class="panel-body">
                                 <ul>
                                     <li class="sales">Sales: <c:out value="${todaysReturned}"/></li>
                                    <li class="sites">Channels: <c:out value="${todaysReturnedChannels}"/></li>
                                 </ul>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div id="week" class="tab-pane fade">
                     <h3>Last Week</h3>
                     <div class="row">
                        <div class="col-md-4 confirmed">
                           <div class="panel panel-default">
                              <div class="panel-heading"> Confirmed</div>
                              <div class="panel-body">
                                 <ul>
                                     <li class="sales">Sales: <c:out value="${weekConfirmed}"/></li>
                                    <li class="sites">Channels: <c:out value="${weekConfirmedChannels}"/></li>
                                 </ul>
                              </div>
                           </div>
                        </div>
                        <div class="col-md-4 cancelled">
                           <div class="panel panel-default">
                              <div class="panel-heading">Cancelled</div>
                              <div class="panel-body">
                                 <ul>
                                    <li class="sales">Sales: <c:out value="${weekCancelled}"/></li>
                                    <li class="sites">Channels: <c:out value="${weekCancelledChannels}"/></li>
                                 </ul>
                              </div>
                           </div>
                        </div>
                        <div class="col-md-4 return">
                           <div class="panel panel-default">
                              <div class="panel-heading">Returns</div>
                              <div class="panel-body">
                                 <ul>
                                   <li class="sales">Sales: <c:out value="${weekReturned}"/></li>
                                    <li class="sites">Channels: <c:out value="${weekReturnedChannels}"/></li>
                                 </ul>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div id="month" class="tab-pane fade">
                     <h3>Last Month</h3>
                     <div class="row">
                        <div class="col-md-4 confirmed">
                           <div class="panel panel-default">
                              <div class="panel-heading"> Confirmed</div>
                              <div class="panel-body">
                                 <ul>
                                     <li class="sales">Sales: <c:out value="${monthsConfirmed}"/></li>
                                    <li class="sites">Channels: <c:out value="${monthConfirmedChannels}"/></li>
                                 </ul>
                              </div>
                           </div>
                        </div>
                        <div class="col-md-4 cancelled">
                           <div class="panel panel-default">
                              <div class="panel-heading">Cancelled</div>
                              <div class="panel-body">
                                 <ul>
                                    <li class="sales">Sales: <c:out value="${monthsCancelled}"/></li>
                                    <li class="sites">Channels: <c:out value="${monthCancelledChannels}"/></li>
                                 </ul>
                              </div>
                           </div>
                        </div>
                        <div class="col-md-4 return">
                           <div class="panel panel-default">
                              <div class="panel-heading">Returns</div>
                              <div class="panel-body">
                                 <ul>
                                    <li class="sales">Sales: <c:out value="${monthsReturned}"/></li>
                                    <li class="sites">Channels: <c:out value="${monthReturnedChannels}"/></li>
                                 </ul>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            <hr/>
         </div>
      </div>
      <div class="col col-md-12 seller-stock">
         <div class="col-sm-6 border-right seller-height container-fluid">
            <h3>Trending Products this Month</h3>
            <table class="table table-striped" id="trending_products">
               <thead>
                  <tr>
                     <th scope="col">#</th>
                     <th scope="col">Product Name</th>
                     <th scope="col">Sales</th>
                  </tr>
               </thead>
               <tbody id="top_products">
               		<%-- <c:out value="${jsonData}"/> --%>
               		<%-- <c:forEach var="jsonProduct" items="${jsonData.get(\"productSales\").get(\"product\")}">
               		<c:out value="name"/>
               		</c:forEach> --%>
               		 <c:set var="count" value="1" scope="page" />
               		<c:forEach var="entry" items="${items}">
               			
	               		<tr>
	               		<td><c:out value="${count}"/></td>
	               		<td><c:out value="${entry.key}"/></td> 
	               		<td><c:out value="${entry.value}"/> </td>
	               		</tr> 
	               		<c:set var="count" value="${count + 1}" scope="page"/>
               		</c:forEach>
               </tbody>
            </table>
         </div>
         <!-- <div class="col-sm-4 borders seller-height overflow-scroll" id="trendingNewsDiv">
            <h3>Trending News</h3>
            <ul class="list-group" id="trendingNewsList">
            
            </ul>
            </div> -->
      <!--    <form:form method="POST" action="/spring-mvc-java/addEmployee"
	  modelAttribute="employee">
	    <form:label path="name">Name</form:label>
	    <form:input path="name" />
	     
	    <form:label path="id">Id</form:label>
	    <form:input path="id" />
	     
	    <input type="submit" value="Submit" />
	</form:form>    -->
         
            
         <div class="col-sm-6 border-left seller-height">
            <h2>Profit Calculator</h2>
            	 <table class="table table-striped" id="trending_products">
               <thead>
                  <tr>
                     <th scope="col">Product Name</th>
                     <th scope="col">Cost</th>
                     <th scope="col">Shipping price</th>
                     <th scope="col">Quantity</th>
                     <th scope="col">Calculate</th>
                  </tr>
               </thead>
               <tbody id="top_products">
       				 <c:set var="counter" value="1" scope="page" />
               		<c:forEach var="products" items="${products}">
	               		<tr>
	               			<form action="">
	               			<td class="productName"><c:out value="${products.productName}"/></td>
	               			<td class="prodAmt row${counter}"><c:out value="${products.amount}"/></td>
	               			<td><input type="text" class="form-control row${counter} shipping" placeholder="shipping" name="shipping"></td>
	               			<td><input type="text" class="form-control row${counter} stock" placeholder="No. Of items" name="stock"></td>
	               			<td><button type="button" class="btn btn-default row${counter} calculate" id="${counter}">Calculate</button></td>
	               			</form>
	               		</tr>
	               		<c:set var="counter" value="${counter + 1}" scope="page"/>
               		</c:forEach>
               		<p id="result"></p>
               </tbody>
            </table>
           
         </div>
         <hr />
      </div>
      <div class="seller-footer">
         <div class="seller-container" style="padding-top: 20px;">Copyright &copy; 2018 Seller Management Cooperation</div>
      </div>
   </body>
</html>