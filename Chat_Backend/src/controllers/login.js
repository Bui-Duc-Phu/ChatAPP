const {pool}  = require('../configs/connectionSQL')


const getLogin = (req, res) => {
    const { username, password } = req.body;

    pool.query('SELECT * FROM user WHERE username = ? AND password = ?', [username, password], (error, results) => {
        if (error) {
            console.error('Error during login:', error);
            res.status(500).json({ success: false, message: 'Internal server error' });
        } else {
            if (results.length > 0) {
                console.log('User logged in successfully');
                res.status(200).json({ success: true, message: 'User logged in successfully'});
            } else {
                console.log('Invalid username or password');
                res.status(401).json({ success: false, message: 'Invalid username or password' });
            }
        }
    });
};

const getUserByName = (req, res) => {
    const { name } = req.params; // Lấy tên người dùng từ tham số URL

    pool.query('SELECT * FROM user WHERE username = ?', [name], (error, results) => {
        if (error) {
            console.error('Error during retrieving user:', error);
            res.status(500).json({ success: false, message: 'Internal server error' });
        } else {
            if (results.length > 0) {
                console.log('User found successfully');
                console.log(results);
                const responseData = JSON.stringify(results[0]); // Chuyển đổi kết quả thành chuỗi JSON
                res.send(responseData); 
                // res.status(200).json({ success: true, message: 'User found successfully' });
            } else {
                console.log('User not found');
                res.status(404).json({ success: false, message: 'User not found' });
            }
        }
    });
};


module.exports = {getLogin
    ,getUserByName

}