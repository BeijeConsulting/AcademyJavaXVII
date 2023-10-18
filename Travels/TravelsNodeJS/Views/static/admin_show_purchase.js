function showPurchases(){
					
		let htmlContent = '';
		
		let api = "purchases";
		
		let body= "";
		
		let  headers = {
	            'Content-type': 'application/json',
	            'Authorization': `Bearer ` + token};
	
	
		let method = 'GET';
		
		
		fetchContainer(api, method, body, headers)
		.then((response) => response.json())
		.then((purchases) => {
			purchases.forEach((purchase) => {
			htmlContent+=`
				 <div class="card" id="purchases-` + purchase.id + `" onclick="fetchDetails(` + purchase.id + `)">
				 	<p><strong>User:</strong>` + purchase.user.name + ` ` + purchase.user.surname + `</p>
                    <p><strong>Purchase ID:</strong> ` + purchase.id + `</p>
                    <p><strong>N° Tickets:</strong>` + purchase.n_tickets + `</p>
                    <p><strong>Total Amount:</strong> &euro; ` + purchase.amount + `</p>               
                </div>`;
			});	
			
			purchaseData.innerHTML = htmlContent;
			
		});
	}
	

	function fetchDetails(purchase_id){
		
		
		
		let api = "bookings_by_purchase/" + purchase_id;

		let body= "";
		let  headers = {
	            'Content-type': 'application/json',
	            'Authorization': `Bearer ` + token};


		let method = 'GET';

		fetchContainer(api, method, body, headers)
	    .then((response) => response.json())
		.then((bookingsData) => {

			writeOnPopup(bookingsData, purchase_id);
			
		});
		
	}
	
	function writeOnPopup(bookings_data, purchase_id){
		
		let htmlContent = '';
		
		htmlContent += `
		<div class="modal-content">
			<h2> Purchase `+ purchase_id + `<i class="material-icons close" onclick="closePopup()" style="float: right;">close</i></h2>`;
			
			
		bookings_data.forEach((booking) => {
			htmlContent += `
            	<div>
            	<p><strong>ID Booking:</strong> ` + booking.id + `</p>
            	<p><strong>Departure Date:</strong> ` + parseDate(booking.departure) + `</p>
            	<p><strong>Arrival Date:</strong> ` + parseDate(booking.arrival) + `</p>
            	<p><strong>Departure Xport:</strong> ` + booking.departureXport.name + `</p>
            	<p><strong>Arrival Xport:</strong> ` + booking.arrivalXport.name + `</p>
            	<p><strong>N° Tickets:</strong> ` + booking.n_tickets + `</p>
            	<p><strong>Price:</strong> ` + booking.amount + `</p>
            	<hr class="line">                	
            	</div>
            `;
        });
			htmlContent +=`
		    	<div>
		    		<div class="button-center-container">
		    			<button class="primary-button" id="showPassenger" onclick="fetchPassengers(` + purchase_id + `)">Show Passengers</button>
		    			<button class="primary-button hidden" id="hidePassenger" onclick="closePassengers()">Hide Passenger Travel</button>
		    		</div>
		    	</div><br>
		    	
		    		<div class="hidden" id="passengersList">	
		    	
		    		</div>
		    </div>`;

		generatePopup(htmlContent);

	}
	
	function fetchPassengers(purchase_id){
		let passengersDetails = document.getElementById('passengersList');
		let showPassengersButton = document.getElementById('showPassenger');
		let hidePassengersButton = document.getElementById('hidePassenger');
		
		passengersDetails.classList.remove('hidden');
		
		let htmlContent = '';
		
		let api = "passengers_data/" + purchase_id;

		let body= "";
		let  headers = {
	            'Content-type': 'application/json',
	            'Authorization': `Bearer ` + token};


		let method = 'GET';

		fetchContainer(api, method, body, headers)
	    .then((response) => response.json())
		.then((passengersData) => {
			htmlContent += `<h4> Passengers List: </h4>`;
			passengersData.forEach((passenger) => {
				htmlContent += ` 
					<div class="text-center">
						<p> ` + passenger.name + ` ` + passenger.surname + `</p>
					</div>	
				`;
			});
			
			passengersDetails.innerHTML = htmlContent;
			showPassengersButton.classList.add('hidden');
			hidePassengersButton.classList.remove('hidden');
		})
		.catch((error) => {
			console.error('Fetch error:', error);
		});
	}
	
	function parseDate(date_input) {
		
		  let date = new Date(date_input);
		  let finalDate = '';

		  let day = String(date.getDate()).padStart(2, '0');
		  let month = String(date.getMonth() + 1).padStart(2, '0');
		  let year = date.getFullYear();
		  let hours = String(date.getHours()).padStart(2, '0');
		  let minutes = String(date.getMinutes()).padStart(2, '0');
		  let seconds = String(date.getSeconds()).padStart(2, '0');
		  
		  if (seconds !== '00') {
			  finalDate = day + '/' + month + '/' + year + ' ' + hours + ':' + minutes + ':' + seconds;
		  } else {
			  finalDate = day + '/' + month + '/' + year + ' ' + hours + ':' + minutes;
		  }
		  return finalDate;
	}
	
	function closePassengers(){
		let passengersDetails = document.getElementById('passengersList');
		let showPassengersButton = document.getElementById('showPassenger');
		let hidePassengersButton = document.getElementById('hidePassenger');
		
		hidePassengersButton.classList.add('hidden');
		passengersDetails.classList.add('hidden');
		showPassengersButton.classList.remove('hidden');
	}