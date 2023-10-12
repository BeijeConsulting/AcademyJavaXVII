const { json } = require("express");
const myModule = require("../mysql");

let connection = myModule.getConnection();

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
  },
  addPurchases: function (purchases) {
    return new Promise((resolve, reject) => {
      connection.query(
        "INSERT INTO purchases (user_id, n_tickets, amount) VALUES (?, ?, ?)",
        [purchases.user_id, purchases.n_tickets, purchases.amount],
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
  //mi devo ritornare l'id della purchase appena creata
  //con questo id prendo la lista passeggeri e la metto nel db
  //per ogni booking della lista mi devo vedere se ha il travel e lo cerco dalla schedule
};