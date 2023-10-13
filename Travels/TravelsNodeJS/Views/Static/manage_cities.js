function getCountries(){
	  let token = localStorage.getItem("token");
  let headers = {
    'Content-type': 'application/json',
    'Authorization': `Bearer ` + token
  };

  fetchContainer("countries", 'GET', "", headers)
    .then((response) => response.json())
    .then((data) => {
    	console.log(data);
    	
      let arrayCountries = data;
      let body = document.body;
      let table = document.createElement("table");
      body.appendChild(table);
      table.innerHTML = `
        <tr>
          <th>Id</th>
          <th>Name</th>
          <th>Local name</th>
          <th>Cities</th>
        </tr>`;
    
      for (let i = 0; i < arrayCountries.length; i++) {
        let country = arrayCountries[i];
        let row = document.createElement("tr");
        table.appendChild(row);

        let rowHtml = `
          <td>${country.id}</td>
          <td>${country.name}</td>
          <td>${country.localName}</td>
          <td>`;

        for (let j = 0; j < country.cities.length; j++) {
          let city = country.cities[j];
          rowHtml += `
            <a href="./city_details.html?cityId=${city.id}">${city.cityName}</a><br/><br/>`;
        }
        rowHtml += `</td>`
        rowHtml += ` <td>
              <a href="./insert_city.html?countryId=${country.id}"><button>Add city</button></a><br>
              </td>`
               row.innerHTML = rowHtml;

      }
      
    
    })
    .catch((error) => {
      console.error("Error fetching data: ", error);
    });
}
function insertCity(){
	let token = localStorage.getItem("token");
		let api = "city";
		let method = "POST";
		let name = document.getElementById("name").value;
		console.log(name);
		let timeZone = document.getElementById("timeZone").value;
		console.log(timeZone)
		let countryId = document.getElementById("countryId").value;
		console.log(countryId);
		let body = JSON.stringify({
			cityName : name,
			timeZone : timeZone,
			countryId : countryId
		});
		let headers = {
			'Content-type': 'application/json',
			'Authorization': `Bearer ` + token
		};

		fetchContainer(api, method, body, headers)
			.then((response) => response.status)
			.then((status) => {

				if (status === 200) {
					location.replace(baseUrl + "manage_cities.html");
				} 
			});
}