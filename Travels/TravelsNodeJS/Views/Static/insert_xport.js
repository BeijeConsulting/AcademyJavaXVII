const urlParams = new URLSearchParams(window.location.search);
        const cityId = parseInt(urlParams.get('cityId'));
function getCities(){
	let body = "";
	let headers = "";
	fetchContainer("cities", 'GET',body, headers)
    .then((response) => response.json())
    .then((data) => {

        let cities = data;
        
        let selectHtml = '<label for="city">City:</label><br><select name="cityId" id="selectCity">';
        
        cities.forEach((city) => {
            selectHtml += '<option value="' + city.id + '"';
            if (cityId === city.id) {
                selectHtml += ' selected';
            }
            selectHtml += '>' + city.name + '</option>';
        });
        
        selectHtml += '</select><br><br>';
        
        // Aggiungi il selectHtml alla tua pagina HTML
        // Ad esempio, puoi aggiungerlo a un elemento div con id "citySelectContainer"
        document.getElementById("citySelectContainer").innerHTML = selectHtml;
 
    })
	}

function insertXport(){
     let body = JSON.stringify( {
		 cityId : parseInt(document.getElementById("selectCity").value),
		 xportName : document.getElementById("name").value,
		 type : document.getElementById("type").value
	 });
let token = localStorage.getItem("token");
let headers = {
			'Content-type': 'application/json',
			'Authorization': `Bearer ` + token
		};
fetchContainer("xport", 'POST',body, headers)
    .then((response) => response.status)
    .then((status) => {
		console.log(status);
    	if(status === 200){
			location.replace(baseUrl + "city_details.html?cityId=" + cityId)
		}
    })
      .catch((error) => {
      console.error("Error fetching data: ", error);
    });
}