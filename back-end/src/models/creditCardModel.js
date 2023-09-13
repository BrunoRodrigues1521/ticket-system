'use strict';
const mongoose = require('mongoose');
const Schema = mongoose.Schema;
const UserModel = require('./userModel');

const creditCardSchema = new Schema({
    type: {
        type: String,
        required: 'A card type is needed'
    },
    cardNumber: {
        type: Number,
        required: 'A card number is needed'
    },
    expirationDate: {
        type: Number,
        required: 'A expiration date is needed'
    },
    userId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: UserModel,
        required: 'A user id is needed'
    }
}, {collection: 'creditCardCollection'});
module.exports = mongoose.model('CreditCard', creditCardSchema);