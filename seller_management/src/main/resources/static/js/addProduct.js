"use strict"

window.addEventListener("load", function(){
	//reset functionality--anonymous function
	document.getElementById("resetBtn").addEventListener("click", function(){
		location.reload();
	}, false);
},false); //end on load