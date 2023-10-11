const myModule = require('./mysql');

let connection = myModule.getConnection();

module.exports = {
  getAllPurchase: function() {
    app.get('/api/purchases', (req, res) => {
        connection.query('SELECT * FROM cities', (err, rows, fields) => {
            if (err) throw err
          
            console.log('rows: ', rows)
            res.json(rows)
        })
    })
    return connection;
  }
}