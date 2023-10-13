const myModule = require("../mysql");
let connection = myModule.getConnection();

module.exports = {
    getUserById: function (id) {
        return new Promise((resolve, reject) => {
            connection.query(' SELECT * FROM users WHERE id=?',[id],  (err, rows, fields) => {
                if (err) {
                    console.log("ERRORE get user by id")
                    reject(err);
                } else {
                    resolve(rows[0]);
                }
            })
        }
        )
    },

    getUserByEmail: function (email) {
        return new Promise((resolve, reject) => {
            connection.query(' SELECT * FROM users WHERE email=?',[email],  (err, rows, fields) => {
                if (err) {
                    console.log("ERRORE get user by mail")
                    reject(err);
                } else {
                    resolve(rows);
                }
            })
        }
        )
    },

    // da testare
    emailExist: function (email) {
        return new Promise((resolve, reject) => {
            connection.query(' SELECT * FROM users WHERE email=?',[email],  (err, rows, fields) => {
                if (err) {
                    console.log("ERRORE get user by mail boolean")
                    reject(err);
                } else {
                    resolve(true);
                }
            })
        }
        )
    },
    
    // da testare
    getUserList: function (listUser) {
        let users = [];
        listUser.forEach(element => {
            users.push(getUserById(element.id))
        });
       return users;
    },

    //METODI DA FARE: addUser, addAdmin

    editUserDetails: function(id, name, surname) {
        //controllo parametri non nulli
        return new Promise((resolve, reject) => {
            connection.query("UPDATE users SET `name` = ?, `surname` = ? WHERE `id` = ?", [name, surname, id], (err, rows, fields) => {
                if (err) {
                    reject(err);
                } else {
                    resolve(true);
                }
            });
        });
    },

    editUserPassword: function(id, oldPw, newPw) {
        //controllo parametri old/new uguali
        return new Promise((resolve, reject) => {
            connection.query("UPDATE users SET `password` = ?, WHERE `id` = ?", [newPw, id], (err, rows, fields) => {
                if (err) {
                    reject(err);
                } else {
                    resolve(true);
                }
            });
        });
    },

    disableUser: function(id){
        const today = new Date();
        return new Promise((resolve, reject) => {
          connection.query("UPDATE users SET `disabled_date` = ? WHERE `id` = ?", [today, id] , (err, rows, fields) => {
              if (err) {
                  reject(err);
              } else {
                  resolve(true);
              }
          });
        });
      }


}