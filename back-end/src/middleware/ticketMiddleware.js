const userData = require('../data/userData');
const UserService = require("../services/userService");
const UserServiceInstance = new UserService(userData);
const PerformanceService = require("../services/performanceService");
const performanceData = require('../data/performanceData');
const PerformanceServiceInstance = new PerformanceService(performanceData);
const TicketService = require("../services/ticketService");
const ticketData = require('../data/ticketData');
const TicketServiceInstance = new TicketService(ticketData);
const {isValid} = require('../helpers/helpers');


exports.isValidTicket= async function(req, res, next){

    for(const element of req.body.tickets){
        const {userId, performanceId,seatNumber} = element;
        const existsUser= await UserServiceInstance.existsById(userId)
        const existsPerformance= await PerformanceServiceInstance.existsById(performanceId)
        try{
            if(existsUser.error){
                return res.status(500).send({success:0, error: "User Id does not exist!"});
            }
            if(existsPerformance.error){
                return res.status(500).send({success:0, error: "Performance Id does not exist!"});
            }
            if(!isValid(seatNumber,"string")){
                return res.status(400).send({success:0,error:"Insert a valid seat number"});
            }
    
        }catch(err) {
            return res.status(500).send({success: 0, error: "Internal Server Error(Format Validation)"})
        }
    }
    return next();
}

exports.userExists = async function(req, res, next){

    const existsUser= await UserServiceInstance.existsById(req.params.userId)
    try{
        if(existsUser.error){
            return res.status(500).send({success:0, error: "User Id does not exist!"});
        }
        return next()
    }catch (err){
        return res.status(500).send({success: 0, error: "Internal Server Error(User Exists Validation)"})
    }

}
exports.ticketExistsAndIsValid = async function(req, res, next){

    const ticket= await TicketServiceInstance.getTicketById(req.params._id);
    try{
        if(!ticket.data.valid){
            return res.status(500).send({success:0, error: "Ticket Id does not exist or is not valid(has been used)!"});
        }
        return next()
    }catch (err){
        return res.status(500).send({success: 0, error: "Internal Server Error(User Exists Validation)"})
    }

}