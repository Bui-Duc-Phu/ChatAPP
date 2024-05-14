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
