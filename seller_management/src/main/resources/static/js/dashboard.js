"use strict"
var newsArray;
var cost;
var stock;
var shipping;
var rowNumber;
$(document).ready(function(){  
	
	$(".calculate").click(function(){
		rowNumber = $(this).attr('id');
		cost = $(".prodAmt.row"+rowNumber).text();
		stock = $(".stock.row"+rowNumber).val();
		shipping = $(".shipping.row"+rowNumber).val();
		console.log("stock is",stock);
		console.log("shipping is",shipping);
		calculateProfit(cost,shipping,stock);
		
	})
   
});//end ready function



function calculateProfit(cost,shipping,quantity){
	var profit;
    //get the values necessary from the user selection
    
    //add validations such that the page shows errors when the fields are left unfilled
    if(shipping && quantity){
        //if shipping price and stock are not numbers throw an error
        if(isNaN(shipping) || isNaN(quantity))
            {
                alert("Only Numericals are allowed");
            }
        //else calculate profit which will be - (costOfProduct * stock) - (shippingPrice * stock)
        else{
            profit = (cost*quantity) - (shipping*quantity);
        $("#result").text("Your approximate profit for the product  for "+stock+" units would be $"+profit.toFixed());
        }// end if/else
        
    }
    else{
        alert("Please enter all the details appropriately");
    }//end if/else
}//end calculateProfit
