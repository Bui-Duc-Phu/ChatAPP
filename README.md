
 <h1>Frontend: Android App using RESTful API (Retrofit)</h1>
 
 <h1>Backend: Nodejs</h1>

 <h2>Demo App</h2>

 https://github.com/Bui-Duc-Phu/ChatAPP/assets/161537384/fb4c5b22-fdb2-4564-b377-3f22170e8136


 <h2>Server.js</h2>
<pre><code>
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
 </code></pre>






 <h2>Login Route</h2>
</code><pre>
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
</code></pre>



 <h2>SignUp Route</h2>
</code><pre>
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
</code></pre>




 <h2>Message Route</h2>
</code><pre>
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

</code></pre>



 <h2>User Route</h2>
</code><pre>
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
</code></pre>





 <h2>soket.io Realtime (xử lý loading recylerview message cho client khi có sự kiện send message)</h2>
</code><pre>
const socketIo = require('socket.io');
const printColoredConsole = require('../Ultils/coloredConsole');
function socketMiddleware(server) {
    const io = socketIo(server);
    io.on('connection', (socket) => {
        printColoredConsole('blue', '`Client connect soketIO SERVER ');
        socket.on('joinRoom', (room) => {
            socket.join(room); 
            printColoredConsole('blue', 'Client joined room ' +  room);
        });
        socket.on('key', (data) => {
            io.to(data).emit('123', { message: 'loading server' });
        });
        socket.on('disconnect', () => {
            printColoredConsole('red', 'A client disconnected ');
        });
    });
    return io;
}
module.exports = socketMiddleware;
</code></pre>


 <h2>ApiService Client</h2>
 </code><pre>
interface ApiService {
    @GET("/get-user/{name}")
    fun getUser(@Path("name") name: String): Call<User_res>
    
    @GET("/get-receiver/{id}")
    fun getAllReceiver(@Path("id") id: String): Call<List<User_res>>

    @GET("/get-message/{receiver_id}/{sender_id}")
    fun getMessage(@Path("receiver_id") receiver_id: String,
                   @Path("sender_id") sender_id: String): Call<List<Chat>>
    
    @POST("/sign-up")
    fun SignUp(@Body user: User): Call<Void>

    @POST("/login")
    fun login(@Body user: User): Call<Void>

    @POST("/post-message")
    fun postMessage(@Body mess: Chat): Call<Void>
    
    @DELETE("/v1/deletestudent/{id}")
    fun deleteStudent(@Path("id") id: Int): Call<Void>

    @PUT("/v1/putname")
    fun putStudent(@Body student: Chat): Call<Void>

    @PATCH("/v1/updateAge")
    fun patchStudent(@Body student: Chat): Call<Void>
}
</code></pre>

