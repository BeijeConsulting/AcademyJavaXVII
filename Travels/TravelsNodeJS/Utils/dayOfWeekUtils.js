const mysql = require('./mysql');

module.exports = {
    getAllDayOfWeek: function(){
        connection.query('SELECT * FROM days_of_week', (err, rows, fields) => {
            if (err) throw err
          
            console.log('rows: ', rows)
            res.json(rows)
        })
    },
    getDayOfWeekById: function(){

    }
}