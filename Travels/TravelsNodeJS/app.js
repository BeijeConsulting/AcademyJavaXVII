const myModule = require('./mysql');
const dayOfWeekUtils = require('./Utils/dayOfWeekUtils');
const purchasesUtils = require('./Utils/purchaseUtils');
const travelsUtils = require('./Utils/travelUtils');
const companyUtils = require('./Utils/companyUtils');
const countryUtils = require('./Utils/countryUtils');
const bookingUtils = require('./Utils/bookingUtils');

const express = require('express')
const app = express()
const port = 3000
const path = require('path')
const bodyParser = require('body-parser')

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

app.get('/api/getAllTravels', (req, res) => {
    travelsUtils.getAllTravels().then((travel) => {
        res.json(travel);
    })

//     console.log("ciao", purchases)
//    res.send(purchases);
})
    
app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})



app.get('/api/test', (req, res) => {
    dayOfWeekUtils.getAllDayOfWeek().then((days => {
        res.json(days);
    }))
})

app.get('/api/purchases', (req, res) => {
    purchasesUtils.getAllPurchase().then((purchases) => {
        res.json(purchases);
    })
})


//companies
app.get('/api/companies', (req, res) => {
    companyUtils.getAllCompanies().then((companies) => {
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

app.get('/api/enabled_companies', (req, res) => {
    companyUtils.getAllEnabledCompanies().then((companies) => {
        res.json(companies);
    })
})

app.get('/api/disabled_companies', (req, res) => {
    companyUtils.getAllDisabledCompanies().then((companies) => {
        res.json(companies);
    })
})

app.get('/api/company_by_name/:name', (req, res) => {
    const name = req.params.name;
    console.log(name);
    companyUtils.getCompanyByName(name).then((company) => {
        res.json(company);
    })
})


//countries
app.get('/api/countries', (req, res) => {
    countryUtils.getAllCountries().then((countries) => {
        res.json(countries);
    })
})

app.get('/api/country/:id', (req, res) => {
    const id = req.params.id;
    countryUtils.getCountryById(id).then((country) => {
        res.json(country);
    })
})


//bookings
app.get('/api/bookings', (req, res) => {
    bookingUtils.getAllBookings().then((bookings) => {
        res.json(bookings);
    })
})

app.get('/api/booking/:id', (req, res) => {
    const id = req.params.id;
    bookingUtils.getBookingById(id).then((booking) => {
        res.json(booking);
    })
})

app.get('/api/booking_by_purchase/:purchase_id', (req, res) => {
    const purchase_id = req.params.purchase_id;
    bookingUtils.getBookingByPurchaseId(purchase_id).then((booking) => {
        res.json(booking);
    })
})

app.get('/api/booking_by_travel/:travel_id', (req, res) => {
    const travel_id = req.params.travel_id;
    bookingUtils.getBookingByTravelId(travel_id).then((booking) => {
        res.json(booking);
    })
})



//users
app.get('/api/getUserById/:id', (req, res) => {
    const id = req.params.id;
    userUtils.getUserById(id).then((user) => {
        res.json(user);
    })
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

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})