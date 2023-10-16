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
    getPassengersByPurchase: function(purchase){
        return new Promise((resolve, reject) =>{
            connection.query('SELECT * FROM passengers WHERE purchase_id = ?', [purchase.id], (err, rows, fields) => {
                if (err) {
                    reject(err);
                }else{
                    resolve(rows);
                }
            })
        })
    },
    addPassenger: function(purchase, name, surname){
        return new Promise((resolve, reject) =>{
            connection.query('INSERT INTO passengers (purchase_id, name, surname) VALUES(?, ?, ?)', [purchase.id, name, surname] ,(err, rows, fields) => {
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
                    resolve(true);
                }
           })
        })
    },
    addPassenger: function(passenger){
        return new Promise((resolve, reject) =>{
            connection.query('INSERT INTO passengers (purchase_id, name, surname) VALUES(?, ?, ?)', [passenger.purchase_id, passenger.name, passenger.surname] ,(err, rows, fields) => {
                if (err) {
                    reject(err);
                }else{
                    resolve(true);
                }
            })
        })
    }
}