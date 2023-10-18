const myModule = require("../mysql");
const routeUtils = require("./scheduleRouteUtils");
let connection = myModule.getConnection();

module.exports = {
    getAllTravels: function () {
        return new Promise((resolve, reject) => {
            connection.query(' SELECT * FROM travels', (err, rows, fields) => {
                if (err) {
                    console.log("ERRORE get all travels")
                    reject(err);
                } else {
                    resolve(rows);
                }
            })
        }
        )
    },

    getTravelById: function (id) {
        return new Promise((resolve, reject) => {
            connection.query(' SELECT * FROM travels WHERE id = ?', [id], (err, rows, fields) => {
                if (err) {
                    console.log("ERRORE get travel by id")
                    reject(err);
                } else {
                    resolve(rows[0]);
                }
            })
        }
        )
    },

    getTravelsByDeparture: function (departure) {
        return new Promise((resolve, reject) => {
            connection.query(' SELECT * FROM travels WHERE departure_date = ?', [departure], (err, rows, fields) => {
                if (err) {
                    console.log("ERRORE get travels by departure date")
                    reject(err);
                } else {
                    resolve(rows);
                }
            })
        }
        )
    },

    getTravelsByArrival: function (arrival) {
        return new Promise((resolve, reject) => {
            connection.query(' SELECT * FROM travels WHERE arrival_date = ?', [arrival], (err, rows, fields) => {
                if (err) {
                    console.log("ERRORE get travels by arrival date")
                    reject(err);
                } else {
                    resolve(rows);
                }
            })
        }
        )
    },

   /* getTravelsByCity: function (city) {
        let routes = routeUtils.getAllRoutesByCityNameLike(city)
        console.log("ROUTES: " , routes)
        routes.forEach(element => {
            return new Promise((resolve, reject) => {
                connection.query(' SELECT * FROM travels WHERE route_id = ?', [element.id], (err, rows, fields) => {
                    if (err) {
                        console.log("ERRORE get travels by city")
                        reject(err);
                    } else {
                        resolve(rows);
                    }
                })
            }
            )
        });

    },*/

    // da testare schedule e date
    getTravelByScheduleAndDate: function (schedule_id, date) {
        let day = date.dayOfMonth;
        let month = date.monthValue;
        let year = date.year;
        let stringDate = year + "-" + month + "-" + day; //2023-9-21
        return new Promise((resolve, reject) => {
            connection.query(' SELECT * FROM travels WHERE schedule_id = ? and departure_date= ? ', [schedule_id, date], (err, rows, fields) => {
                if (err) {
                    console.log("ERRORE get travels by schedule and date")
                    reject(err);
                } else {
                    resolve(rows[0]);
                }
            })
        }
        )
    },

    // METODI DA FARE: addTravel , editEmptySeats

    addTravel: function (schedule_id, route_id, departure_date, arrival_date, empty_seats) {
        return new Promise((resolve, reject) => {
            connection.query('INSERT INTO travels (schedule_id, route_id, departure_date, arrival_date, empty_seats) VALUES (?, ?, ?, ?, ?)'
            , [schedule_id, route_id, departure_date, arrival_date, empty_seats], async (err, rows, fields) => {
                if (err) {
                    console.log("ERROR add travels")
                    reject(err);
                } else {
                    resolve(rows.insertedId);
                }
            })
        }
        )
    },

    editEmptySeats: function(id, empty_seats){
        return new Promise((resolve, reject) => {
            connection.query("UPDATE travels SET empty_seats = ? WHERE id = ?", [empty_seats, id], (err, rows, fields) => {
                if (err) {
                    reject(err);
                } else {
                    resolve(true);
                }
            });
        });
    }

}