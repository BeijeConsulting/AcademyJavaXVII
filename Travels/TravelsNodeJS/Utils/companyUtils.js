const myModule = require('./mysql');

let connection = myModule.getConnection();

module.exports = {

    getAllCompanies: function () {
        return new Promise((resolve, reject) => {
          connection.query("SELECT * FROM companies", (err, rows, fields) => {
            if (err) {
              reject(err);
            } else {
              resolve(rows);
            }
          });
        });
      },


    getCompanyById: function(id){
        return new Promise((resolve, reject) => {
            connection.query("SELECT * FROM companies WHERE id = ?", [id], (err, rows, fields) => {
              if (err) {
                reject(err);
              } else {
                resolve(rows);
              }
            });
        });
    },
}
