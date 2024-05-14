const express = require("express");
const printColoredConsole = require('./Ultils/coloredConsole');
require("dotenv").config();
const conFigViewEngine = require("./configs/viewEngine");
const socketIO_Server = require('./soket.io/indexIO')
const port =  process.env.PORT || 3000;
const hostname = process.env.HOST_NAME  || 'localhost'
// route
const {router_signup} = require("./routes/signUpRoute");
const {router_login} = require("./routes/loginRoute");
const {router_mess} = require("./routes/messageRoute");
const {router_user} = require("./routes/userRoute");
// create app
const app = express();
// View Engine
conFigViewEngine(app);
// io serrver
socketIO_Server()

// use Routes
app.use("/", router_signup);
app.use("/", router_login);
app.use("/", router_mess);
app.use("/", router_user);


app.listen(port, hostname, () => {

  printColoredConsole('violet', 'Server Running --->  listening on port ' +  port);
 
});
