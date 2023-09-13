class PerformanceService {

    constructor(performanceData) {
        this.performanceData = performanceData;
    }

    async insert(performance) {

        try {
            let result = await this.performanceData.insert(performance);
            result = {
                id: result._id,
                showName: performance.showName,
                showDate: performance.showDate,
                showPrice: performance.showPrice,
                showLocation: performance.showLocation,
                thumbnailIcon: performance.thumbnailIcon,
            }
            return {data: result};
        } catch (err) {
            return {error: "Internal Server Error"};
        }
    }

    async getById(performanceId){
        try{
            let result = await this.performanceData.getById(performanceId);
            result = {
                id: result._id,
                showName: result.showName,
                showDate: result.showDate,
                showPrice: result.showPrice,
                showLocation: result.showLocation,
                thumbnailIcon: result.thumbnailIcon,
            }
            return {data: result};
        }catch (err) {
            return {error: "Internal Server Error"};
        }
    }

    async getByQuery(req){
        const query= req.query;
        try{
            let queryResult= await this.performanceData.getByQuery(query)
            return{data: queryResult};
        }catch(err){
            return {error: "Internal Server Error"};
        }

    }

    async existsById(performanceId){
        try{
            const result = await this.performanceData.getById(performanceId);
            if(result){
                return true;
            }
            return false;
        }catch(err){
            return {error:err};
        }
    }
}
module.exports = PerformanceService;