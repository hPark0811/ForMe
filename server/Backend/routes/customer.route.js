const express = require('express');
const router = express.Router();
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
        .get('/:id/recommendations', controller.recommendations)
        .post('/create', controller.create);

module.exports = router;