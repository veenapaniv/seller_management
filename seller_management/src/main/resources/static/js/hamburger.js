"use strict"
$(document).ready(function(){  
	$(window).on("load", function(){
			$("#openHamburger").on("click",openHamburger);
			$("#closeHamburger").on("click",closeHamburger);
		});
	function openHamburger() {
		$("#hamburgerClass").css("width", "250px");
	}//end function openHamburger
	
	function closeHamburger() {
		$("#hamburgerClass").css("width", "0px");
	}//end function closeHamburger
});