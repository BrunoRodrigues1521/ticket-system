'use strict';
const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const userSchema = new Schema({
    username: {
        type: String,
        required: 'A username is needed'
    },
    email: {
        type: String,
        required: 'An email is needed'
    },
    password: {
        type: String
    },
    nif: {
        type: Number,
        required: 'A nif is needed'
    },
    picture:{
        type: String
    },
    pKey:{
        type: String
    },
    Created_date: {
        type: Date,
        default: Date.now
    }
}, {collection: 'userCollection'}   );
module.exports = mongoose.model('User', userSchema);