const mongoose = require('mongoose')
const performanceModel = mongoose.model('Performance')

exports.insert = async function (performance) {

    const newPerformance = new performanceModel();
    newPerformance.showName= performance.showName;
    newPerformance.showDate= performance.showDate;
    newPerformance.showPrice= performance.showPrice;
    newPerformance.showLocation= performance.showLocation;
    newPerformance.thumbnailIcon= performance.thumbnailIcon;
    return newPerformance.save();
};

exports.getById = async function(performanceId){
    return performanceModel.findOne({
        _id: performanceId
    });
};

exports.getByQuery = async function (query) {
    return performanceModel.find({
        $and:[query]
    });
};
