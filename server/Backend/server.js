//server.js
const express = require('express');
const bodyParser = require('body-parser');
const customer = require('./routes/customer.route'); // Imports routes for the customers

const app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));
app.use(function(req, res, next){
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
})
app.use('/customers', customer);


console.log('process.env.PORT: '+8080);

// ACCESSED BY "http://34.201.141.150:8080/customers/test"
app.listen(8080);
