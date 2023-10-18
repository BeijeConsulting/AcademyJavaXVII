
function fetchUser() {
	let htmlContent = '';

	let api = "user/" + userId;


	let body = "";
	let headers = {
		'Content-type': 'application/json',
		'Authorization': `Bearer ` + token
	};


	let method = 'GET';

	fetchContainer(api, method, body, headers)
		.then((response) => response.json())
		.then((user) => {
			currentName = user.name;
			currentSurname = user.surname;
			htmlContent +=
				`<p>Name: ` + currentName + `</p>
				<p>Surname: ` + currentSurname + `</p>
				<p>Email: ` + user.email + `</p>
				<p>Creation date: ` + parseDate(user.creation_date) + `</p>`;

			userInfo.innerHTML = htmlContent;
		});
}

function fetchEditUser() {
	let htmlContent = '';

	let name = document.getElementById("newName").value;
	let surname = document.getElementById("newSurname").value;

	let errorElement = document.getElementById("error-message");
	errorElement.classList.add("hidden");

	let api = "user/" + userId;

	let body = "{\"id\": \"" + userId + "\", \"name\": \"" + name + "\", \"surname\": \"" + surname + "\"}";


	let headers = {
		'Content-type': 'application/json',
	    'Authorization': `Bearer ` + token
	};


	let method = 'PUT';

	fetchContainer(api, method, body, headers)
		.then(response => {
			if (response.ok) {
				return ;
			} else {
				console.error('Edit user failed:', data.message);
				errorElement.textContent = data.message;
				errorElement.classList.remove("hidden");
				throw new Error('Edit user failed');
			}
		}).then(() => {
			console.log("ciao")
			closePopup();
			window.alert("Il tuo profilo è stato aggiornato con successo.");
			//fetchUser();		
			
			
		})
		.catch(error => {
			console.error('Errore nella richiesta:', error);
		});
}

function fetchEditUserPassword() {
	let htmlContent = '';

	let currentPassword = document.getElementById("currentPassword").value;
	let newPassword = document.getElementById("newPassword").value;

	let errorElement = document.getElementById("error-message");
	errorElement.classList.add("hidden");

	let api = "changeUserPassword/" + userId;


	let body = "{\"id\": \"" + userId + "\", \"currentPassword\": \"" + currentPassword + "\", \"newPassword\": \"" + newPassword + "\"}";


	let headers = {
		'Content-type': 'application/json',
		// 'Authorization': `Bearer ` + token
	};


	let method = 'PUT';

	fetchContainer(api, method, body, headers)
		.then(response => {
			if (response.ok) {
				return;
			} else {
				return response.json()
					.then(errorData => {
						console.error('Edit user password failed:', errorData.message);
						errorElement.textContent = errorData.message;
						errorElement.classList.remove("hidden");
						throw new Error('Edit user password failed');
					});
			}
		})
		.then(() => {
			alert("Your password has been updated successfully.");
			closePopup();
		})
		.catch(error => {
			console.error('Fetch error:', error);
		});
}

function fetchPurchases() {
	let htmlContent = '';

	let api = "purchase/" + userId;

	let body = "";
	let headers = {
		'Content-type': 'application/json',
		'Authorization': `Bearer ` + token
	};


	let method = 'GET';

	fetchContainer(api, method, body, headers)
		.then((response) => response.json())
		.then((purchaseList) => {
			console.log(purchaseList);
			htmlContent += `<h3>Purchases: </h3>`;

			//LISTA PURCHASE VUOTA

			purchaseList.forEach((purchase) => {
				htmlContent +=
					`<div class="card">
						<p><strong>ID:</strong> ` + purchase.id + `</p>
						<p><strong>N° Tickets:</strong>` + purchase.nTickets + `</p>
						<p><strong>Total Amount:</strong> &euro; ` + purchase.amount + `</p>
							<div class="button-right-container">
								<button class="primary-button" id="fetchDetails" onclick="fetchDetails(` + purchase.id + `)"> Details </button>
							</div>
					</div>`;
			});

			leftSide.innerHTML = htmlContent;

		});
}

function fetchDetails(purchase_id) {
	rightSide.classList.remove('hidden');

	let htmlContent = '';

	let api = "bookings/" + purchase_id;

	let body = "";
	let headers = {
		'Content-type': 'application/json',
		'Authorization': `Bearer ` + token
	};


	let method = 'GET';

	fetchContainer(api, method, body, headers)
		.then((response) => response.json())
		.then((bookingsData) => {
			htmlContent += `
				<div class="button-right-container">
					<button class ="close-button" title="Close details" onclick="closeDetails()">
	    				<i class="material-icons"> close</i>
	    			</button>
	    		</div>
    			`;
			htmlContent += `<h4> Purchase ` + purchase_id + `</h4>`;
			bookingsData.forEach((booking) => {
				htmlContent += `
                	<div>
                	<p><strong>ID Booking:</strong> ` + booking.id + `</p>
                	<p><strong>Departure Date:</strong> ` + parseDate(booking.departure_date) + `</p>
                	<p><strong>Arrival Date:</strong> ` + parseDate(booking.arrival_date) + `</p>
                	<p><strong>Departure Xport:</strong> ` + booking.departureXport.name + `</p>
                	<p><strong>Arrival Xport:</strong> ` + booking.arrivalXport.name + `</p>
                	<p><strong>N° Tickets:</strong> ` + booking.numTickets + `</p>
                	<p><strong>Price:</strong> ` + booking.amount + `</p>
                	<hr class="line">                	
                	</div>
                `;
			});

			htmlContent += ` 
				<div class="button-center-container">
					<button class="primary-button" id="fetchPassengers" onclick="fetchPassengers(` + purchase_id + `)"> Show Passengers </button>
					<button class="primary-button hidden" id="closePassengersList" onclick="closePassengers()"> Hide Passengers </button>
            	</div>
            	<div id="passengersDetails">		        
		   		</div>
            `;
			bookingsDetails.innerHTML = htmlContent;
			bookingsDetails.classList.remove('hidden');
		})
		.catch((error) => {
			console.error('Fetch error:', error);
		});

}

