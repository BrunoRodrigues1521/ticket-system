'use strict';
const ticketMiddleware = require('../middleware/ticketMiddleware');
const validationMiddleware = require('../middleware/validationMiddleware');

module.exports = function (app) {

    const ticketController = require("../controllers/ticketController");
    
    app.route('/ticket')
        .post(ticketMiddleware.isValidTicket,
            ticketController.insert)
    app.route('/ticket/getAllUserId/:userId')
        .get(
            ticketMiddleware.userExists,
            ticketController.getAllByUserId)
    app.route('/ticket/getAll')
        .get(
            ticketController.getAll)
    app.route('/ticket/useTicket/:_id')
        .put(
            ticketMiddleware.ticketExistsAndIsValid,
            ticketController.updateValidity)
    app.route('/ticket/receipts/:_id')
        .get(ticketController.getReceipts)

};