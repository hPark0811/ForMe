const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let CustomerSchema = new Schema({
    customerID: {type: String, required: true, max: 100},
    creditCard: {type: String, required: true},
    bankAccount:  {type: String, required: true}
});


// Export the model
module.exports = mongoose.model('Customer', CustomerSchema);