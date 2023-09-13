'use strict';
const mongoose = require('mongoose');
const {Decimal128} = require("mongodb");
const Schema = mongoose.Schema;

const performanceSchema = new Schema({
    showName: {
        type: String,
        required: 'A show name is needed'
    },
    showDate: {
        type: String,
        required: 'A show date is needed (yyyy-MM-dd)'
    },
    showPrice:{
        type: String,
        required: 'A price for the show is needed'
    },
    showLocation:{
        type:String,
        required: 'A location for the show is necessary '
    },
    thumbnailIcon:{
        type: String
    },
    Created_date: {
        type: Date,
        default: Date.now
    }
}, {collection: 'performanceCollection'}   );
module.exports = mongoose.model('Performance', performanceSchema);