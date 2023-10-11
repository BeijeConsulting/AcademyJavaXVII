const express = require('express')
const app = express()
const port = 3000
const path = require('path')
const bodyParser = require('body-parser')

const mysql = require('mysql')
const connection = mysql.createConnection({
  host: 'bookstore.cvzu4xrxvkdz.eu-south-1.rds.amazonaws.com',
  user: 'admin',
  password: 'tltBJwVWgoY62y08brB1',
  database: 'fly_mary'
})
//connection.connect()

app.use(express.static('Views'))
//app.use('/', express.static(path.join(__dirname, 'public')))

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());


    
app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})

/* 
app.get('/api/contacts', (req, res) => {
    connection.query('SELECT * FROM rubrica', (err, rows, fields) => {
        if (err) throw err
      
        console.log('rows: ', rows)
        res.json(rows)
    })
})

app.post('/api/contact', (req, res) => {
    //console.log(req)

    let c = req.body;
    console.log(c.nome)

    res.json(c)
})

app.get('/', (req, res) => {
    res.send('Hello Suor Mary!!!')
  })


app.get('/home', (req, res) => {
    //res.status(205)
    res.send('<h1>sono la home page</h1>')
})

app.get('/api/hello', (req, res) => {
    let obj = {
        message: "Hello Suor Mary!"
    }

    res.json(obj);
})

*/