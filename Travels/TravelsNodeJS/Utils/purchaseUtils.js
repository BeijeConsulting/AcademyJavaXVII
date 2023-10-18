const { json } = require("express");
const myModule = require("../mysql");

let connection = myModule.getConnection();

module.exports = {
  getAllPurchases: function () {
    return new Promise((resolve, reject) => {
      connection.query("SELECT purchases.*, users.name, users.surname "
                      + "FROM purchases "
                      + "INNER JOIN users ON purchases.user_id = users.id ",
                      (err, rows, fields) => {
        for (let i = 0; i < rows.length; i++){
          rows[i].user = {
            name : rows[i].name,
            surname : rows[i].surname
          };
          delete rows[i].name;
          delete rows[i].surname;
        }
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
            resolve(rows[0]);
          }
        }
      );
    });
  },
  getPurchaseByUserId: function (user_id) {
    return new Promise((resolve, reject) => {
      connection.query(
        "SELECT * FROM purchases WHERE user_id = ?",
        [user_id],
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
  addPurchase: function(user_id, total, nTickets,){
    return new Promise( async (resolve, reject) => {
      await  connection.query(
        "INSERT INTO purchases (user_id, n_tickets, amount) VALUES (?, ?, ?)",
        [user_id, nTickets, total],
        (err, result, fields) => {
          if (err) {
            reject(err);
          } else{
            console.log("inserted id: ", result.insertId);
            resolve(result.insertId)
          }
        }
      );

    });
  },

};