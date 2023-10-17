
function addPassenger(){
	let n = document.getElementById("name").value;
	let s = document.getElementById("surname").value;
	
	let passenger = {name : n, surname: s};
	passengers.push(passenger);
	
	passengersJson = JSON.stringify(passengers);
	
	show();
	replaceDivHtml(passengers);
	
	document.getElementById("name").value = "";
	document.getElementById("surname").value = "";

	localStorage.setItem("passengers", passengersJson);
	console.log("passengers dalla sessione: ", localStorage.getItem("passengers"));
}
		
		
function removePassenger(i){			
	passengers.splice(i, 1);
	passengersJson = JSON.stringify(passengers);
	
	show();
	replaceDivHtml(passengers);
	
	localStorage.setItem("passengers", passengersJson);
	console.log("ho cancellato: ", localStorage.getItem("passengers"));
}


function replaceDivHtml(passengers){
	let text = "";
	
	for (let i = 0; i < passengers.length; i++){
		text += "<div class=\"card-container\">";
		text += "<label style=\"vertical-align: -webkit-baseline-middle;\" id=\"p-"+i+"\">" + passengers[i].name + " " + passengers[i].surname + "</label><button style=\"vertical-align: text-top; float: right; margin-right: 25px;\" class=\"secondary-button\" onclick=\"removePassenger("+i+")\">Remove</button></p>";	
		text += "</div><hr/>";
	}
	newDiv.innerHTML = text;
}
		
		
function show(){
	if (passengers.length >= maxPassengers){
		document.getElementById("form").style.display = "none";
		document.getElementById("continue").style.display = "block";
	} else {
		document.getElementById("form").style.display = "block";
		document.getElementById("continue").style.display = "none";
	}
}