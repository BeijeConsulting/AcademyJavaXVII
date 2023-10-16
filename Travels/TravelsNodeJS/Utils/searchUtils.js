const { json } = require("express");
const myModule = require("../mysql");
const { parseDate } = require("./utils");

let connection = myModule.getConnection();

module.exports={
    searchTravels: function(data){
        return new Promise((resolve, reject) => {
            let departure_date = data.departure_date;
            let passengers = data.passengers_number;
            let day_of_week = 3;
            console.log(day_of_week);
            console.log("dayyyy", departure_date);
            let departure_city = "'%" + data.departure_city + "%'";
            let arrival_city = "'%" + data.arrival_city + "%'";

            connection.query(
                "SELECT s.id, dx.name, s.departure_time, ax.name, s.arrival_time, s.duration, s.company_id, s.price, t.empty_seats, s.seats, t.departure_date, dow.day " + 
                "FROM schedules AS s " +
                            
                "JOIN routes AS r " +
                "ON s.route_id = r.id " +

                "JOIN xports AS dx " +
                "ON r.departure_xport_id = dx.id " +
                "JOIN cities as dc  " +
                "ON dx.city_id = dc.id " +
                
                "JOIN xports AS ax " +
                "ON r.arrival_xport_id = ax.id " +
                "JOIN cities as ac  " +
                "ON ax.city_id = ac.id " +

                "LEFT JOIN travels AS t " +
                "ON s.id = t.schedule_id " +

                "JOIN days_of_week AS dow " +
                "ON s.id = dow.schedule_id " +

                "WHERE (s.start_date <= ?) AND " +
                "(s.end_date IS NULL OR s.end_date > ?) AND " +
                "(t.departure_date = ? OR t.departure_date IS NULL) AND " +
                "(t.empty_seats >= ? OR t.empty_seats IS NULL) AND " +
                "(s.seats >= ?) AND " +
                "(dow.day = ?) AND " +
                "(dc.name LIKE ? AND ac.name LIKE ?) ",
                [departure_date, departure_date, departure_date, passengers, passengers, day_of_week, departure_city, arrival_city],
                (err, rows, fields) => {
                    if (err) {
                        reject(err);
                    } else {
                        console.log(rows)
                        resolve(rows);
                    }
                }
            );

        });
    }

}