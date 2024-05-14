const express = require('express')
const bodyParser = require("body-parser");
const router_user = express.Router()

router_user.use(bodyParser.json());
router_user.use(bodyParser.urlencoded({ extended: true }));

//controller
const {
    getAllReceiver

} = require('../controllers/user')

//Middelware
const {
    checkNullFields

} = require('../middelwares/userMiddelware')

//route
router_user.get('/get-receiver/:id',checkNullFields,getAllReceiver)

module.exports = { router_user }