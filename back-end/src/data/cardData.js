const mongoose = require('mongoose')
const cardModel = mongoose.model('CreditCard')

exports.insert = async function (card) {
        const newCard = new cardModel();
        newCard.userId= card.userId;
        newCard.type = card.type;
        newCard.cardNumber= card.cardNumber;
        newCard.expirationDate= card.expirationDate;
        return newCard.save();
}

exports.get = async function (userId) {
    return cardModel.findOne({
        userId: userId
    });
}