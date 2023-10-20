function travelsList(array, divCont) {
	console.log("ARRAY", array)
	//console.log("arrayLength " + array.length );
       if(array.length === 0) {
        let div = document.createElement("div");
        let p = document.createElement("p");
        p.innerHTML = "no flights exist for the dates entered.";
        p.style.fontSize = "20px";
        p.style.color="red";
        div.appendChild(p);
        div.style.textAlign = "center";
        divCont.appendChild(div);
    } else{
		for (let i = 0; i < array.length; i++) {
			// Ottieni l'oggetto schedule corrente
			let schedule = array[i];
			let div = document.createElement("div");
			div.classList.add("schedule");
			div.setAttribute("data-id", schedule.id);
			div.classList.add("not-selected");
			console.log("scheduleId:", schedule.id);
			div.addEventListener("click", function() {
				// Controlla se il div è selezionato
				if (div.classList.contains("not-selected")) {
					checkOnlyDepart(schedule.id);
					// Rimuovi l'evento click dopo la selezione
					div.classList.remove("not-selected");
					div.removeEventListener("click", this);
				}
			});
			// Crea l'HTML interno con le proprietÃ  dell'oggetto schedule
			div.innerHTML = `
				<h2 class="title"></h2>
				<hr>
				<p class="paragraph"><strong>Departure city xport: </strong></p>
				<p class="paragraph"><strong>Arrival city xport: </strong></p>
				<p class="paragraph"><strong>Departure time: </strong></p>
				<p class="paragraph"><strong>Arrival time: </strong></p>
				<p class="paragraph"><strong>Duration: </strong></p>
				<p class="paragraph"><strong>Price: </strong></p>
				<p class="id" style="visibility:hidden"></p>`

			// Aggiungi il div al tuo elemento di destinazione
			divCont.appendChild(div);
// Calcola la differenza tra le due date in millisecondi
			let durataInSecondi =schedule.duration;
			 //Calcola la durata in ore
			let durataInOre = durataInSecondi / 3600; // 1 ora = 3600000 millisecondi
			
			let durataInMinuti = durataInOre * 60;
			durataInOre = Math.trunc(durataInOre);
			durataInMinuti =Math.trunc( durataInMinuti % 60);
				
		div.querySelector(".title").innerHTML += schedule.name;
		div.querySelector(".paragraph:nth-child(3)").innerHTML += schedule.departure_name;
		div.querySelector(".paragraph:nth-child(4)").innerHTML += schedule.arrival_name;
		div.querySelector(".paragraph:nth-child(5)").innerHTML += schedule.departure_time;
		div.querySelector(".paragraph:nth-child(6)").innerHTML += schedule.arrival_time;
		div.querySelector(".paragraph:nth-child(7)").innerHTML += durataInOre + "  H " + durataInMinuti + " m";
		div.querySelector(".paragraph:nth-child(8)").innerHTML += schedule.price + " &euro;";
		div.querySelector(".id").innerHTML += schedule.id;
		//div.innerHTML += '<button name="select" id="myButton" type="submit" class="button" value="" onclick="checkOnlyDepart(' + schedule.id + ')">Select</button>';
	}
	}
}

function checkOnlyDepart(scheduleId) {
	let cont = document.getElementById("departureSchedules");
	let url = "./passengers_data.html" 
        
	if (only_depart !== "null") {
		createBookings(scheduleId, passengers_number, departure_date)
			.then(() => {
				//location.replace("./BeijeTravels/passengers_data");
				// Reindirizza l'utente alla nuova pagina con l'URL costruito
       			 window.location.href = url;
			});
	}
	else if (isArrival === true){
		createBookings(scheduleId, passengers_number, return_date)
			.then(() => {
				//location.replace("./BeijeTravels/passengers_data");
				// Reindirizza l'utente alla nuova pagina con l'URL costruito
       			 window.location.href = url;
			});

	}
	else {
		createBookings(scheduleId, passengers_number, departure_date)
		let departureSchedulesDiv = document.getElementById("departureSchedules");
		for (child of departureSchedulesDiv.children) {
			for (child2 of child.children) {
				if (child2.classList.contains("id") && child2.textContent !== scheduleId.toString()) {
					child.style.display = "none";

				} else {
					if (child2.tagName === "BUTTON") {
						console.log("sono nell'if")
						child2.style.display = "none";
					}
				}
			}
		}
		let schedContainer = document.getElementById("schedContainer");
		let container = document.createElement("div");
		schedContainer.append(container);

		let h1 = document.createElement("h1");
		let hr2 = document.createElement("hr");
		container.appendChild(hr2);
		h1.textContent = "Arrival";
		container.appendChild(h1);
		let pArrivalDate = document.createElement("p");
		pArrivalDate.innerHTML = return_date;
		container.appendChild(pArrivalDate);
		let hr = document.createElement("hr");
		container.appendChild(hr);
		let arrivalSchedulesDiv = document.createElement("div");
		arrivalSchedulesDiv.id = "arrivalSchedules";
		arrivalSchedulesDiv.style.display = "flex"
		arrivalSchedulesDiv.style.gap = "20px"
		schedContainer.append(arrivalSchedulesDiv);
		isArrival = true;
		travelsList(arr.arrival_schedule, arrivalSchedulesDiv);

	}

}

