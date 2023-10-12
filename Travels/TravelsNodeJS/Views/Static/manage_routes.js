function getTypeAbbreviation(routeType) {
  switch (routeType) {
    case 'plane':
      return 'P';
    case 'train':
      return 'T';
    case 'bus':
      return 'B';
    case 'ship':
      return 'S';
    default:
      return 'Error';
  }
}

function clearRouteTable() {
    routeTable.innerHTML = ''; // Pulisci la tabella
}

/*function clearSearch() {
    // Cancella il testo nell'input di ricerca
    document.getElementById('searchName').value = '';
    fetchRoutes();
}*/

function addRouteRows(routesList) {
	let htmlContent = '';

    htmlContent += 
			`<tr>
				<th>Departure</th>
				<th>Arrival</th>
				<th>Type</th>
				<th></th>
			</tr>`;
			
    routesList.forEach((route) => {
        htmlContent += 
        `<tr>
            <td>`+ route.departureXport.name +`</td>
            <td>`+ route.arrivalXport.name +`</td>
            <td>` + getTypeAbbreviation(route.type) +`</td>
            <td><a href="./manage_route_schedules.html?routeId=`+ route.id +`">Details</a></td>
         </tr>`; 
    });
    routeTable.insertAdjacentHTML('beforeend', htmlContent);
}

function fetchXports(){
	let htmlContent = '';
	
	let api = "xports";
	
	let method = "GET";
	
	let body = "";
	let  headers = {'Content-type': 'application/json',
				    'Authorization': `Bearer ` + token};
	
	fetchContainer(api, method, body, headers)
	    .then((response) => response.json())
	    .then((xportsList) => {
			
	    	xportsList.forEach((xport) => {
	            htmlContent +=  
					`<option id="` + xport.id + `" 
							 value="` + xport.id + `">
							 ` + xport.name + 
							 `</option>`
							 ;  
	    	});
	    	
	     	document.getElementById("departureXport").innerHTML = htmlContent;
	     	document.getElementById("arrivalXport").innerHTML = htmlContent;
	     });
}

function fetchRoutes(){
	
	let api = "routes";
	
	let method = "GET";
	
	let body = "";
	let  headers = {'Content-type': 'application/json',
				    'Authorization': `Bearer ` + token};
				    
	clearRouteTable();
	
	fetchContainer(api, method, body, headers)
	    .then((response) => response.json())
	    .then((routesList) => {
	    	addRouteRows(routesList)
	     });
}

function fetchSearchName(){
	let searchName = document.getElementById("searchName").value;
		
	let api = "routes/"+searchName;
	
	let method = "GET";
	
	let body = "";
	let  headers = {'Content-type': 'application/json',
				    'Authorization': `Bearer ` + token};
				    
	clearRouteTable();
	
	fetchContainer(api, method, body, headers)
	    .then((response) => response.json())
	    .then((routesList) => {
	    	addRouteRows(routesList)
	     });
}


function fetchInsertRoute(){
		
		let departureXport = document.getElementById("departureXport").value;
		let arrivalXport = document.getElementById("arrivalXport").value;
		let type = document.getElementById("type").value;
		
		let errorElement = document.getElementById("error-message");
		errorElement.classList.add("hidden");
		
		let api = "route";
		

		let body=JSON.stringify({
			departureXportId: departureXport,
			arrivalXportId: arrivalXport,
			type: type
		  });
		
		let  headers = {
	            'Content-type': 'application/json',
	            'Authorization': `Bearer ` + token};


		let method = 'POST';

		fetchContainer(api, method, body, headers)
		.then(response => {
			if (response.ok) {
				return response.json();
	        } else {
				return response.json()
	            .then(errorData => {
					console.error('Insert route failed:', errorData.message);
	                errorElement.textContent = errorData.message;
	                errorElement.classList.remove("hidden");
	                throw new Error('Insert route failed');
	            });
	        }
		})
		.then((json) => {
			fetchRoutes();
			alert("Route added successfully.");
			closePopup();
		})
		.catch(error => {
			console.error('Fetch error:', error);
	    });
	}
	
function generatePopupAddRoute(){
	let htmlContent = '';
	htmlContent += `
		<div class="modal-content">
			<h2>New Route</h2>
			
			<div id="error-message" class="error-message hidden">
			</div>
			
			<label for="departureXport"><b>Departure Xport:</b></label>
			<br>
			<select name="departureXport" id="departureXport" >
			</select>
			<br>
			<br>
				
			<label for="arrivalXport"><b>Arrival Xport:</b></label>
			<br>
			<select name="arrivalXport" id="arrivalXport">
			</select>
			<br>
			<br>
			
			<label for="type"><b>Type:</b></label>
			<select name="type" id="type">
			 	<option value="plane">Plane</option>
			</select>
			
			<div class="modal-buttons">
				<button class="primary-button" id="login" onclick="fetchInsertRoute()">Add</button>
			</div>
			
		</div>
		`;
	generatePopup(htmlContent);
	fetchXports();
}
