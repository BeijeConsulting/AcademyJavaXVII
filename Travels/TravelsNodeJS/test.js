const myModule = require('./mysql');
//uitls
const bookingUtils = require('./Utils/bookingUtils');
const cityUtils = require('./Utils/cityUtils');
const companyUtils = require('./Utils/companyUtils');
const countryUtils = require('./Utils/countryUtils');
const dayOfWeekUtils = require('./Utils/dayOfWeekUtils');
const purchasesUtils = require('./Utils/purchaseUtils');
const travelsUtils = require('./Utils/travelUtils');
const userAuthorityUtils = require('./Utils/userAuthorityUtils');
const xportUtils = require('./Utils/xportUtils');
const scheduleRouteUtils = require('./Utils/scheduleRouteUtils');

//controller
const bookingController = require('./RestController/bookingController');
const cityController = require('./RestController/cityController');
const companyController = require('./RestController/companyController');
const scheduleRouteController  = require('./RestController/scheduleRouteController');

const express = require('express')
const app = express()
const port = 3000
const path = require('path')
const bodyParser = require('body-parser');
const purchaseController = require('./RestController/purchaseController');

/*const mysql = require('mysql')
const connection = mysql.createConnection({
  host: 'bookstore.cvzu4xrxvkdz.eu-south-1.rds.amazonaws.com',
  user: 'admin',
  password: 'tltBJwVWgoY62y08brB1',
  database: 'fly_mary'
})*/

let connection = myModule.getConnection();

//connection.connect()

app.use(express.static('Views'))
//app.use('/', express.static(path.join(__dirname, 'public')))

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
    

//bookings
app.get('/api/bookings', (req, res) => {
    bookingController.getAllBookings().then((bookings) => {
        res.json(bookings);
    })
})

app.get('/api/booking/:id', (req, res) => {
    const id = req.params.id;
    bookingController.getBookingById(id).then((booking) => {
        res.json(booking);
    })
})

app.get('/api/bookings_by_purchase/:purchase_id', (req, res) => {
    const purchase_id = req.params.purchase_id;
    bookingController.getBookingsByPurchaseId(purchase_id).then((booking) => {
        res.json(booking);
    })
})

app.get('/api/bookings_by_travel/:travel_id', (req, res) => {
    const travel_id = req.params.travel_id;
    bookingController.getBookingByTravelId(travel_id).then((booking) => {
        res.json(booking);
    })
})


//cities

app.get('/api/cities', (req, res) =>{
    cityController.getAllCities().then((cities) =>{
        res.json(cities);
    })
})

app.get('/api/getCityByCountry/:country', (req, res) => {
    const country = req.params.country;
    cityUtils.getCityByCountry(country).then((cities) => {
        res.json(cities);
    })
})


app.get('/api/city_details/:id', (req, res) => {
    const id = req.params.id;
    cityController.getCityById(id).then((city) => {
        console.log("quiii:", city);
        res.json(city);
    })
})

//companies
app.get('/api/companies', (req, res) => {
    companyController.getAllCompanies().then((companies) => {
        res.json(companies);
    })

})

app.get('/api/company/:id', (req, res) => {
    const id = req.params.id;
    companyUtils.getCompanyById(id).then((company) => {
        res.json(company);
    })
})

app.get('/api/companies/:idsString', (req, res) => {
    const idsString = req.params.idsString;
    console.log("ho preso il parametro: ", idsString);
    companyUtils.getCompaniesByIds(idsString).then((companies) => {
        res.json(companies);
    })
})

app.get('/api/filtered_companies/enableAll', (req, res) => {
    companyController.getAllEnabledCompanies().then((companies) => {
        res.json(companies);
    })
})

app.get('/api/filtered_companies/disableAll', (req, res) => {
    companyController.getAllDisabledCompanies().then((companies) => {
        res.json(companies);
    })
})

app.get('/api/filtered_companies/viewAll', (req, res) => {
    companyController.getAllCompanies().then((companies) => {
        res.json(companies);
    })

})

app.get('/api/company_by_name/:name', (req, res) => {
    const name = req.params.name;
    companyUtils.getCompanyByName(name).then((company) => {
        res.json(company);
    })
})

app.post('/api/insert_company', (req, res) =>{
    let company = req.body;
    companyController.addCompany(company.name).then(() => res.json(company));
})

