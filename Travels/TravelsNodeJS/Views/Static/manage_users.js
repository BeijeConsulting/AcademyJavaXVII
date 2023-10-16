function generatePopup(htmlContent) {
	let modal = document.getElementById("modal");

	// Verifica se il modal esiste già
	if (!modal) {
		modal = document.createElement("div");
		modal.className = "modal";
		modal.id = "modal";

		document.body.appendChild(modal);

		//aggiunge un event listener all'oggetto window (cioè la finestra del browser) per ascoltare gli eventi di clic sulla pagina.
		//gestisce la chiusura del popup quando l'utente fa clic al di fuori del contenuto del popup, 
		//cioè sullo sfondo ovvero quando l'utente fa clic sul modal (l'elemento che copre il background quando il popup è aperto).
		window.addEventListener('click', function(event) {
			if (event.target === modal) {
				closePopup();
			}
		});
	}

	// Aggiorna il contenuto del modal con quello passato come argomento
	modal.innerHTML = htmlContent;
}

function closePopup() {
	let modal = document.getElementById('modal');
	if (modal) {
		modal.remove(); // Rimuovi il popup dalla DOM
		// Rimuovi l'event listener per evitare che venga chiuso più volte
		//se non si rimuove l'event listener rimarrà attivo e continuerà ad ascoltare i clic sulla pagina
		window.removeEventListener('click', closePopup);
	}
	//generateMenu();
}



function generatePopupInsertUser() {
	let htmlContent = '';
	htmlContent += `
		<div class="modal-content">
			<h2>Insert User</h2>
			
			<div id="error-message" class="error-message hidden">
			</div>
			
			<label for="name"><b>Name : </b></label><br>
		 	<input type="text" placeholder="Name"  id="name" name="name" required/><br>
		 	
		 	<label for="surname"><b>Surname : </b></label><br>
		 	<input type="text" placeholder="Surname"  id="surname" name="surname" required/><br>
		 	
		 	<label for="email"><b>Email : </b></label><br>
		 	<input type="text" placeholder="Enter email"  id="email" name="email" required/><br>
		 	
		 	<label for="password"><b>Password : </b></label><br>
		 	<input type="text" id="password" name="password" required/><br>
		 	
		 	<label for="confirmPassword"><b>ConfirmPassword : </b></label><br>
		 	<input type="text" id="confirmPassword" name="confirmPassword" required/><br>
		 	<br>
		 	
		 	<select id="type" name="type" required>
				 	 <option value="2">Admin</option>
				 	 <option value="1">Customer</option>
			</select><br>
		 	
		 	<div class="modal-buttons">
		 		<button class="primary-button" id="insertUser" onclick="insertUser()">Add</button>
			</div>		
		</div>
		`
	generatePopup(htmlContent);
}


function insertUser() {
	let name = document.getElementById("name").value;
	let surname = document.getElementById("surname").value;
	let email = document.getElementById("email").value;
	let password = document.getElementById("password").value;
	let confirmPassword = document.getElementById("confirmPassword").value;
	let type = document.getElementById("type").value;

	let errorElement = document.getElementById("error-message");
	errorElement.classList.add("hidden");

	let api = "insert_user/" + type;

	let body = JSON.stringify({
		name: name,
		surname: surname,
		email: email,
		password: password,
		confirmPassword: confirmPassword
	});

	let headers = {
		'Content-type': 'application/json',
		'Authorization': `Bearer ` + token
	};

	let method = 'POST';

	fetchContainer(api, method, body, headers)
		.then(response => {
			if (response.ok) {
				return ;
			} else {
				return response.json()
					.then(errorData => {
						console.error('insert user failed:', errorData.message);
						errorElement.textContent = errorData.message;
						errorElement.classList.remove("hidden");
						throw new Error('insert user failed');
					});
			}
		})
		//.then((json) => {
			//generatePopupInsertUser();
		//})
		.then(() => {
			// Chiudo il popup una volta che l'utente è stato inserito con successo
			closePopup();
			window.alert('Utente inserito con successo');	
			if(type === "Admin") {
				let adminTableContainer = document.getElementById('adminTableContainer');
				adminTableContainer.innerHTML = '';
				getAllAdmin();
			} else {
				let customerTableContainer = document.getElementById('customerTableContainer');
				customerTableContainer.innerHTML = '';
				getAllCustomers();
			}
		})
		.catch(error => {
			console.error('Fetch error:', error);
		});

}

function parseDate(date_input) {

	let date = new Date(date_input);
	let finalDate = '';

	let day = String(date.getDate()).padStart(2, '0');
	let month = String(date.getMonth() + 1).padStart(2, '0');
	let year = date.getFullYear();


	finalDate = day + '/' + month + '/' + year;

	return finalDate;
}

