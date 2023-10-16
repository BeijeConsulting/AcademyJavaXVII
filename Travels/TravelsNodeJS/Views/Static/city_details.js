function getCityDetails(){
var url = window.location.href;

var parts = url.split('=');

var pathVariable = parts[parts.length - 1];
console.log("path " , pathVariable);
let body = "";
let token = localStorage.getItem("token");
let headers = {
			'Content-type': 'application/json',
			'Authorization': `Bearer ` + token
		};
fetchContainer("city_details/" + pathVariable, 'GET',body, headers)
    .then((response) => response.json())
    .then((data) => {
    	console.log(data); 
    	let city  = data;  
    	let cityDetailsContainer = document.getElementById('cityDetailsContainer');

  // Crea l'HTML dinamicamente
  let html = `
    <h1>${city.name}</h1>
    <br/>
    Id: ${city.id}<br/>
    Time zone: ${city.time_zone}<br/>
    <br><br>
  `;

  if (city.xports && city.xports.length > 0) {
    html += 'Xports: <br>';
    city.xports.forEach((xport) => {
      html += `
        Id: ${xport.id} <br>
          <input type="hidden" name="xportId" value="${xport.id}">
          <input type="hidden" name="cityId" value="${city.id}">
          Name: <input id="xportName" type="text" name="xportName" value="${xport.name}" required>
          <button type="submit" onclick="updateXport(${xport.id})">Update</button>
        Type: ${xport.type} <br>
        <br>

      `;
    });
  } else {
    html += 'No xports found. <br>';
  }
  html += `
			<a href="./insert_xport.html?cityId=${city.id}"><button>Insert xport</button></a><br><br>`

  // Inserisci l'HTML generato nel tuo container
  cityDetailsContainer.innerHTML = html;
    })
    .catch((error) => {
      console.error("Error fetching data: ", error);
    });
	
}
function updateXport(xportId){
	var url = window.location.href;

var parts = url.split('/');

var pathVariable = parts[parts.length - 1];
console.log("path " , pathVariable);
let body = document.getElementById("xportName").value;
console.log(body);
let token = localStorage.getItem("token");
let headers = {
			'Content-type': 'application/json',
			'Authorization': `Bearer ` + token
		};
fetchContainer("xport/" + xportId, 'PUT',body, headers)
    .then((response) => response.status)
    .then((status) => {
    	if(status === 200){
			location.replace(baseUrl +  "manage_cities.html");
		} 
    
    })
}