const { json } = require("express");
const myModule = require("../mysql");

let connection = myModule.getConnection();

// module.exports = {
//   getAllPurchase: function () {
//     connection.query("SELECT * FROM purchases", (err, rows, fields) => {
//       if (err) throw err;

//       console.log("rows: ", rows);
//       //res.json(rows)
//       return rows;
//     });
//   }
// };

module.exports = {
  getAllPurchase: function () {
    return new Promise((resolve, reject) => {
      connection.query("SELECT * FROM purchases", (err, rows, fields) => {
        if (err) {
          reject(err);
        } else {
          resolve(rows);
        }
      });
    });
  },
  getPurchaseById: function (id) {
    return new Promise((resolve, reject) => {
      connection.query(
        "SELECT * FROM purchases WHERE id = ?",
        [id],
        (err, rows, fields) => {
          if (err) {
            reject(err);
          } else {
            resolve(rows);
          }
        }
      );
    });
  },
  getPurchaseByUser: function (user) {
    return new Promise((resolve, reject) => {
      connection.query(
        "SELECT * FROM purchases WHERE user = ?",
        [user],
        (err, rows, fields) => {
          if (err) {
            reject(err);
          } else {
            resolve(rows);
          }
        }
      );
    });
  }
};