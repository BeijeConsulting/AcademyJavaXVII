function redirect() {
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

				if (status === 200) {
					location.replace(baseUrl + "new_purchase.html");
				} else {
					generatePopupLogin(); // genera il pop up in caso il token sia scaduto
				}
			});
	
	}
	else{
		generatePopupLogin();
	}
}