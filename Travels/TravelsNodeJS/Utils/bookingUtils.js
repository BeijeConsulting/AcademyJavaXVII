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

    getBookingsByPurchaseId: function(purchaseId){
        return new Promise((resolve, reject) => {
            connection.query("SELECT b.*, u.name, u.surname, dep_x.name as dep, arr_x.name as arr FROM bookings AS b JOIN purchases AS p ON b.purchase_id = p.id " +
            "JOIN users AS u ON p.user_id = u.id " +
            "JOIN xports AS dep_x ON dep_x.id = b.departure_xport_id "+
            "JOIN xports AS arr_x ON arr_x.id = b.arrival_xport_id " +
            "WHERE p.id = ?", [purchaseId], (err, rows, fields) => {
                for(let i=0; i<rows.length; i++){
                  rows[i].user = {
                    name: rows[i].name,
                    surname: rows[i].surname
                  }
                  rows[i].departureXport = {
                    name: rows[i].dep
                  }
                  rows[i].arrivalXport = {
                    name: rows[i].arr
                  }
                  delete rows[i].name;
                  delete rows[i].surname;
                  delete rows[i].dep;
                  delete rows[i].arr;
                }
                if (err) {
                  reject(err);
                } else {
                  console.log(rows);
                  resolve(rows);
                }
            });
        });
    },

    getBookingsByTravelId: function(travelId){
      return new Promise((resolve, reject) => {
        connection.query("SELECT b.*, u.name, u.surname, dep_x.name as dep, arr_x.name as arr FROM bookings AS b JOIN purchases AS p ON b.purchase_id = p.id " +
        "JOIN users AS u ON p.user_id = u.id " +
        "JOIN xports AS dep_x ON dep_x.id = b.departure_xport_id "+
        "JOIN xports AS arr_x ON arr_x.id = b.arrival_xport_id " +
        "WHERE b.travel_id = ?", [travelId], (err, rows, fields) => {
            for(let i=0; i<rows.length; i++){
              rows[i].user = {
                name: rows[i].name,
                surname: rows[i].surname
              }
              rows[i].departureXport = {
                name: rows[i].dep
              }
              rows[i].arrivalXport = {
                name: rows[i].arr
              }
              delete rows[i].name;
              delete rows[i].surname;
              delete rows[i].dep;
              delete rows[i].arr;
            }
            if (err) {
              reject(err);
            } else {
              console.log(rows);
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

    createBooking: function(scheduleId, departure_xport, arrival_xport, departure_date, arrival_date, numTickets, amount) {
      let obj = {
        scheduleId : scheduleId,
        departureXport : departure_xport,
        arrivalXport: arrival_xport,
        departure_date : departure_date,
        arrival_date : arrival_date,
        numTickets : numTickets,
        amount : amount
      }

      console.log("OBJ", obj)
      return obj;
    }

//add booking

}