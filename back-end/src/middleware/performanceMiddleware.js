const {isValid} = require('../helpers/helpers');

exports.isValidPerformance= async function(req, res, next){
    const {showName, showDate, showPrice, showLocation} = req.body;
    try{

        if(!isValid(showDate,"date")){
            return res.status(400).send({success:0,error:"Insert a valid show date"});
        }
        if(!isValid(showName,"string")){
            console.log(showName)
            return res.status(400).send({success:0,error:"Insert a valid show name"});
        }
        if(!isValid(showPrice,"string")){
            return res.status(400).send({success:0,error:"Insert a valid show price"});
        }
        if(!isValid(showLocation,"string")){
            return res.status(400).send({success:0,error:"Insert a valid show name"});
        }

        return next();

    }catch(err) {
        return res.status(500).send({success: 0, error: "Internal Server Error(Format Validation)"})
    }
}

exports.isValidQuery = function(req,res,next){
    const query = Object.keys(req.query);
    const whiteList = ["showName","showDate","showPrice", "showLocation"]

    for(var i =0;i<query.length;i++){
        if(!whiteList.includes(query[i])){
            return res.status(400).send({success:0,error:"Unknown query params"});
        }
    }
    return next();
}