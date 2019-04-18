<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                                    <li class="sales"></li>
                                    <li class="sites"></li>
                                 </ul>
                              </div>
                           </div>
                        </div>
                        <div class="col-md-4 cancelled">
                           <div class="panel panel-default">
                              <div class="panel-heading">Cancelled</div>
                              <div class="panel-body">
                                 <ul>
                                    <li class="sales"></li>
                                    <li class="sites"></li>
                                 </ul>
                              </div>
                           </div>
                        </div>
                        <div class="col-md-4 return">
                           <div class="panel panel-default">
                              <div class="panel-heading">Returns</div>
                              <div class="panel-body">
                                 <ul>
                                    <li class="sales"></li>
                                    <li class="sites"></li>
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
                                    <li class="sales"></li>
                                    <li class="sites"></li>
                                 </ul>
                              </div>
                           </div>
                        </div>
                        <div class="col-md-4 cancelled">
                           <div class="panel panel-default">
                              <div class="panel-heading">Cancelled</div>
                              <div class="panel-body">
                                 <ul>
                                    <li class="sales"></li>
                                    <li class="sites"></li>
                                 </ul>
                              </div>
                           </div>
                        </div>
                        <div class="col-md-4 return">
                           <div class="panel panel-default">
                              <div class="panel-heading">Returns</div>
                              <div class="panel-body">
                                 <ul>
                                    <li class="sales"></li>
                                    <li class="sites"></li>
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
                                    <li class="sales"></li>
                                    <li class="sites"></li>
                                 </ul>
                              </div>
                           </div>
                        </div>
                        <div class="col-md-4 cancelled">
                           <div class="panel panel-default">
                              <div class="panel-heading">Cancelled</div>
                              <div class="panel-body">
                                 <ul>
                                    <li class="sales"></li>
                                    <li class="sites"></li>
                                 </ul>
                              </div>
                           </div>
                        </div>
                        <div class="col-md-4 return">
                           <div class="panel panel-default">
                              <div class="panel-heading">Returns</div>
                              <div class="panel-body">
                                 <ul>
                                    <li class="sales"></li>
                                    <li class="sites"></li>
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
               </tbody>
            </table>
         </div>
         <!-- <div class="col-sm-4 borders seller-height overflow-scroll" id="trendingNewsDiv">
            <h3>Trending News</h3>
            <ul class="list-group" id="trendingNewsList">
            
            </ul>
            </div> -->
         <div class="col-sm-6 border-left seller-height">
            <h2>Profit Calculator</h2>
            <form action="">
               <div class="form-group">
                  <label for="price">Product:</label>
                  <p>
                     <select id="cust_products"></select>
                  </p>
                  <p>
                     <label id="cust_prod_cost" style="margin-left: :20px;">Cost: </label>
                     <input type="text" class="form-control" id="price" name="price" disabled="true"/>
                  </p>
               </div>
               <div class="form-group">
                  <label for="pwd">Shipping Price:</label>
                  <input type="text" class="form-control" id="shipping" placeholder="Enter shipping price" name="shipping">
               </div>
               <div class="form-group">
                  <label for="qty">Quantity:</label>
                  <input type="text" class="form-control" id="stock" placeholder="No. Of items" name="stock">
               </div>
               <button type="button" class="btn btn-default" id="calculate">Calculate</button>
               <strong id="result"></strong>
            </form>
         </div>
         <hr />
      </div>
      <div class="seller-footer">
         <div class="seller-container" style="padding-top: 20px;">Copyright &copy; 2018 Seller Management Cooperation</div>
      </div>
   </body>
</html>