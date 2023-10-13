function showTravels(){
		
		let htmlContent = '';
		
		let api = "travels/";
		

		let body= "";
		let  headers = {
	            'Content-type': 'application/json',
	            'Authorization': `Bearer ` + token};


		let method = 'GET';

		fetchContainer(api, method, body, headers)
		.then((response) => response.json())
		.then((tralvels) => {
			htmlContent += `<h3>Travels: </h3>`;
			
			tralvels.forEach((travel) => {
			htmlContent += `
				<div class="card">   
				 	<h2><b>Travel ID:</b>` + travel.id + `<br></h2>
				 	<b>Departure Xport:</b>` + travel.route.departureXport.name + `<br>
				 	<b>Departure Time:</b>` + travel.schedule.departure_time + `<br>
				 	<b>Departure Date:</b>` + travel.departure_date + `<br>
				 	<b>Arrival Xport:</b>` + travel.route.arrivalXport.name + `<br>
				 	<b>Arrival Time:</b>` + travel.schedule.arrival_time + `<br>
				 	<b>Arrival Date:</b>` + travel.arrival_date + `<br>
				 	<b>Empty Seats:</b>` + travel.empty_seats + `/` + travel.schedule.seats + `<br>
				 	<br>
					 <div class="button-center-container">
					 	<button class="primary-button" onclick="travelsBooking(` + travel.id + `)">Show Related Bookings</button>
		    		</div>
			 	</div>`;
			});
			
			travels.innerHTML = htmlContent;
			
		});
		
	}	

	function travelsBooking(travel_id){
		
		console.log(travel_id);
		
		details.classList.remove('hidden');
		
		let htmlContent = '';
		
		let api = "bookings_by_travel/" + travel_id;

		let body= "";
		
		let  headers = {
	            'Content-type': 'application/json',
	            'Authorization': `Bearer ` + token};


		let method = 'GET';

		fetchContainer(api, method, body, headers)
	    .then((response) => response.json())
	    .then((bookings) => {	    	
	    	htmlContent += `
	    	    <div style="text-align: center;">
	    	        <h3>Travel #` + travel_id + ` Details: <i class="material-icons close" onclick="closeDetails()" style="float: right;">close</i></h3>
	    	        
	    	    </div>`;

	    	
	    	
	    	bookings.forEach((booking) => {
	    		htmlContent += `
	    			<div class="card">
		    			<h3>Booking N°: ` + booking.id + `</h3>
		    			<b>User Id: </b>` + booking.purchase_user.id +`<br>
		    			<b>Full Name: </b>` + booking.purchase_user.name + ` ` + booking.purchase_user.surname + `<br>
		    			<b>Number of Tickets: </b>` + booking.numTickets + `<br>
		    			<b>Amount Paid: </b>` + booking.amount + ` &euro; <br>
	    			</div>`;
	    	});
	    	
	    	htmlContent +=`
	    	<div>
	    		<div class="button-center-container">
	    			<button class="primary-button" id="showPassenger" onclick="travelsPassengers(` + travel_id + `)">Show Passenger Travel</button>
	    			<button class="primary-button hidden" id="hidePassenger" onclick="closePassengers()">Hide Passenger Travel</button>
	    		</div>
	    	</div><br>`;
	    	
	    	htmlContent += `
	    			<div class="card hidden" id="passengersList">	
	    	
	    			</div>`;
	    	
	    	details.innerHTML = htmlContent;

	    });
	}
	
	
	
	function travelsPassengers(travel_id){
		
		let passengers = document.getElementById("passengersList");
		let showButton = document.getElementById("showPassenger");
		let hideButton = document.getElementById("hidePassenger");
		
		let htmlContent = '';
		
		let api = "passengers/travel/" + travel_id;

		let body= "";
		
		let  headers = {
	            'Content-type': 'application/json',
	            'Authorization': `Bearer ` + token};


		let method = 'GET';

		fetchContainer(api, method, body, headers)
	    .then((response) => response.json())
	    .then((passengersList) => {	    	
	    	htmlContent += `<h3> Travels Passengers </h3>`;
	    	
	    	passengersList.forEach((passenger) => {
	    		htmlContent += `    			
		    			<b>Full Name: </b>` + passenger.name + ` ` + passenger.surname + `<br>`;    			
	    	});
	    	
	    		    	
	    	passengers.innerHTML = htmlContent;
	    	passengers.classList.remove('hidden');
	    	showButton.classList.add('hidden');
	    	hideButton.classList.remove('hidden');

	    });
	}
	
	function closeDetails(){
		
		details.innerHTML = ``;
		details.classList.add("hidden");
		
	}
	
	function closePassengers(){
		
		let passengers = document.getElementById("passengersList");
		let showButton = document.getElementById("showPassenger");
		let hideButton = document.getElementById("hidePassenger");
		
		passengers.classList.add('hidden');
		hideButton.classList.add('hidden');
		showButton.classList.remove('hidden');
    	
	}