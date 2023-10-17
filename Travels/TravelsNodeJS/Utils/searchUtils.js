const { json } = require("express");
const myModule = require("../mysql");
const { parseDate } = require("./utils");

let connection = myModule.getConnection();

module.exports = {
    searchTravels: function (departure_date, departure_city, arrival_city, passengers) {
        return new Promise((resolve, reject) => {
            //let departure_date = data.departure_date;
            //let passengers = data.passengers_number;
            //let day_of_week = 3;
            //console.log(day_of_week);
            //console.log("dayyyy", departure_date);
            const date = new Date(departure_date);

            // Ottieni il numero del giorno della settimana (0 = Domenica, 1 = Lunedì, ..., 6 = Sabato)
            const dayOfWeek = date.getDay();

            //console.log("Il numero del giorno della settimana è: " + dayOfWeek);
            departure_city = "%" + departure_city + "%";
            arrival_city = "%" + arrival_city + "%";

            connection.query(
                "SELECT s.id, dx.name as departure_name, s.departure_time, ax.name as arrival_name, s.arrival_time, s.duration, s.company_id, comp.name, s.price, t.empty_seats, s.seats, t.departure_date, dow.day " +
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

                "JOIN companies AS comp " +
                "ON s.company_id = comp.id " +

                "WHERE (s.start_date <= ?) AND " +
                "(s.end_date IS NULL OR s.end_date > ?) AND " +
                "(t.departure_date = ? OR t.departure_date IS NULL) AND " +
                "(t.empty_seats >= ? OR t.empty_seats IS NULL) AND " +
                "(s.seats >= ?) AND " +
                "(dow.day = ?) AND " +
                "(dc.name LIKE ? AND ac.name LIKE ?) ",
                [departure_date, departure_date, departure_date, parseInt(passengers), parseInt(passengers), dayOfWeek, departure_city, arrival_city],
                (err, rows, fields) => {
                    if (err) {
                        reject(err);
                    } else {
                        //console.log(rows)
                        resolve(rows);
                    }
                }
            );

        });
    }

}