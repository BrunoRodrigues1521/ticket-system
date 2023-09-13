const PerformanceService = require("../services/performanceService");
const performanceData = require('../data/performanceData');
const PerformanceServiceInstance = new PerformanceService(performanceData);

class TicketService {

    constructor(ticketData) {
        this.ticketData = ticketData;
    }

    async insert(ticket) {
        try {
            const seatIsTaken = await this.ticketData.getSeatByPerformance(ticket.seatNumber,ticket.performanceId);
            if(seatIsTaken){
                return {error: "Seat is already taken"};
            }

            let performance = await PerformanceServiceInstance.getById(ticket.performanceId)
            ticket.showName= performance.data.showName;
            ticket.showDate = performance.data.showDate;
            ticket.thumbnailIcon=performance.thumbnailIcon;

            let result = await this.ticketData.insert(ticket);
            result = {
                id: result._id,
                userId:result.userId,
                performanceId: result.performanceId,
                showName: result.showName,
                showDate: result.showDate,
                seatNumber: result.seatNumber,
                thumbnailIcon: result.thumbnailIcon,
                valid: result.valid,
            }
            return {data: result};
        } catch (err) {
            return {error: "Internal Server Error"};
        }
    }
    async getAllByUserId(userId){
        try{
            let result = await this.ticketData.getAllByUserId(userId);
            return {data: result};
        } catch (err) {
            return {error: "Internal Server Error"};
        }
    }

    async getAll(){
        try{
            let result = await this.ticketData.getAll();
            return {data: result};
        } catch (err) {
            return {error: "Internal Server Error"};
        }
    }

    async getTicketById(ticketId){
        try{
            let result = await this.ticketData.getTicketById(ticketId);
            return {data: result};
        } catch (err) {
            return {error: "Internal Server Error"};
        }
    }

    async updateValidity(ticketId){
        try{
            let result = await this.ticketData.updateValidity(ticketId);
            return {data: result};
        } catch (err) {
            return {error: "Internal Server Error"};
        }
    }

    async getReceipts(userId){
        try{
            let result = await this.ticketData.getReceipts(userId);
            if(result.length === 0){
                return {data: []};
            }
            let response = [];
            for (const element of result) {
                let ticket = element;
                let receipt = {
                    name: ticket.userId.username,
                    nif: ticket.userId.nif,
                    showName: ticket.performanceId.showName,
                    showDate: ticket.performanceId.showDate,
                    showPrice: ticket.performanceId.showPrice,
                }
                response.push(receipt);
            }
            return {data: response};
        } catch (err) {
            console.log(err)
            return {error: "Internal Server Error"};
        }
    }
}
module.exports = TicketService;