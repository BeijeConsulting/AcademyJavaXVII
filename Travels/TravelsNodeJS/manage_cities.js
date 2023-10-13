const myModule = require('./mysql');
const express = require('express')
const manage_cities = express()
const cityController = require('./RestController/cityController');

const port = 3000
const path = require('path')
const bodyParser = require('body-parser');

let connection = myModule.getConnection();

manage_cities.use(express.static('Views'))
//app.use('/', express.static(path.join(__dirname, 'public')))

manage_cities.use(bodyParser.urlencoded({ extended: false }));
manage_cities.use(bodyParser.json());

manage_cities.get('/api/manage_cities', (req, res) =>{
    cityController.getAllCities().then((cities) =>{
        res.json(cities);
    })
})