'use strict';
const mongoose = require('mongoose');
const Schema = mongoose.Schema;
const UserModel = require('./userModel');
const PerformanceModel = require('./performanceModel');

const ticketSchema = new Schema({
    userId:{
       type: mongoose.Schema.Types.ObjectId,
       ref: UserModel,
       required: 'The ticket has to be assigned to a User (UserId is needed)'
    },
    performanceId:{
        type: mongoose.Schema.Types.ObjectId,
        ref: PerformanceModel,
        required: 'The ticket has to be assigned to a Performance (PerformanceId is needed)'
    },
    showName: {
        type: String,
        required: 'A show name is needed'
    },
    showDate: {
        type: String,
        required: 'A show date is needed (yyyy-MM-dd)'
    },
    seatNumber:{
        type: String,
        required:'A seat Number is necessary'
    },
    thumbnailIcon:{
        type: String
    },
    valid:{
        type: Boolean,
        required:'A valid boolean is necessary to represent if the ticket was used or not'
    },
    Created_date: {
        type: Date,
        default: Date.now
    }
}, {collection: 'ticketCollection'}   );
module.exports = mongoose.model('Ticket', ticketSchema);