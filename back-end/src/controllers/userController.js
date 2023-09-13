'use strict';
const UserService = require("../services/userService");
const TokenService = require('../services/tokenService');
const CreditCardService = require('../services/creditCardService');
const userData = require('../data/userData');
const cardData = require('../data/cardData');
const UserServiceInstance = new UserService(userData);
const TokenServiceInstance = new TokenService();
const CreditCardServiceInstance = new CreditCardService(cardData);

exports.insert = async function (req, res) {
    try{
        const result = await UserServiceInstance.insert(req.body);
        if(result.error){
            return res.status(500).json({success:0,error:result.error});
        }
        const insertCard = await CreditCardServiceInstance.insert(result.data.id,req.body.card);
        if(insertCard.error){ 
            return res.status(500).json({success:0,error:result.error});
        }
        const token = TokenServiceInstance.createJwtToken(result.data);
        const response = {success:1,token:token};
        res.status(200).json(response);
    }catch(err){
        return res.status(500).send({success:0,error:"Internal Server Error"});
    }
   
};
exports.get = function (req, res) {
    try{
        const token = TokenServiceInstance.createJwtToken(res.locals.data);
        const response = {success:1,token:token};
        res.status(200).json(response);
    }catch(err){
        return res.status(500).send({success:0,error:"Internal Server Error"});
    }
};

exports.getByEmail = async function (req, res) {
    try{
        const result = await UserServiceInstance.getByEmail(req.params.email)
        const response = {success:1,user:result.data};
        res.status(200).json(response);
    }catch(err){
        return res.status(500).send({success:0,error:"Internal Server Error"});
    }
};

exports.authWithGoogle = async function (req, res) {
        const exists = await UserServiceInstance.exists(res.locals.data.email);
        if(!exists){
            const result = await UserServiceInstance.authWithGoogle(res.locals.data);
            if(result.error){
                return res.status(401).json({success:0,error:result.error});
            }
        }
        const token = TokenServiceInstance.createJwtToken(res.locals.data);
        const response = {success:1,token:token};
        res.status(200).json(response);
};

exports.update = async function (req, res) {
    try{
        const result = await UserServiceInstance.update(req);
        if(result.error){
            return res.status(500).json({success:0,error:result.error});
        }
        res.status(200).json({success:1,user:result.data});
    }catch(err){
        return res.status(500).send({success:0,error:"Internal Server Error"});
    }
};

exports.resetPasswd = async function (req, res) {
    try{
        const result = await UserServiceInstance.resetPasswd(req.body);
        if(result.error){
            return res.status(403).json({success:0,error:result.error});
        }
        res.status(200).json(result);
    }catch(err){
        return res.status(500).send({success:0,error:"Internal Server Error"});
    }
};

exports.addFavourites= async function (req, res){
    try{
        const result= await UserServiceInstance.addFavourites(req.body);
        if(result.error){
            return res.status(500).json({success:0,error:result.error});;
        }
        res.status(200).json({success:1});
    }catch(err){
        console.log(err)
        return res.status(500).send({success:0,error:"Internal Server Error"});
    }
};

exports.getFavourites= async function (req, res){
    try{
        const result= await UserServiceInstance.getFavourites(req.params);
        if(result.error){
            return res.status(500).json({success:0,error:result.error});
        }
        res.status(200).json({success:1,routes:result.data});
    }catch(err){
        console.log(err)
        return res.status(500).send({success:0,error:"Internal Server Error"});
    }
};
