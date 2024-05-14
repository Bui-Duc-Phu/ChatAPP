const express = require('express')
const bodyParser = require("body-parser");
const router_signup = express.Router()

router_signup.use(bodyParser.json());
router_signup.use(bodyParser.urlencoded({ extended: true }));

//controller
const {
    PostData_SignUp

} = require('../controllers/signUp')

//Middelware
const {
    checkExistingUser,
    checkNullFields,
    checkConfirmPassword
} = require('../middelwares/signUpMiddelware')

//route
router_signup.post('/sign-up',checkNullFields,checkConfirmPassword,checkExistingUser,PostData_SignUp)

module.exports = { router_signup }