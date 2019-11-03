//server.js
const fs = require('fs');
const express = require('express');
const bodyParser = require('body-parser');
const customer = require('./routes/customer.route');
const app = express();

const mongoose = require('mongoose');

let dev_db_url = 'mongodb://localhost:27017/preset_model';
let mongoDB = process.env.MONGODB_URI || dev_db_url;

mongoose.connect(mongoDB, {useNewUrlParser: true, useUnifiedTopology: true });
mongoose.Promise = global.Promise;

let db = mongoose.connection;
db.once('open', _ =>{
    console.log('Database connected: ',dev_db_url);
});
db.on('error', err => {
    console.error.bind(console, 'MongoDB connection error:');
});

var mappedList = JSON.parse(fs.readFileSync("../Tools/KMEAN/demo.JSON"));

console.log(mappedList);
console.log(Object.keys(mappedList).length);

for(var i=0; i<Object.keys(mappedList).length; i++){
    var tempKey = Object.keys(mappedList)[i];
    var tempValue = Object.values(mappedList)[i];
    if(!db.collection('customers').find({customerID: tempKey})){
        db.collection('customers').insertOne({customerID: tempKey, creditCard: tempValue[0], bankAccount: tempValue[1]})
    }else{
        console.log('already exists...');
    }
}

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));
app.use(function(req, res, next){
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
})
app.use('/customers', customer);

// ACCESSED BY "http://34.201.141.150:8080/customers/test"
app.listen(8080);
