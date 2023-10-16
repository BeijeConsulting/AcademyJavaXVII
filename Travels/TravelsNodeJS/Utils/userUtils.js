const myModule = require("../mysql");
let connection = myModule.getConnection();

module.exports = {
    getUserById: function (id) {
        return new Promise((resolve, reject) => {
            connection.query(' SELECT * FROM users WHERE id=?', [id], (err, rows, fields) => {
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
            connection.query(' SELECT * FROM users WHERE email=?', [email], (err, rows, fields) => {
                if (err) {
                    console.log("ERRORE get user by mail")
                    reject(err);
                } else {
                    resolve(rows[0]);
                }
            })
        }
        )
    },

    // da testare
    emailExist: function (email) {
        return new Promise((resolve, reject) => {
            connection.query(' SELECT * FROM users WHERE email=?', [email], (err, rows, fields) => {
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

    editUserDetails: function (id, name, surname) {
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

    editUserPassword: function (id, oldPw, newPw) {
        //controllo parametri old/new uguali
        return new Promise((resolve, reject) => {
            connection.query("UPDATE users SET `password` = ? WHERE `id` = ?", [newPw, id], (err, rows, fields) => {
                if (err) {
                    reject(err);
                } else {
                    resolve(true);
                }
            });
        });
    },

    disableUser: function(id){
        //da randomizzare la mail
        const today = new Date();
        return new Promise((resolve, reject) => {
            connection.query("UPDATE users SET `disabled_date` = ? WHERE `id` = ?", [today, id], (err, rows, fields) => {
                if (err) {
                    reject(err);
                } else {
                    resolve(true);
                }
            });
        });
    },

    disableUserbyEmail: function (email) {
        const today = new Date();
        return new Promise((resolve, reject) => {
            connection.query("UPDATE users SET `disabled_date` = ? WHERE `email` = ?", [today, email], (err, rows, fields) => {
                if (err) {
                    reject(err);
                } else {
                    resolve(true);
                }
            });
        });
    },

    addUser: function (name, surname, email, password) {
        const today = new Date();
        return new Promise((resolve, reject) => {
            connection.query('INSERT INTO users (name, surname, email, password, creation_date) VALUES (?, ?, ?, ?, ?)', [name, surname, email, password, today], async (err, rows, fields) => {
                if (err) {
                    console.log("ERROR add user")
                    reject(err);
                } else {
                    resolve(true);
                }
            })
        }
        )
    },

    addAdmin: function (name, surname, email, password) {
        const today = new Date();
        return new Promise((resolve, reject) => {
            connection.query('INSERT INTO users (name, surname, email, password, creation_date) VALUES (?, ?, ?, ?, ?)', [name, surname, email, password, today], (err, rows, fields) => {
                if (err) {
                    console.log("ERROR add user")
                    reject(err);
                } else {
                    resolve(this.getUserByEmail(email).id);
                }
            })
        }
        )
    },

    insertAuthority: function (type, id) {
        //console.log("type aut", type)
        //console.log("id aut", id)
        return new Promise((resolve, reject) => {
            connection.query('INSERT INTO users_authority (user_id, authority_id) VALUES (?, ?)', [id, type], (err, rows, fields) => {
                if (err) {
                    console.log("ERROR add authority")
                    reject(err);
                } else {
                    resolve(rows);
                }
            })
        }
        )
    }
}