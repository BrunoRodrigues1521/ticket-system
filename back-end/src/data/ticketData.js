const mongoose = require('mongoose')
const ticketModel = mongoose.model('Ticket')

exports.insert = async function (ticket) {

    const newTicket = new ticketModel();
    newTicket.userId= ticket.userId;
    newTicket.performanceId= ticket.performanceId;
    newTicket.showName= ticket.showName;
    newTicket.showDate= ticket.showDate;
    newTicket.seatNumber= ticket.seatNumber;
    newTicket.thumbnailIcon= ticket.thumbnailIcon;
    newTicket.valid= true;
    return newTicket.save();
};

exports.getAllByUserId= async function(userId) {
    return ticketModel.find({
        userId: userId
    });
};

exports.getSeatByPerformance= async function(seatNumber, performanceId) {
    return ticketModel.findOne({
        performanceId: performanceId,
        seatNumber: seatNumber
    });
};

exports.getAllByUserId= async function(userId) {
    return ticketModel.find({
        userId: userId
    });
};

exports.getAll= async function(userId) {
    return ticketModel.find({

    });
};

exports.getTicketById= async function(_id) {
    return ticketModel.findOne({
        _id: _id
    });
};

exports.updateValidity= async function(_id){
    return ticketModel.findOneAndUpdate({
        _id:_id
    },{valid:false},{new:true});
};

exports.getReceipts= async function(userId){
    return ticketModel.find({
        userId: userId,
        valid: false
    })
    .populate('performanceId', 'showName showDate showPrice')
    .populate('userId', 'username nif')
}
