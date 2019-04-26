<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8">
      <script src="../js/third-party/jquery-2.2.4.min.js"></script>
      <script type="text/javascript" src="../js/hamburger.js"></script>
   </head>
   <div class="seller-header">
      <div class="seller-container">
         <nav class="seller-nav">
            <span class="seller-heading">SELLER MANAGEMENT</span>
            <span class="seller-hamburger" id="openHamburger">&#9776;</span>
            <form id="logoutForm" action="sign_out_action" method="GET">
            <ul class="nav navbar-nav navbar-right">
                  <li><span id="seller-logout"><a href="sign_out_action" id="seller-logout_action" style="list-style: none;color: white;font-size: 20px;">Logout</a></span> </li>
            </ul>
            </form>
         </nav>
      </div>
      <div id="hamburgerClass" class="hamburger">
         <a href="javascript:void(0)" class="closebtn" id="closeHamburger">&times;</a>
         <a href="dashboard">Dashboard</a>
         <a href="inventory">Inventory</a>
         <a href="contact">Contact Us</a>
      </div>
   </div>
</html>