module.exports = {
  getConnection: function() {
    const mysql = require('mysql')
    const connection = mysql.createConnection({
      host: 'bookstore.cvzu4xrxvkdz.eu-south-1.rds.amazonaws.com',
      user: 'admin',
      password: 'tltBJwVWgoY62y08brB1',
      database: 'fly_mary'
    })
    return connection;
  }
}