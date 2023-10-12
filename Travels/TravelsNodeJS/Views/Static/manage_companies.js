function addCompany() {
	const inputEl = document.querySelector('#createCompany');
	//console.log(inputEl.value)

	let api = "insert_company";
	let method = 'POST';
	let headers = {
		'Content-type': 'application/json',
		'Authorization': `Bearer ` + token
	};
	

	let body = inputEl.value;

	fetchContainer(api, method, body, headers)
		.then((response) => {
			if (response.ok) {

				return response.json();
			} else {

				return response.json()

					.then(errorData => {

						console.error('failed to insert a company', errorData.message);
						errorElement.textContent = errorData.message;
						errorElement.classList.remove("hidden");
					});
			}
		})
		.then(company => {
			console.log("company", company)
			createCompanyDiv(company)
		})
}

function toggleCompany(company, btnRef) {
	console.log(company);
	let api = company.disabled_date != null ? 'enable_company/' + company.id : 'disable_company/' + company.id
	console.log("api", api);
	let method = 'PUT';
	let body = "";
	let headers = {
		'Content-type': 'application/json',
		'Authorization': `Bearer ` + token
	};


	fetchContainer(api, method, body, headers)
		.then(response => response.json())

		.then(updatedCompany => {
			console.log("updatedCompany", updatedCompany)

			//console.log(company, btnRef)
		})
		.catch(error => {
			console.error('Fetch error:', error);

		});

}

function getAllCompanies() {
	let api = "companies";
	let method = 'GET';
	let headers = {
		'Content-type': 'application/json',
		'Authorization': `Bearer ` + token
	};
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
			companies = data;
			//console.log(companies);
			companies.forEach((company) => {

				console.log(company)

				createCompanyDiv(company);

			});


		})

		.catch(error => {

			console.error('Fetch error:', error);
		});
}

function createCompanyDiv(company) {
	const companyDiv = document.createElement('div');
	const companyDivText = document.createElement('div');
	companyDivText.textContent = company.name;
	companyDivText.style.textAlign="center";
	companyDivText.style.flex = "1";
	companyDiv.classList.add("containerCompany");
	const companyDivButton = document.createElement('div');
	companyDivButton.style.flex ="1";
	const toggleButton = document.createElement('button');

	toggleButton.innerHTML = company.disabled_date != null ? 'Enable' : 'Disable';
	toggleButton.className = 'toggleButton';
	toggleButton.style.marginLeft = "5em";
	toggleButton.addEventListener('click', () => {
		if (toggleButton.innerHTML === 'Enable') {
			toggleButton.innerHTML = 'Disable';
		} else {
			toggleButton.innerHTML = 'Enable';
		}
		toggleCompany(company, toggleButton)
	})

	companyDivButton.appendChild(toggleButton);
	
	const br = document.createElement('br');
	companyDiv.appendChild(companyDivText);
	companyDiv.appendChild(companyDivButton);
	companyDiv.appendChild(br);

	companiesContainer.appendChild(companyDiv);
}

function filterCompany(valueChecked) {
	console.log("value checked", valueChecked.value)
	if (valueChecked.checked) {
		let api = "filtered_companies/" + valueChecked.value;
		let method = 'GET';
		let headers = {
			'Content-type': 'application/json',
			'Authorization': `Bearer ` + token
		};
		let body = "";

		fetchContainer(api, method, body, headers)
			.then((response) => {
				if (response.ok) {
					return response.json();
				} else {
					return response.json()
						.then(errorData => {
							console.error('filter company failed:', errorData.message);
							errorElement.textContent = errorData.message;
							errorElement.classList.remove("hidden");
						});
				}
			})
			.then((data) => {
				companies = data;
				companiesContainer.innerHTML = '';
				companies.forEach((company) => {
					createCompanyDiv(company);
				});
			})
			.catch(error => {
				console.error('Fetch error:', error);
			});
	} else {
		let divCompaniesContainer = document.getElementById("companiesContainer");
		divCompaniesContainer.innerHTML = '';
		getAllCompanies();
	}
}
