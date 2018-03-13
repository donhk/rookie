//ES6 arrow function
//dependencies
//https://stackoverflow.com/questions/5178334/folder-structure-for-a-node-js-project
const express = require('express');
const app = express();
const bodyParser = require('body-parser');

app.set('view engine', 'ejs');
app.use(express.static('public'));
app.use(bodyParser.json()); // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({extended: true}));

app.listen(process.env.PORT || 6015, () => {
    console.log('listening on 6015')
});

app.get('/', (req, res) => {
    console.log('loading page');
    res.render('index.ejs')
});