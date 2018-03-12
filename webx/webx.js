//ES6 arrow function
//dependencies
const express = require('express');
const bodyParser = require('body-parser');

const app = express();

app.use(bodyParser.json()); // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({extended: true}));

app.listen(process.env.PORT || 6015, () => {
    console.log('listening on 6015')
});

app.get('/', (req, res) => {
   console.log('loading page');
   res.json('done');    
});