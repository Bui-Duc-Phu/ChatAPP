const express = require('express')
const bodyParser = require("body-parser");
const router_mess = express.Router()

router_mess.use(bodyParser.json());
router_mess.use(bodyParser.urlencoded({ extended: true }));

//controller
const {
    postMessage,
    getMessage

} = require('../controllers/message')

//Middelware
const {
    checkNullFields,
    checkNullgetMessage
} = require('../middelwares/messageMiddelware')



//route
router_mess.post('/post-message',checkNullFields,postMessage)

router_mess.get('/get-message/:receiver_id/:sender_id',checkNullgetMessage,getMessage);

module.exports = { router_mess }