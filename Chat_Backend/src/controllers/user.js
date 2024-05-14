const { pool } = require('../configs/connectionSQL');

const getAllReceiver = (req, res) => {
    const { id } = req.params;

    pool.query('SELECT * FROM user WHERE uid != ?', [id], (error, results) => {
        if (error) {
            console.error('Error retrieving receivers:', error);
            console.log("err1111 : " + error)
            res.status(500).json({ success: false, message: 'Internal server error' });
        } else {
            const responseData = JSON.stringify(results); 
            res.send(responseData); 
        }
    });
};

module.exports = {getAllReceiver };
