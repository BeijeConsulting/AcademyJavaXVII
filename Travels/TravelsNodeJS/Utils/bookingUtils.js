const { json } = require("express");
const myModule = require("../mysql");

let connection = myModule.getConnection();

module.exports = {

    getAllBookings: function () {
        return new Promise((resolve, reject) => {
          connection.query("SELECT * FROM bookings", (err, rows, fields) => {
            if (err) {
              reject(err);
            } else {
              resolve(rows);
            }
          });
        });
    },

    getBookingById: function(id){
        return new Promise((resolve, reject) => {
            connection.query("SELECT * FROM bookings WHERE id = ?", [id], (err, rows, fields) => {
              if (err) {
                reject(err);
              } else {
                resolve(rows[0]);
              }
            });
        });
    },

    getBookingByPurchaseId: function(purchaseId){
        return new Promise((resolve, reject) => {
            connection.query("SELECT * FROM bookings WHERE purchase_id = ?", [purchaseId], (err, rows, fields) => {
              if (err) {
                reject(err);
              } else {
                resolve(rows);
              }
            });
        });
    },

    getBookingsByTravelId: function(travelId){
      return new Promise((resolve, reject) => {
          connection.query("SELECT * FROM bookings WHERE travel_id = ?", [travelId], (err, rows, fields) => {
            if (err) {
              reject(err);
            } else {
              resolve(rows);
            }
          });
      });
  },

    getBookingByTravelId: function(travelId){
        return new Promise((resolve, reject) => {
            connection.query("SELECT * FROM bookings WHERE travel_id = ?", [travelId], (err, rows, fields) => {
              if (err) {
                reject(err);
              } else {
                resolve(rows);
              }
            });
        });
    },

//add booking

}