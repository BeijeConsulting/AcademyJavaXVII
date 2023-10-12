/**
 * 
 */

//NO fetchContainer(api, method, body)
//NO fetchContainer(api, method, headers)
//NO fetchContainer(api, method, body, token)
//NO fetchContainer(api, method, token);

//Sì fetchContainer(api, method, "","");
//Sì fetchContainer(api, method, body,"");
//Sì fetchContainer(api, method, "",headers);

/*
api = "companies";
method = "GET";
body = "";
let  headers = {'Content-type': 'application/json',
				'Authorization': `Bearer ` + token};

fetchContainer(api, method, body, headers);

body = JSON.stringify({
	"name" : "Gianni",
	"surname" : "Bianchi"
})

body = JSON.stringify(User);



fetchContainer(...)
.then()
*/

const baseUrl = "http://localhost:3000/";
//const baseUrl = "http://localhost:8080/";
//const baseUrl = "http://beijetravels-env.eba-mxcwmbhe.eu-south-1.elasticbeanstalk.com/";
const apiPrefix = "api/";

function fetchContainer(api, method, body, headers) {

	if (body === "") {
		body = "{}";
	}

	if (headers === "") {
		headers = { 'Content-type': 'application/json; charset=UTF-8' };
	}

	if (method != "GET") {
		return fetch(baseUrl + apiPrefix + api, {
			method: method,
			body: body,
			headers: headers,
		});
	} else {
		return fetch(baseUrl + apiPrefix + api, {
			method: method,
			headers: headers,
		});
	}

}

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
	generateMenu();
}

//FUNZIONE CHE CREA IL POP UP PER IL LOGIN
function generatePopupLogin() {
	let htmlContent = '';
	htmlContent += `
		<div class="modal-content">
			<h2>Sign In</h2>
			
			<div id="error-message" class="error-message hidden">
			</div>
			
			<label for="email"><b>Email:</b></label>
			<br>
			<input type="email" placeholder="Enter email" name="email" id="email" required>
			<br>
				
			<label for="password"><b>Password:</b></label>
			<br>
			<input type="password" placeholder="Enter password" name="password" id="password" required>
			<br>
			
			<div class="modal-buttons">
				<p>Not a member yet? <a href="#" onclick="generatePopupSignUp()">Sign up</a></p>
				<button class="primary-button" id="login" onclick="login()">Login</button>
			</div>
			
		</div>
		`;

	generatePopup(htmlContent);
}


//FUNZIONE CHE CREA IL POP UP PER IL SIGN UP

function generatePopupSignUp() {
	let htmlContent = '';
	htmlContent += `
		<div class="modal-content">
			<h2>Sign Up</h2>
			
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
		 	
		 	<div class="modal-buttons">
		 		<p>Already a member? <a href="#" onclick="generatePopupLogin()">Log in</a></p>
		 		<button class="primary-button" id="signup" onclick="signup()">Sign Up</button>
			</div>		
		</div>
		`
	generatePopup(htmlContent);
}

//FUNZIONE CHE FA IL SIGN UP CON LE ROBE NUOVE

function signup() {
	let name = document.getElementById("name").value;
	let surname = document.getElementById("surname").value;
	let email = document.getElementById("email").value;
	let password = document.getElementById("password").value;
	let confirmPassword = document.getElementById("confirmPassword").value;

	let errorElement = document.getElementById("error-message");
	errorElement.classList.add("hidden");

	let api = "signup";

	let body = JSON.stringify({
		name: name,
		surname: surname,
		email: email,
		password: password,
		confirmPassword: confirmPassword
	});

	let headers = "";

	let method = 'POST';

	fetchContainer(api, method, body, headers)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				return response.json()
					.then(errorData => {
						console.error('SignUp failed:', errorData.message);
						errorElement.textContent = errorData.message;
						errorElement.classList.remove("hidden");
						throw new Error('Signup failed');
					});
			}
		})
		.then((json) => {
			generatePopupLogin();
		})
		.catch(error => {
			console.error('Fetch error:', error);
		});

}


function login() {
	let email = document.getElementById("email").value;
	let password = document.getElementById("password").value;

	let errorElement = document.getElementById("error-message");
	errorElement.classList.add("hidden");

	let api = "signin";

	let body = JSON.stringify({
		email: email,
		password: password
	});
	let headers = "";

	let method = 'POST';

	fetchContainer(api, method, body, headers)
		.then(response => {
			if (response.ok) {
				// Se il login è riuscito (risposta HTTP 200), ottieni i dati JSON dalla risposta
				return response.json();
			} else {
				// Se il login ha fallito, gestisci l'errore
				return response.json()
					.then(errorData => {
						// Ora hai accesso ai dati dell'errore JSON
						console.error('Login failed:', errorData.message);
						errorElement.textContent = errorData.message;
						errorElement.classList.remove("hidden");
						throw new Error('Login failed');
					});
			}
		})
		.then((json) => {
			// Gestisci la risposta di successo qui
			localStorage.setItem("userId", json.id);
			localStorage.setItem("email", json.email);
			localStorage.setItem("token", json.token);
			localStorage.setItem("permission", JSON.stringify(json.permission));
			console.log(json);

			// Chiudi il popup dopo il login
			closePopup();
			//bisogna fare il ricaricamento della pagina? perché non si aggiorna in automatico il menu
		})
		.catch(error => {
			console.error('Fetch error:', error);
		});
}

