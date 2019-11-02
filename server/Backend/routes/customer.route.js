const express = require('express');
const router = express.Router();

// Require the controllers WHICH WE DID NOT CREATE YET!!
const controller = require('../controllers/customer.controller');

router.use(function(req, res, next){
    console.log("LOG");
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    res.header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
    next();
});

router.get('/test', controller.test)
        .get('/:id/location', controller.location)
        .get('/:id/recommendations', controller.recommendations);
    // .get('/getAll', controller.getAll)
    // .post('/create', controller.create)
    // .get('/:id', controller.details)
    // .put('/:id/update', controller.update)
    // .delete('/:id/delete', controller.delete);


module.exports = router;