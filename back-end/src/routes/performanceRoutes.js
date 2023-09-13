'use strict';
const performanceMiddleware = require('../middleware/performanceMiddleware');

module.exports = function (app) {
    const performanceController = require("../controllers/performanceController");

    app.route('/performance')
        .post(performanceMiddleware.isValidPerformance,
            performanceController.insert)
    app.route('/performance/filter')
        .get(performanceMiddleware.isValidQuery,
            performanceController.getByQuery)
}