function fetchPassengers(purchase_id) {
	let passengersDetails = document.getElementById('passengersDetails');
	let showPassengersButton = document.getElementById('fetchPassengers');
	let hidePassengersButton = document.getElementById('closePassengersList');

	passengersDetails.classList.remove('hidden');

	let htmlContent = '';

	let api = "passengers_data/" + purchase_id;

	let body = "";
	let headers = {
		'Content-type': 'application/json',
		'Authorization': `Bearer ` + token
	};


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

function generatePopupEditUser() {
	let htmlContent = '';

	htmlContent += `
			<div class="modal-content">
				<h2>Edit User</h2>
			
				<div id="error-message" class="error-message hidden">
				</div>
			
				<label for="name"><b>Name : </b></label><br>
			 	<input type="text" placeholder="Enter new name"  id="newName" name="newName" value="` + currentName + `" required/><br>
			 	
			 	<label for="surname"><b>Surname : </b></label><br>
			 	<input type="text" placeholder="Enter new surname"  id="newSurname" name="newSurname" value="` + currentSurname + `" required/><br>
				
			 	<div class="modal-buttons">
			 	<p>Would you like to change your password? <a href="#" onclick="generatePopupEditUserPassword()">Change password</a></p>
					<button class="primary-button" id="saveButton" onclick="fetchEditUser()">Save</button>
		        	<button class="secondary-button" id="cancelButton" onclick="closePopup()">Cancel</button>
			    </div>
			</div>
			`;

	generatePopup(htmlContent);
}

function generatePopupEditUserPassword() {
	let htmlContent = '';

	htmlContent += `
			<div class="modal-content">
				<h2>Edit Password</h2>
			
				<div id="error-message" class="error-message hidden">
				</div>
				
				<label for="current-password"><b>Current Password: </b></label><br>
			 	<input type="text" placeholder="Enter current password" id="currentPassword" name="currentPassword" required/><br>
			 	
			 	<label for="new-password"><b>New Password : </b></label><br>
			 	<input type="text" placeholder="Enter new password"  id="newPassword" name="newPassword" required/><br>
				
			 	<div class="modal-buttons">
			 	<p>Would you like to edit your profile? <a href="#" onclick="generatePopupEditUser()">Edit Profile</a></p>
					<button class="primary-button" id="saveButton" onclick="fetchEditUserPassword()">Save</button>
		        	<button class="secondary-button" id="cancelButton" onclick="closePopup()">Cancel</button>
			    </div>
			</div>
			`;

	generatePopup(htmlContent);
}

function generatePopupDisableUser() {
	let htmlContent = '';

	htmlContent += `
			<div class="modal-content">
				<p>Are you sure you want to disable the user?</p>
				<div class="modal-buttons">
			      <button class="primary-button" id="confirmButton" onclick="fetchDisableUser()">Confirm</button>
			      <button class="secondary-button" id="cancelButton" onclick="closePopup()">Cancel</button>
			    </div>
			</div>
			`;

	generatePopup(htmlContent);
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

function fetchDisableUser() {

	let api = "disable_user/" + userId;

	let body = "";
	let headers = {
		'Content-type': 'application/json',
		'Authorization': `Bearer ` + token
	};


	let method = 'PUT';

	fetchContainer(api, method, body, headers)
		.then((response) => response.json())

	alert("User disabled");
	closePopup();
	logout();
}

function closeDetails() {

	rightSide.classList.add('hidden');
	bookingsDetails.classList.add('hidden');
}

function closePassengers() {
	let passengersDetails = document.getElementById('passengersDetails');
	let showPassengersButton = document.getElementById('fetchPassengers');
	let hidePassengersButton = document.getElementById('closePassengersList');

	hidePassengersButton.classList.add('hidden');
	passengersDetails.classList.add('hidden');
	showPassengersButton.classList.remove('hidden');
}
/*
function closePopup() {
	console.log("sto chiudendo");
	let modal = document.getElementById('modal');
	if (modal) {
		modal.remove(); // Rimuovi il popup dalla DOM
		// Rimuovi l'event listener per evitare che venga chiuso più volte
		//se non si rimuove l'event listener rimarrà attivo e continuerà ad ascoltare i clic sulla pagina
		window.removeEventListener('click', closePopup);
	}
	generateMenu();
}*/

