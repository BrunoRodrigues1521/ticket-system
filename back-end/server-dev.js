require('./src/config/mongo-config');
require('./src/models/userModel');
require('./src/models/ticketModel');
require('./src/models/performanceModel');
require('./src/models/creditCardModel');
require("swagger-jsdoc");

const YAML = require('yamljs')
const swaggerUI = require("swagger-ui-express");
const swaggerDocument= YAML.load("docs/openapi/openapi.yaml")

const express = require('express')
const cors = require('cors')
const PORT = process.env.PORT || 5000
const helmet = require('helmet');
const app = express();

const userRoutes = require('./src/routes/userRoutes');
const ticketRoutes = require('./src/routes/ticketRoutes');
const performanceRoutes = require('./src/routes/performanceRoutes');

app.use(helmet());
app.use(express.json({limit:'5mb'}));
app.use(express.urlencoded({limit:'5mb', extended: false }));
app.use(cors());
app.use(function(req, res, next) {
      res.header('Access-Control-Allow-Origin', '*');
      res.header('Access-Control-Allow-Headers', 'Content-Type');
      res.header('Access-Control-Allow-Methods', 'GET, POST, PUT ,DELETE');
      next();
});
app.use("/docs/openapi",swaggerUI.serve, swaggerUI.setup(swaggerDocument))
userRoutes(app);
ticketRoutes(app);
performanceRoutes(app);

app.use(function(req, res) {
      res.status(404).send({sucess:0,error:"Not Found"});
});
app.use(function(req, res) {
      res.status(403).send({sucess:0,error:"Forbidden"});
});
app.use(function(req, res) {
      res.status(500).send({sucess:0,error:"Internal Server Error"});
});
  
app.listen(PORT, () =>
    console.log(`Listening on http://localhost:${PORT}`));