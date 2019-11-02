// const Item = require('../models/item.model');

//Simple version, without validation or sanitation
exports.test = function (req, res) {
    res.send('Greetings from the Test controller!');
};

exports.recommendations = function(req, res){
    console.log('Queried customer ID_recommendation: '+req.params.id);

    const {spawn} = require('child_process');
    const pyProg = spawn('python', ['../Tools/KMEAN/__init__.py', req.params.id]);

    pyProg.stdout.on('data', function(data){
        console.log("PY DATA: \n\n"+data);   //print on console running server.js
        res.send(data);   //output on request origins (POSTMAN)
    })
};

exports.location = function (req, res) {
    console.log('Queried customer ID_location: '+req.params.id);
    // res.send("Received query id of: "+req.params.id)

    const {spawn} = require('child_process');
    const pyProg = spawn('python', ['../Tools/TD_Davinci_API_Closest_Branch.py', req.params.id]);

    pyProg.stdout.on('data', function(data){
        console.log("PY DATA: \n\n"+data.toString());   //print on console running server.js
        res.send(data);   //output on request origins (POSTMAN)
    })
};

// exports.create = function (req, res) {
//     let item = new Item({
//         name: req.body.name,
//         type: req.body.type,
//         due: req.body.due,
//         quantity: req.body.quantity
//     });

//     item.save(function(err) {
//         if(err){
//             return next(err);
//         }
//         res.send("Item Created Successfully");
//     });
// };

// exports.details = function (req, res){
//     console.log('DETAILS: Queried for '+req.params.id);
//     Item.findById(req.params.id, function (err, item){
//         if(err) return next(err);
//         res.send(item);
//     });
// };

// exports.getAll = function (req, res){
//     console.log('GETALL: Queried for '+req.params.id);

//     Item.find({}, 'name type due quantity', function(err, item){
//         if(err){
//             console.log(err);
//         }else{
//             res.send(item);
//             console.log('RETRIEVED CURRENT LIST OF '+item.length+" items");
//         }
//     });
// };


// exports.update = function (req, res){
//     Item.findByIdAndUpdate(req.params.id, {$set: req.body},
//         function(err, item){
//             if(err) return next(err);
//             res.send("Item updated.");
//         });
// };

// exports.delete = function (req, res){
//     Item.findByIdAndRemove(req.params.id, function (err){
//         if(err) return next(err);
//         res.send("Deleted Successfully!");
//     });
// };