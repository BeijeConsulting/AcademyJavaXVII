const mysql = require('../mysql');

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
    getTravelPassengers: function(id){
        
    }
}