function loaderElement() {
	let loader = document.getElementById("loader");
	if (arr === undefined) {
		loader.style.display = "block";
		contSchedules.style.display = "none";
	} else {
		loader.style.display = "none";
		contSchedules.style.display = "flex";
		contSchedules.style.gap = "20px;";

	};
}

function companiesId(array) {
	let newArray = "";

	for (let i = 0; i < array.length; i++) {
		if (i === array.length - 1)
			newArray += array[i];
		else {
			newArray += array[i];
			newArray += "_";
		}
	}

	return newArray;
}

function sortByPriceAsc(f1, f2){
	if (f1.price > f2.price) {
		return 1;
	  }
	  if (f1.price < f2.price) {
		return -1;
	  }
	  return 0;
}

function sortByDurationAsc(f1, f2){
	if (f1.duration > f2.duration) {
		return 1;
	  }
	  if (f1.duration < f2.duration) {
		return -1;
	  }
	  return 0;
}

function sort(select, arrayGenerics) {
	switch(select){
		case "cheapest": arrayGenerics.sort((f1, f2) => sortByPriceAsc(f1, f2)); break;
		case "expensive": arrayGenerics.sort((f1, f2) => sortByPriceAsc(f2, f1)); break;
		case "fastest": arrayGenerics.sort((f1, f2) => sortByDurationAsc(f1, f2)); break;
		case "lowest": arrayGenerics.sort((f1, f2) => sortByDurationAsc(f2, f1)); break;	
	}
	return arrayGenerics;
}

function showArrayFilteredAndSorted(){
	let divCont;
	let temp = [];
	temp = JSON.parse(localStorage.getItem("original_schedules"));

	let arrayGenerics = [];
	if (isArrival) {
		arrayGenerics = temp.arrival_schedule;
		divCont = document.getElementById("arrivalSchedules");
		//listIdSchedule = schedulesId(arr.arrivalSchedules);
	} else {
		arrayGenerics = temp.departure_schedule;		
		divCont = document.getElementById("departureSchedules");
		//listIdSchedule = schedulesId(arr.departureSchedules);
	}

	var slider = document.getElementById("slider");
	if (slider != null && slider!= undefined && slider.value != 0){
		valueDisplay = document.getElementById("sliderValue");
		valueDisplay.innerHTML = slider.value + " Hours";
		arrayGenerics = updateSliderValue(arrayGenerics);
	} else{
		valueDisplay = document.getElementById("sliderValue");
		valueDisplay.innerHTML = slider.value + " Hours";
	}

	let checkboxes = document.getElementsByClassName("checkbox");
	if (checkboxes.length != 0){
		arrayGenerics = checkCompany(checkboxes, arrayGenerics);
	}

	let selected = document.getElementById("select").value;
	if (selected != "empty"){
		arrayGenerics = sort(selected, arrayGenerics);
	}

	localStorage.setItem("schedules_to_show", arrayGenerics);
	divCont.innerHTML = "";
	travelsList(arrayGenerics, divCont);
	console.log("array to show : ", arrayGenerics);
}

function updateSliderValue(arrayGenerics) {
	let arrayToShow = arrayGenerics.filter((f) => {return f.duration <= slider.value * 3600;});
	return arrayToShow;

}

