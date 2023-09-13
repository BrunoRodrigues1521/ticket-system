'use strict';
const TicketService = require("../services/ticketService");
const CreditCardService = require('../services/creditCardService');
const PerformanceService = require("../services/performanceService");
const ticketData = require('../data/ticketData');
const cardData = require('../data/cardData');
const performanceData = require('../data/performanceData');
const TicketServiceInstance = new TicketService(ticketData);
const CreditCardServiceInstance = new CreditCardService(cardData);
const PerformanceServiceInstance = new PerformanceService(performanceData);

exports.insert = async function (req, res) {
    try{
        const tickets = req.body.tickets;
        const agreggate = [];
        for(const element of tickets){
            const getCreditCardInfo = await CreditCardServiceInstance.getByUserId(element.userId);
            if(getCreditCardInfo.error){
                return res.status(500).json({success:0,error:result.error});
            }
            const isExpired = await CreditCardServiceInstance.isExpired(getCreditCardInfo.data.expirationDate);
            if(!isExpired){
                return res.status(400).json({success:0,error:"Credit Card is expired"});
            }
            const result = await TicketServiceInstance.insert(element);
            if(result.error){
                return res.status(500).json({success:0,error:result.error});
            }
            agreggate.push(result);
        }
        const response = {success:1,result: agreggate};
        res.status(200).json(response);
    }catch(err){
        return res.status(500).send({success:0,error:"Internal Server Error"});
    }

};

exports.getAllByUserId = async function (req, res) {
    try{
        const result = await TicketServiceInstance.getAllByUserId(req.params.userId);
        if(result.error){
            return res.status(500).json({success:0,error:result.error});
        }
        const response = {success:1,result: result};
        res.status(200).json(response);
    }catch(err){
        return res.status(500).send({success:0,error:"Internal Server Error"});
    }

};
exports.getAll = async function (req, res) {
    try{
        const result = await TicketServiceInstance.getAll();
        if(result.error){
            return res.status(500).json({success:0,error:result.error});
        }
        const response = {success:1,result: result};
        res.status(200).json(response);
    }catch(err){
        return res.status(500).send({success:0,error:"Internal Server Error"});
    }

};

exports.updateValidity = async function (req, res) {
    try{
        const result = await TicketServiceInstance.updateValidity(req.params._id);
        if(result.error){
            return res.status(500).json({success:0,error:result.error});
        }
        const response = {success:1,result: result};
        res.status(200).json(response);
    }catch(err){
        return res.status(500).send({success:0,error:"Internal Server Error"});
    }

};

exports.getReceipts = async function (req, res) {
    try{
        const result = await TicketServiceInstance.getReceipts(req.params._id);
        if(result.error){
            return res.status(500).json({success:0,error:result.error});
        }
        const response = {success:1,result: result};
        res.status(200).json(response);
    }catch(err){
        return res.status(500).send({success:0,error:"Internal Server Error"});
    }

}
