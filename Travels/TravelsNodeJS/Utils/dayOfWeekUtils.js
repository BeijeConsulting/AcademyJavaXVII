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
    }
}