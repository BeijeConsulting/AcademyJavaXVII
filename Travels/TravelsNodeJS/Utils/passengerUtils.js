const mysql = require('../mysql');
const bookingUtils = require('../Utils/bookingUtils');
const purchaseUtils = require('../Utils/purchaseUtils');

const connection = mysql.getConnection();

module.exports = {
    getPassengerById: function(id){
        return new Promise((resolve, reject) =>{
            connection.query('SELECT * FROM passengers WHERE id = ?', [id] ,(err, rows, fields) => {
                if (err) {
                    reject(err);
                }else{
                    resolve(rows[0]);
                }
            })
        })
    },
    getAllPassengers: function(){
        return new Promise((resolve, reject) =>{
            connection.query('SELECT * FROM passengers',(err, rows, fields) => {
                if (err) {
                    reject(err);
                }else{
                    resolve(rows);
                }
            })
        })
    },
    getPassengersByPurchaseId: function(purchase_id){
        return new Promise((resolve, reject) =>{
            connection.query('SELECT * FROM passengers WHERE purchase_id = ?', [purchase_id], (err, rows, fields) => {
                if (err) {
                    reject(err);
                }else{
                    resolve(rows);
                }
            })
        })
    },
    addPassenger: function(purchase_id, name, surname){
        console.log("add pass: ", purchase_id, name, surname);
        return new Promise((resolve, reject) =>{
            connection.query('INSERT INTO passengers (purchase_id, name, surname) VALUES(?, ?, ?)', [purchase_id, name, surname] ,(err, rows, fields) => {
                if (err) {
                    reject(err);
                }else{
                    resolve(true);
                }
            })
        })
    },
    getTravelPassengers: function(travel_id){
        return new Promise(async (resolve, reject) =>{
           connection.query('SELECT p.* FROM bookings AS b JOIN passengers AS p ON b.purchase_id = p.purchase_id WHERE b.travel_id = ?', [travel_id],(err, rows, fields) => {
                if (err) {
                    reject(err);
                }else{
                    resolve(rows);
                }
           })
        })
    },
}