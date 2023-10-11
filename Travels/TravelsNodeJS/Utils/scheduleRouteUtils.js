const myModule = require("../mysql");
const dayOfWeek = require("./dayOfWeekUtils");

let connection = myModule.getConnection();

module.exports = {
    getAllSchedulesByRoutes: function (routes, date) {
        let newSchedules = array();
        let day = date.dayOfMonth;
        let month = date.monthValue;
        let year = date.year;
        let stringDate = year + "-" + month + "-" + day; //2023-9-21
        let dayOfWeek;

        switch(date.dayOfWeek){
            case "MONDAY":
                dayOfWeek = 1;
                break;
            case "TUESDAY":
                dayOfWeek = 2;
                break;
            case "WEDNESDAY":
                dayOfWeek = 3;
                break;
            case "THURSDAY":
                dayOfWeek = 4;
                break;
            case "FRIDAY":
                dayOfWeek = 5;
                break;
            case "SATURDAY":
                dayOfWeek = 6;
                break;
            case "SUNDAY":
                dayOfWeek = 0;
                break;
        }

        this.getAllSchedulesByRoutes1(routes, stringDate).then((schedules) => {
            for(let i = 0; i < schedules.length; i++){
                dayOfWeek.getDaysOfWeekBySchedule(schedules[i]).then((days) => {
                    for(let j=0; j<days.length; j++){
                        if(days[j].day == dayOfWeek){
                            newSchedules.push(schedules[i]);
                        }
                    }
                })
            }
            return newSchedules;
        });
    },
    getAllSchedulesByRoutes1: function (routes, stringDate) { 
        
        return new Promise((resolve, reject) => {
            connection.query("SELECT * FROM schedules WHERE route_id = ? AND start_date <= ? AND (end_date is null or end_date >= ?)",
            [routes.id, stringDate, stringDate], (err, rows, fields) => {
                if (err) {
                reject(err);
                } else {
                resolve(rows);
                }
            });
        });
    },
    getScheduleById: function (id) {
        return new Promise((resolve, reject => {
            connection.query("SELECT * FROM schedules WHERE id = ?", [id], (err, rows, fields) => {
                if(err){
                    reject(err);
                } else {
                    resolve(rows);
                }
            });
        }))
    },
    getScheduleByRoute: function (route) {
        return new Promise((resolve, reject => {
            connection.query("SELECT * FROM schedules WHERE route_id = ?", [route], (err, rows, fields) => {
                if(err){
                    reject(err);
                } else {
                    resolve(rows);
                }
            });
        }))
    },
    getAllRoutesByCityNameLike: function (cityName) {
        return new Promise((resolve, reject) => {
            connection.query("SELECT * FROM routes WHERE city_name LIKE ?", [cityName], (err, rows, fields) => {
                if(err){
                    reject(err);
                } else {
                    resolve(rows);
                }
            });
        });
    }
}