function logout() {
	localStorage.removeItem("id");
	localStorage.removeItem("email");
	localStorage.removeItem("permission");
	localStorage.removeItem("token");
	location.replace(baseUrl + "home.html");
}

//FUNZIONE CHE CREA IL MENU
function generateMenu() {
	let htmlContent = '';
	let permissionJson = localStorage.getItem("permission");
	htmlContent = '';
	let menu = document.getElementById("menu");
	menu.innerHTML = htmlContent;
	console.log(permissionJson);
	htmlContent += `<a href="./home.html">Home</a>`;
	htmlContent += `<div class="dropdown">
						  <span class="material-symbols-outlined">
						  <a href="#!" onclick="dropdown()" class="dropbtn">menu</a>
						  </span>
						  <div id="myDropdown" class="dropdown-content">`;
	if (permissionJson != undefined) {
		//logged
		let permission = JSON.parse(permissionJson);
		htmlContent += `<a href="./user.html">Profile</a>`;
		if (permission.includes("ADMIN")) {
			htmlContent += `
			<div class="dropdown">
				<a href="#" onclick="dropdownadmin()" class="dropbtn">Management</a>
				<div id="managementDropdown" class="dropdown-content sub-menu">
					<a href="./show_purchases.html">Show purchases</a>
					<a href="./show_travels.html">Show travels</a>
					<a href="./manage_companies.html">Manage companies</a>
					<a href="./manage_cities.html">Manage cities</a>
					<a href="./manage_routes.html">Manage routes</a>
					<a href="./manage_users.html">Manage users</a>
				</div>
            </div>
			`;
		}

		htmlContent += `<a href="#" onclick="logout()">Logout</a>`;

	} else {
		htmlContent += `<a href="#" onclick="generatePopupLogin()">Log in</a>`;
		htmlContent += `<a href="#" onclick="generatePopupSignUp()">Sign up</a>`;
	}
	htmlContent += `</div></div>`;
	menu.innerHTML = htmlContent;
}

function dropdown() {
	document.getElementById("myDropdown").classList.toggle("show");
}

function dropdownadmin() {
	document.getElementById("managementDropdown").classList.toggle("show");
}

function redirectHome() {
	let token = localStorage.getItem("token");
	let userId = localStorage.getItem("userId");
	if (token != undefined) {
		let api = "check_token_validity";
		let method = "GET";
		let body = "";
		let headers = {
			'Content-type': 'application/json',
			'Authorization': `Bearer ` + token
		};

		fetchContainer(api, method, body, headers)
			.then((response) => response.status)
			.then((status) => {

				if (status !== 200) {
					location.replace(baseUrl + "home.html");
				}
			});

	}
	else {
		location.replace(baseUrl + "home.html");
	}
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
	if (!event.target.matches('.dropbtn')) {
		let dropdowns = document.getElementsByClassName("dropdown-content");
		let i;
		for (i = 0; i < dropdowns.length; i++) {
			let openDropdown = dropdowns[i];
			if (openDropdown.classList.contains('show')) {
				openDropdown.classList.remove('show');
			}
		}
	}
}

function generateMenuAdmin() {
	let menuAdmin = document.getElementById("menuAdmin");

	let link1 = document.createElement("a");
	link1.href = "./show_purchases.html";
	link1.textContent = "Show purchase";
	menuAdmin.appendChild(link1);

	let link2 = document.createElement("a");
	link2.href = "./show_travels.html";
	link2.textContent = "Show travels";
	menuAdmin.appendChild(link2);

	let link3 = document.createElement("a");
	link3.href = "./manage_companies.html";
	link3.textContent = "Manage companies";
	menuAdmin.appendChild(link3);

	let link4 = document.createElement("a");
	link4.href = "./manage_cities.html";
	link4.textContent = "Manage cities";
	menuAdmin.appendChild(link4);

	let link5 = document.createElement("a");
	link5.href = "./manage_routes.html";
	link5.textContent = "Manage routes";
	menuAdmin.appendChild(link5);

	let link6 = document.createElement("a");
	link6.href = "./manage_users.html";
	link6.textContent = "Manage users";
	menuAdmin.appendChild(link6);


}

function checkToken() {
	let api = "check_token_validity"
	let method = "GET"
	let body = "";
	let token = localStorage.getItem("token");
	console.log("token", token)
	let headers = {
		'Content-type': 'application/json',
		'Authorization': `Bearer ` + token
	};

	if (token !== null) {
		fetchContainer(api, method, body, headers)
			.then((response) => {
				if (response.ok) {
					return response.json();
				} else {
					logout();
					console.log("sono nell'else della fetch")
					return response.json()
				}
			})

			.catch(error => {

				console.error('Fetch error:', error);
			});
	}
}