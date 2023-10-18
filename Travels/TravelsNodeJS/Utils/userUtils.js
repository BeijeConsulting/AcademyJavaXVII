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

    getUserByEmailAndPassword: function (email, password) {
        return new Promise((resolve, reject) => {
            connection.query(
            "SELECT users.id, count(*) as count FROM users " +
            "JOIN users_authority " + 
            "on users.id = users_authority.user_id " +
            "where email = ? " +
            "AND BINARY password = ? ", [email, password], (err, rows, fields) => { 
        
                if (err) {
                    console.log("ERRORE get user by mail adnd password")
                    reject(err);
                } else {
                    resolve(rows[0]);
                }
            })
        }
        )
    },


    emailExist: function (email) {
        return new Promise((resolve, reject) => {
            connection.query(' SELECT * FROM users WHERE email=?', [email], (err, rows, fields) => {
                if (err) {
                    console.log("ERRORE get user by mail boolean")
                    reject(err);
                } else {
                    resolve(rows.length > 0);
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

    disableUser: async function(id){
        //da randomizzare la mail
        const today = new Date();
        let random;
        let disabledEmail;
        let  isNotValid;


        return new Promise(async (resolve, reject) => {
            do {
                random = Math.floor(Math.random() * 100000) + 1;;
                console.log("ransom: ", random);
                disabledEmail = random + "@disabled.com";
                isNotValid = await this.emailExist(disabledEmail)
            } while (isNotValid);

            connection.query("UPDATE users SET `disabled_date` = ?, `email` = ? WHERE `id` = ?", [today, disabledEmail, id], (err, rows, fields) => {
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
        return new Promise(async (resolve, reject) => {
            connection.query('INSERT INTO users (name, surname, email, password, creation_date) VALUES (?, ?, ?, ?, ?)', [name, surname, email, password, today], (err, rows, fields) => {
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