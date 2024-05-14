const {pool}  = require('../configs/connectionSQL')


const PostData_SignUp = (req, res) => {
    const {id, username, password } = req.body;

    pool.query('INSERT INTO user (uid, username, password) VALUES (?, ?,?)', [id, username, password], (error, results) => {
        if (error) {
            console.error('Error during signup:', error);
            res.status(500).json({ success: false, message: 'Internal server error' });
        } else {
            console.log('User signed up successfully');
            res.status(200).json({ success: true, message: 'User signed up successfully' });
        }
    });
};



module.exports  = {PostData_SignUp}