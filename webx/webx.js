//ES6 arrow function
//dependencies
//https://stackoverflow.com/questions/5178334/folder-structure-for-a-node-js-project
const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const mysql = require('mysql');
const port = 6015;

const connection = mysql.createConnection({
    host: '192.168.0.16',
    user: 'dbboy',
    password: 'Welcome123+',
    database: 'rockola'
});

connection.connect();

app.set('view engine', 'ejs');
app.use(express.static('public'));
app.use(bodyParser.json()); // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({extended: true}));

app.listen(process.env.PORT || port, () => {
    console.log('listening on ' + port)
});

app.get('/', (req, res) => {
    console.log('loading page');
    res.render('index.ejs')
});

app.get('/tracks', (req, res) => {
    console.log('loading page');
    connection.query("select title, hash, length, album, year, trackNo, bitrate, size from Tracks", function (err, result, fields) {
        if (err) throw err;
        console.log("serving list of tracks");
        res.json(result);
    });
});

