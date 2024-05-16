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
            const jsonData = JSON.parse(data);
            const room  = jsonData.room;
            const receiverId  = jsonData.receiver;
            const message_send  = jsonData.message_send;
            const receiver_name  = jsonData.receiver_name;
            const senderId  = jsonData.senderId;

            console.log("senderId   :  ",senderId)

        
            
            io.to(room).emit('123', { message: 'loading server' });
            io.to(receiverId).emit('new_notification',  { message_send: message_send+""}
             , { receiver_name: receiver_name+""}
              , { senderId: senderId+""});
        });
        

        socket.on('disconnect', () => {
            printColoredConsole('red', 'A client disconnected ');
        });
    });

    return io;
}

module.exports = socketMiddleware;
