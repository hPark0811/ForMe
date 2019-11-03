const Customer = require('../models/customer.model');

exports.test = function (req, res) {
    res.send('Greetings from the Test controller!');
};

exports.recommendations = function(req, res){
    console.log('Queried customer ID_recommendation: '+req.params.id);
    
    const mongoose = require('mongoose');

    let dev_db_url = 'mongodb://localhost:27017/preset_model';
    let mongoDB = process.env.MONGODB_URI || dev_db_url;

    mongoose.connect(mongoDB, {useNewUrlParser: true, useUnifiedTopology: true });
    mongoose.Promise = global.Promise;

    let db = mongoose.connection;
    db.once('open', _ =>{
        console.log('Database connected in CONTROLLER: ',dev_db_url);
    });
    db.on('error', err => {
        console.error.bind(console, 'MongoDB connection error in CONTROLLER:');
    });

    db.collection('customers').find({customerID: req.params.id}, {creditCard: 1, bankAccount: 1}).toArray(function(err, result){
        if(result.length>0){
            console.log(result);
            console.log(result[0].creditCard+", "+result[0].bankAccount);
            res.send(result[0]);
        }
    });
    // console.log(temp);

};

exports.location = function (req, res) {
    console.log('Queried customer ID_location: '+req.params.id);

    const {spawn} = require('child_process');
    const pyProg = spawn('python', ['../Tools/TD_Davinci_API_Closest_Branch.py', req.params.id]);

    pyProg.stdout.on('data', function(data){
        console.log("PY DATA: \n\n"+data.toString());    //print on console running server.js
        res.send(data);                                  //output on request origins (POSTMAN)
    })
};


exports.create = function (req, res) {
    let customer = new Customer({
        customerID: req.body.customerID,
        creditCard: req.body.creditCard,
        bankAccount: req.body.bankAccount
    });

    customer.save(function(err) {
        if(err){
            return next(err);
        }
        res.send("Customer Created Successfully");
    });
};
