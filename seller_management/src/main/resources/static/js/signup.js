/**
 * 
 */
$(document).ready(function () {
    $("#signup").click(function(e){ 
	
         $.ajax({
            type: "GET",
			
            url: 'http://localhost:8080/signup',
             contentType: 'application/json; charset=utf-8',
			
                    success: function (jsonData) {
                        window.location = '/signup';
                       
                    },
                    error: function(data) {
                        console.log('Error loading the image');
                    }
           
    }); });
});