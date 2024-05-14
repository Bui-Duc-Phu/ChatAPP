const mysql2 = require('mysql2');
const printColoredConsole = require('../Ultils/coloredConsole');

const pool = mysql2.createPool({
    host: process.env.HOST_NAME_SQL  || 'localhost',
    port: process.env.PORT_SQL || 3360 ,
    user: process.env.USER || 'root',
    password: process.env.PASSWORD || 'phuhk123',
    database: process.env.DATABASE || 'chatdb',
    connectionLimit: 20,
    waitForConnections: true,
    queueLimit: 0
});

pool.on('acquire', function (connection) {
    printColoredConsole('green', 'Connection '+connection.threadId+' acquired' );
});

pool.on('connection', function (connection) {
    connection.query('SET SESSION auto_increment_increment=1');
});

pool.on('enqueue', function () {
    console.log('Waiting for available connection slot');
});


module.exports ={
    pool,
    
}