app.put('/api/enable_company/:id', (req, res) => {
    let id = req.params.id;
    companyController.enableCompany(id).then(() => true);
})

app.put('/api/disable_company/:id', (req, res) => {
    let id = req.params.id;
    companyController.disableCompany(id).then(() => true);
})

//countries
app.get('/api/countries', (req, res) => {
    cityController.getAllCountries().then((countries) => {
        //console.log("countrieeeeeees: " , countries);
        res.json(countries);
    })
})

app.get('/api/country/:id', (req, res) => {
    const id = req.params.id;
    countryUtils.getCountryById(id).then((country) => {
        res.json(country);
    })
})



//purchases
app.get('/api/purchases', (req, res) => {
    purchaseController.getAllPurchases().then((purchases) => {
        res.json(purchases);
    })
})

app.get('/api/purchase/:user_id', (req, res) =>{
    const user_id = req.params.user_id;
   purchaseController.getPurchasesByUserId(user_id).then((purchase) => {
        res.json(purchase)
    });
})

//routes
app.get('/api/routes', (req, res) => {
    scheduleRouteController.getAllRoutes().then((routes) => {
        res.json(routes);
    })
})

app.get('/api/route/:id', (req, res) =>{
    const id = req.params.id;
    scheduleRouteController.getRouteById(id).then((route) => {
        res.json(route)
    });
})


//schedules
app.get('/api/schedules/:route_id', (req, res) =>{
    const route_id = req.params.route_id;
    scheduleRouteController.getSchedulesByRouteId(route_id).then((schedules) => {
        res.json(schedules)
    });
})


//travels
app.get('/api/getAllTravels', (req, res) => {
    travelsUtils.getAllTravels().then((travel) => {
        res.json(travel);
    })
})

app.get('/api/getTravelById/:id', (req, res) => {
    const id = req.params.id;
    travelsUtils.getTravelById(id).then((travel) => {
        res.json(travel);
    })
})

app.get('/api/getTravelsByDeparture/:departure', (req, res) => {
    const departure = req.params.departure;
    travelsUtils.getTravelsByDeparture(departure).then((travel) => {
        res.json(travel);
    })
})

app.get('/api/getTravelsByCity/:city', (req, res) => {
    const city = req.params.city;
    travelsUtils.getTravelsByCity(city).then((travel) => {
        res.json(travel);
    })
})

app.get('/api/getTravelsByArrival/:arrival', (req, res) => {
    const arrival = req.params.arrival;
    travelsUtils.getTravelsByArrival(arrival).then((travel) => {
        res.json(travel);
    })
})

//users
app.get('/api/getUserById/:id', (req, res) => {
    const id = req.params.id;
    userUtils.getUserById(id).then((user) => {
        res.json(user);
    })
})

//users_authority
app.get('/api/customers', (req, res) => {
    userAuthorityUtils.getAllCustomers().then((customers) => {
        res.json(customers);
    })
})

app.get('/api/admins', (req, res) => {
    userAuthorityUtils.getAllAdmins().then((admins) => {
        res.json(admins);
    })
})

app.get('/api/userAuthorityByUserId/:id', (req, res) => {
    const id = req.params.id;
    userAuthorityUtils.getUserAuthorityByUserId(id).then((userAuthority) => {
        res.json(userAuthority);
    })
})

//xports
app.get('/api/xport/:id', (req, res) => {
    const id = req.params.id;
    xportUtils.getXportById(id).then((xport) => {
        res.json(xport);
    })
})

app.get('/api/xports', (req, res) => {
    xportUtils.getAllXports().then((xports) => {
        res.json(xports);
    })
})

app.get('/api/xports_by_name/:name', (req, res) => {
    const name = req.params.name;
    xportUtils.getXportsByName(name).then((xports) => {
        res.json(xports);
    })
})

app.get('/api/xports_by_city/:id', (req, res) => {
    const id = req.params.id;
    xportController.getXportsByCity(id).then((xports) => {
        res.json(xports);
    })
})

app.get('/api/xports_by_type/:type', (req, res) => {
    const type = req.params.type;
    xportUtils.getXportsByType(type).then((xports) => {
        res.json(xports);
    })
})

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
  })