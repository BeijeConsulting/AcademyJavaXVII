const myModule = require("../mysql");
let connection = myModule.getConnection();

module.exports = {
    getAllTravels: function() {
        return new Promise((resolve, reject) => {
        connection.query(' SELECT * FROM travels', (err, rows, fields) => {
            if(err) {
                console.log("ERRORE get all travels")
                reject(err);
            }else {
                resolve(rows);
            }
           
        })
    }
)}
}