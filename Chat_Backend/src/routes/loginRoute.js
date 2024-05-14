const express = require('express')
const bodyParser = require("body-parser");
const router_login = express.Router()

router_login.use(bodyParser.json());
router_login.use(bodyParser.urlencoded({ extended: true }));

//controller
const {
    getLogin,
    getUserByName
} = require('../controllers/login')

//Middelware
const {
    checkNullFields
} = require('../middelwares/loginMiddelware')

router_login.post('/login',checkNullFields,getLogin)
router_login.get('/get-user/:name',getUserByName)
module.exports = { router_login }