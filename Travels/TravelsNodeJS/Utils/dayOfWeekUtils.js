const mysql = require('../mysql');

const connection = mysql.getConnection();

module.exports = {
    getAllDayOfWeek: function(){
        return new Promise((resolve, reject) =>{
            connection.query('SELECT * FROM days_of_week', (err, rows, fields) => {
                if (err) {
                    reject(err);
                }else{
                    resolve(rows);
                }
            })
        })

    },
    getDayOfWeekById: function(id){
        return new Promise((resolve, reject) =>{
            connection.query('SELECT * FROM days_of_week WHERE id = ?', [id] ,(err, rows, fields) => {
                if (err) {
                    reject(err);
                }else{
                    resolve(rows[0]);
                }
            })
        })
    },
    getDaysOfWeekBySchedule: function(schedule){
        return new Promise((resolve, reject) =>{
            connection.query('SELECT * FROM days_of_week WHERE schedule_id = ?', [schedule] ,(err, rows, fields) => {
                if (err) {
                    reject(err);
                }else{
                    //console.log(rows);
                    resolve(rows);
                }
            })
        })
    },
    addDayOfWeek: function(day, schedule){
        return new Promise((resolve, reject) =>{
            connection.query('INSERT INTO days_of_week (schedule_id, day) VALUES(?, ?)', [schedule, day] ,(err, rows, fields) => {
                if (err) {
                    reject(err);
                }else{
                    resolve(true);
                }
            })
        })
    },
    deleteDayOfWeek: function(day){
        return new Promise((resolve, reject) =>{
            connection.query('DELETE FROM days_of_week WHERE id = ?', [day.id] ,(err, rows, fields) => {
                if (err) {
                    reject(err);
                }else{
                    resolve(true);
                }
            })
        })
    }
}