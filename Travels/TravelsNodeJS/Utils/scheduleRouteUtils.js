const myModule = require("../mysql");

let connection = myModule.getConnection();

module.exports = {
    getAllSchedulesByRoutes: function (routes, date) {
        //return new Promise((resolve, reject) => {
        // connection.query("SELECT * FROM schedules WHERE route_id = ? AND start_date <= ? AND end_date >= ?",
        // [routes.id, date, date], (err, rows, fields) => {
        //     if (err) {
        //     reject(err);
        //     } else {
        //     resolve(rows);
        //     }
        // });
        // });
        this.getAllSchedulesByRoutes1(routes, date).then((schedules) => {
        });
    },
    getAllSchedulesByRoutes1: function (routes, date) { 
        return new Promise((resolve, reject) => {
            connection.query("SELECT * FROM schedules WHERE route_id = ? AND start_date <= ? AND (end_date is null or end_date >= ?)",
            [routes.id, date, date], (err, rows, fields) => {
                if (err) {
                reject(err);
                } else {
                resolve(rows);
                }
            });
            });
    }
}