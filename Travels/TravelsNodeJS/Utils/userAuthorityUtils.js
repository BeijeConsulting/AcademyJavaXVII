const myModule = require("../mysql");
let connection = myModule.getConnection();

module.exports = {
    getAllCustomers: function () {
        return new Promise((resolve, reject) => {
            connection.query('SELECT * FROM users as u JOIN users_authority as ua ON u.id = ua.user_id' +
                ' WHERE ua.authority_id = 1 ' +
                'AND ua.user_id not in (SELECT user_id ' +
                'FROM users_authority ' +
                'WHERE authority_id = 2)', (err, rows, fields) => {
                    if (err) {
                        console.log("ERRORE get all customers")
                        reject(err);
                    } else {
                        resolve(rows);
                    }

                })
        }
        )
    },

    getAllAdmins: function () {
        return new Promise((resolve, reject) => {
            connection.query('SELECT * FROM users as u JOIN users_authority as ua ON u.id = ua.user_id' +
                ' WHERE ua.authority_id = 2 ', (err, rows, fields) => {
                    if (err) {
                        console.log("ERRORE get all admins")
                        reject(err);
                    } else {
                        resolve(rows);
                    }

                })
        }
        )
    },

    getUserAuthorityByUserId: function (id) {
        return new Promise((resolve, reject) => {
            connection.query('SELECT * FROM users_authority ' +
                'WHERE user_id = ?', [id], (err, rows, fields) => {
                    if (err) {
                        console.log("ERRORE get UserAuthority By UserId")
                        reject(err);
                    } else {
                        resolve(rows);
                    }

                })
        }
        )
    },

}