function toggleReturnCalendar() {
	let departureDate = document.getElementById("departureDate").value;
	let returnDateInput = document.getElementById("returnDate");
	//returnDateInput.min = departureDate;

	let onewayTrip = document.getElementById("only_depart").checked;

	if (!onewayTrip) {
		if (departureDate == null) {
			returnDateInput.disabled = true;
			returnDateInput.valueAsDate = null;
		} else {
			returnDateInput.disabled = false;
			returnDateInput.min = departureDate;
			returnDateInput.valuesAsDate = new Date(departureDate);
		}
	} else {
		returnDateInput.disabled = true;
		returnDateInput.valueAsDate = null;
	}
}

function search() {
	let departure_city = document.getElementById("departure_city").value;
	let arrival_city = document.getElementById("arrival_city").value;
	let departure_date = document.getElementById("departureDate").value;
	let return_date = document.getElementById("returnDate").value;
	let passengers_number = document.getElementById("passengers_number").value;
	let only_depart;

	if (document.getElementById("only_depart").checked)
		only_depart = "on";
	else {
		only_depart = "null";
	}

	localStorage.setItem("departure_city", departure_city);
	localStorage.setItem("arrival_city", arrival_city);
	localStorage.setItem("departure_date", departure_date);
	localStorage.setItem("return_date", return_date);
	localStorage.setItem("passengers_number", passengers_number);
	localStorage.setItem("only_depart", only_depart);
	if (only_depart === "null" && return_date === "") {
		//errore
		document.getElementById("error").innerHTML = "Please insert return date or check \"only depart\".";
	}
	else {
		// Costruisci l'URL con i parametri
		let url = "./travels.html"
		// Reindirizza l'utente alla nuova pagina con l'URL costruito
		window.location.href = url;
	}
}

function fetchCities() {
	let htmlContent = '';

	let api = "cities";
	let method = "GET";
	let body = "";
	let headers = "";

	fetchContainer(api, method, body, headers)
		.then((response) => response.json())
		.then((citiesList) => {

			citiesList.forEach((city) => {
				htmlContent +=
					`<option id=` + "\"" + city.id + "\"" + `
	                  		 value=` + "\"" + city.name + "\"" + `>
	                 	  ` + city.name + `
	                </option>`
					;
			});

			/*fetch('http://localhost:8080/BeijeTravels/api/cities')*/

			document.getElementById("departure_city").innerHTML = htmlContent;
			document.getElementById("arrival_city").innerHTML = htmlContent;
		});
}