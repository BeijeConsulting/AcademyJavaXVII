const myModule = require("../mysql");
let connection = myModule.getConnection();

module.exports = {
    getAllCities: function () {
        return new Promise((resolve, reject) => {
            connection.query(' SELECT * FROM cities ORDER BY cities.name', (err, rows, fields) => {
                if (err) {
                    console.log("ERRORE get all cities")
                    reject(err);
                } else {
                    resolve(rows);
                }
            })
        }
        )
    },

    getCityById: function (id) {
        return new Promise((resolve, reject) => {
            connection.query(' SELECT * FROM cities WHERE id = ?', [id], (err, rows, fields) => {
                if (err) {
                    console.log("ERRORE get city by id")
                    reject(err);
                } else {
                    resolve(rows[0]);
                }
            })
        }
        )
    },

    getCityByName: function(name) {
        return new Promise((resolve, reject) => {
            connection.query(' SELECT * FROM cities WHERE name = ?', [name], (err, rows, fields) => {
                if (err) {
                    console.log("ERRORE get city by name")
                    reject(err);
                } else {
                    resolve(rows[0]);
                }
            })
        }
        ) 
    }, 

    getCityByCountry: function(country) {
        return new Promise((resolve, reject) => {
            connection.query(' SELECT * FROM cities WHERE country = ?', [country], (err, rows, fields) => {
                if (err) {
                    console.log("ERRORE get city by country")
                    reject(err);
                } else {
                    resolve(rows);
                }
            })
        }
        ) 
    }

    //METODI DA FARE: addCity
}