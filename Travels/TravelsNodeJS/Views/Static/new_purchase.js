function payment(){
	let userId = localStorage.getItem("userId");
	let api = "new_purchase/" + userId;
			
	let method="POST";
			
	let newPurchaseDTO = { passengers: passengers,
							bookings: bookings,
							total : total,
							nTickets: nTickets};
	let body = JSON.stringify(newPurchaseDTO);
			
	let token = localStorage.getItem("token");
	let  headers = {'Content-type': 'application/json',
		    		'Authorization': `Bearer ` + token};
			
	fetchContainer(api, method, body, headers)
		.then((response) => response.json())
		.then((json) => {
			console.log(json);
	    	let popupContent = '';
	    	popupContent +=	`<div class="modal-content">
	    					 	<h2>Purchase succeeded</h2>
	    					 	<div id="error-message" class="error-message hidden">
	    					 	</div>
	    					 	<div class="modal-buttons">
	    							<p>Go to your <a href= "./user.html">profile</a> to see all your purchases</p>
	    			 				<a href= "./home.html"><button class="primary-button" id="home">Home</button></a>
	    						 </div>
	    					 </div>`;

	    	generatePopup(popupContent);
	    	document.getElementById("container").innerHTML = "";
	    	
	    	localStorage.removeItem("passengers");
	    	localStorage.removeItem("bookings");
	    	localStorage.removeItem("nTickets");
	    	localStorage.removeItem("departure_city");
	    	localStorage.removeItem("arrival_city");
	    	localStorage.removeItem("totalAmount");
	    	localStorage.removeItem("departure_date");
	    	localStorage.removeItem("return_date");
	    	localStorage.removeItem("only_depart");
	    	localStorage.removeItem("passengers_number");
	    		
	    	for (var i = 0; i < localStorage.length; i++){
	    	   console.log(localStorage.key(i));
	    	}
	    });
}