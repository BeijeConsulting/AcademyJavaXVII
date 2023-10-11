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
    test: function(){
        () => {
            let a="10";
            console.log(a);
            return a;
        };
    }
}