function getAllAdmin() {
	let api = "admins_user";

	let body = "";

	let headers = {
		'Content-type': 'application/json',
		'Authorization': `Bearer ` + token
	};

	let method = 'GET';

	fetchContainer(api, method, body, headers)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				return response.json()
					.then(errorData => {
						console.error('get all admin failed:', errorData.message);
					});
			}
		})
		.then(data => {
			let tableContainer = document.createElement('div');
			tableContainer.id = 'adminTable';

			let tableHtml = '<table>' +
				'<tr>' +
				'<th>Id</th>' +
				'<th>Name</th>' +
				'<th>Surname</th>' +
				'<th>Email</th>' +
				'<th>Creation date</th>' +
				'</tr>';

			data.forEach(admin => {
				tableHtml += '<tr>';
				tableHtml += '<td>' + admin.id + '</td>';
				tableHtml += '<td>' + admin.name + '</td>';
				tableHtml += '<td>' + admin.surname + '</td>';
				tableHtml += '<td>' + admin.email + '</td>';
				tableHtml += '<td>' + parseDate(admin.creation_date) + '</td>';
				tableHtml += '</tr>';
		
				
			
			});
			tableHtml += '</td></tr>';
			
		

			tableContainer.innerHTML = tableHtml;


			let adminTableContainer = document.getElementById('adminTableContainer');


			adminTableContainer.appendChild(tableContainer);
		})
		.catch(error => {
			console.error('Errore nella richiesta fetch:', error);
		});
}




function getAllCustomers() {


	let api = "customers_user";

	let body = "";

	let headers = {
		'Content-type': 'application/json',
		'Authorization': `Bearer ` + token
	};

	let method = 'GET';

	fetchContainer(api, method, body, headers)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				return response.json()
					.then(errorData => {
						console.error('get all users failed:', errorData.message);
					});
			}
		})
		.then(data => {
			console.log("oooooooooooooo", data)

			let tableContainer = document.createElement('div');
			tableContainer.id = 'adminTable';

			let tableHtml = '<table>' +
				'<tr>' +
				'<th>Id</th>' +
				'<th>Name</th>' +
				'<th>Surname</th>' +
				'<th>Email</th>' +
				'<th>Creation date</th>' +
				'<th>Action</th>' +
				'</tr>';

			data.forEach(customer => {
				tableHtml += '<td>' + customer.id + '</td>';
				tableHtml += '<td>' + customer.name + '</td>';
				tableHtml += '<td>' + customer.surname + '</td>';
				tableHtml += '<td>' + customer.email + '</td>';
				tableHtml += '<td>' + parseDate(customer.creation_date) + '</td>';
				tableHtml += '<td>';
				
				if (!customer.disabled_date) {
					tableHtml += '<form action="./disable_customer" method="get">';
					tableHtml += '<input type="hidden" name="customerId" value="' + customer.id + '">';
					tableHtml += '<button type="submit" onclick="disableUser(' + customer.id + ')" class= "secondary-button">Disable</button>';
					tableHtml += '</form>';
				}
				
				tableHtml += '</td></tr>';
				
			});


			tableContainer.innerHTML = tableHtml;


			let customerTableContainer = document.getElementById('customerTableContainer');


			customerTableContainer.appendChild(tableContainer);

		})
		.catch(error => {
			console.error('Errore nella richiesta fetch:', error);
		});
}

function disableUser(id) {
	let api = `/disable_user/${id}`;
	let method = 'PUT';
	let body = "";
	let headers = {
		'Content-type': 'application/json',
		'Authorization': `Bearer ` + token
	};


	fetchContainer(api, method, body, headers)
		.then(response => response.json())

		.then(updatedUser => {
			console.log("updatedUser", updatedUser)


		})
		.catch(error => {
			console.error('Fetch error:', error);

		});


}


function disableUserByEmail() {

	let email = document.getElementById("customerEmail");

	console.log("Email inserita: " + email.value);
	
	let api = "disable_user_by_email";
	let method = 'PUT';
	let body = "{\"email\": \""+email.value+"\"}";
	let headers = {
		'Content-type': 'application/json',
		'Authorization': `Bearer ` + token
	};


	fetchContainer(api, method, body, headers)
		.then(response => response.json())

		.then(disabledUser => {
			console.log("updatedUser", disabledUser)
			let adminTableContainer = document.getElementById('adminTableContainer');
			adminTableContainer.innerHTML = '';
			let customerTableContainer = document.getElementById('customerTableContainer');
			customerTableContainer.innerHTML = '';
			getAllAdmin();
			getAllCustomers();
			
		})
		.catch(error => {
			console.error('Fetch error:', error);

		});



}





