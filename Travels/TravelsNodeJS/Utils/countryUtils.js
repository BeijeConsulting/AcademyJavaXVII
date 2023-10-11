const { json } = require("express");
const myModule = require("../mysql");

let connection = myModule.getConnection();

module.exports = {

    getAllCountries: function () {
        return new Promise((resolve, reject) => {
          connection.query("SELECT * FROM countries", (err, rows, fields) => {
            if (err) {
              reject(err);
            } else {
              resolve(rows);
            }
          });
        });
    },

    getCountryById: function(id){
        return new Promise((resolve, reject) => {
            connection.query("SELECT * FROM countries WHERE id = ?", [id], (err, rows, fields) => {
              if (err) {
                reject(err);
              } else {
                resolve(rows[0]);
              }
            });
        });
    },
}