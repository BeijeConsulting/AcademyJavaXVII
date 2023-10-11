const myModule = require("../mysql");
let connection = myModule.getConnection();

module.exports = {
    getXportById: function(id) {
        return new Promise((resolve, reject) => {
        connection.query('SELECT * FROM xports '+
                         'WHERE id = ?',[id], (err, rows, fields) => {
            if(err) {
                console.log("ERRORE get Xport By Id")
                reject(err);
            }else {
                resolve(rows[0]);
            }
           
        })
    }
    )},

    getAllXports: function() {
        return new Promise((resolve, reject) => {
        connection.query(' SELECT * FROM xports', (err, rows, fields) => {
            if(err) {
                console.log("ERRORE get all xports")
                reject(err);
            }else {
                resolve(rows);
            }
           
        })
    }
    )},

    getXportsByName:function(name) {
        return new Promise((resolve, reject) => {
        connection.query('SELECT * FROM xports '+
                         'WHERE name = ?',[name], (err, rows, fields) => {
            if(err) {
                console.log("ERRORE get Xports By name")
                reject(err);
            }else {
                resolve(rows);
            }
           
        })
    }
    )},

    getXportsByCity:function(id) {
        return new Promise((resolve, reject) => {
        connection.query('SELECT * FROM xports '+
                         'WHERE city_id = ?',[id], (err, rows, fields) => {
            if(err) {
                console.log("ERRORE get Xports By City Id")
                reject(err);
            }else {
                resolve(rows);
            }
           
        })
    }
    )},

    getXportsByType:function(type) {
        return new Promise((resolve, reject) => {
        connection.query('SELECT * FROM xports '+
                         'WHERE type = ?',[type], (err, rows, fields) => {
            if(err) {
                console.log("ERRORE get Xports By type")
                reject(err);
            }else {
                resolve(rows);
            }
           
        })
    }
    )},
}