function checkCompany(checkboxes, arrayGenerics) {

	companiesIds = [];

	let listIdSchedule = [];
	//if(arrFiltered.length === 0){
	//let dep = JSON.stringify(arr.departureSchedules);
	if (isArrival) {
		listIdSchedule = schedulesId(arr.arrivalSchedules);
	} else {
		listIdSchedule = schedulesId(arr.departureSchedules);
	}
	//} 

	let arrayToShow = [];
	let filteredFlights = [];
	

	for (let i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i].checked) {
			filteredFlights = arrayGenerics.filter((f) => {return f.company_id === checkboxes[i].value;});
			arrayToShow.concat(filteredFlights);
		}
	}

	let listIdCompany = companiesId(companiesIds);
	console.log("id comanies", listIdCompany)
       if(listIdCompany.length == 0){
		   	if (isArrival) {
		listIdSchedule = schedulesId(arr.arrivalSchedules);
	} else {
		listIdSchedule = schedulesId(arr.departureSchedules);
	}
	   }

}

function removeFilters() {
	let arrayGenerics = [];
	if (isArrival) {
		arrayGenerics = arr.arrivalSchedules;
	} else {
		arrayGenerics = arr.departureSchedules;
	}
	let checkboxes = document.getElementsByClassName("checkbox");
	for (let i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i].checked) {
			checkboxes[i].checked = false;
		}
	}
	let slider = document.getElementById("slider");
	if(valueDisplay!==undefined){
		slider.value = 0;
		valueDisplay.innerHTML = slider.value + " Hours";
	}	
	document.getElementById("select").selectedIndex = 0;
	let schedulesDiv = isArrival ? document.getElementById("arrivalSchedules") : document.getElementById("departureSchedules");
	schedulesDiv.innerHTML = "";
	travelsList(arrayGenerics, schedulesDiv);
	event.preventDefault();

}

function createBookings(scheduleId, passengers_number, departure_date) {
	console.log("scheduleId " , scheduleId)
	let api = "bookings";
	let method = "POST";
	let headers = "";
	console.log("Create booking");
	let body = JSON.stringify({
		scheduleId: scheduleId,
		passengers_number: passengers_number,
		departure_date: departure_date
	});

	return fetchContainer(api, method, body, headers)
		.then((response) => response.json())
		.then((data) => {
			let varia = data;
			console.log("data " + varia);

			let listIsPresent = localStorage.getItem("bookings");
			console.log("bookingsStorage " + listIsPresent);
			if (listIsPresent === null) {
				listIsPresent = [];
			} else {
				listIsPresent = JSON.parse(listIsPresent);
			}
			listIsPresent.push(data);
			localStorage.setItem("bookings", JSON.stringify(listIsPresent));			

		})

		.catch(error => {

			console.error('Fetch error:', error);
		});
}

function getAllCompanies() {
	let api = "filtered_companies/enableAll";
	let method = "GET";
	let headers = "";
	let body = "";

	fetchContainer(api, method, body, headers)
		.then((response) => {
			if (response.ok) {
				return response.json();
			} else {

				return response.json()
					.then(errorData => {

						console.error('get all companies failed:', errorData.message);
						errorElement.textContent = errorData.message;
						errorElement.classList.remove("hidden");
					});
			}
		})
		.then((data) => {
			console.log(data);
			let containerCheckbox = document.getElementById("containerCheckbox");
			//containerCheckbox.style.displa
			for (let i = 0; i < data.length; i++) {

				// Create a label
				var label = document.createElement('label');
				label.style.display = "block";
				// Create a checkbox
				var checkbox = document.createElement('input');
				checkbox.type = "checkbox";
				checkbox.classList.add("checkbox")
				checkbox.name = data[i].name;
				checkbox.value = data[i].id;
				checkbox.style.marginRight = "5px";
				//checkbox.onclick = "checkCompany(this)";
				checkbox.onclick = function() {
					checkCompany(checkbox);
				}
				// Append the checkbox to the label
				label.appendChild(checkbox);
				let br = document.createElement("br");

				// Append the label text to the label
				label.appendChild(document.createTextNode(data[i].name));
				label.appendChild(br);
				// Append the label to the control area
				containerCheckbox.appendChild(label);
			}
		})

		.catch(error => {

			console.error('Fetch error:', error);
		});
}
