'use strict';
const PerformanceService = require("../services/performanceService");
const performanceData = require('../data/performanceData');
const PerformanceServiceInstance = new PerformanceService(performanceData);

exports.insert = async function (req, res) {
    try{
        const result = await PerformanceServiceInstance.insert(req.body);
        if(result.error){
            return res.status(500).json({success:0,error:result.error});
        }
        const response = {success:1,result: result};
        res.status(200).json(response);
    }catch(err){
        return res.status(500).send({success:0,error:"Internal Server Error"});
    }

};

exports.getByQuery= async function (req,res){
    try{
        const result= await PerformanceServiceInstance.getByQuery(req);
        if(result.error){
            return res.status(500).json({success:0,error:result.error});
        }
        res.status(200).json({success:1, routes: result.data});
    }catch(err){
        console.log(err);
        return res.status(500).send({success:0,error:"Internal Server Error"});
    }
}