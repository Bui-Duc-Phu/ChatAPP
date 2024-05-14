const {pool}  = require('../configs/connectionSQL')



const checkExistingUser = (req, res, next) => {
    const { id, username } = req.body;

    // Kiểm tra xem id hoặc username đã tồn tại trong cơ sở dữ liệu hay chưa
    pool.query('SELECT * FROM user WHERE uid = ? OR username = ?', [id, username], (error, results) => {
        if (error) {
            console.error('Error during user check:', error);
            res.status(500).json({ success: false, message: 'Internal server error' });
        } else if (results.length > 0) {
            // Nếu id hoặc username đã tồn tại, gửi thông báo lỗi
            res.status(400).json({ success: false, message: 'ID or username already exists' });
        } else {
            // Nếu id và username không tồn tại, tiếp tục đến middleware tiếp theo hoặc route
            next();
        }
    });
};



const checkNullFields = (req, res, next) => {
    const { id, username,password } = req.body;
    console.log("username : " + username)
    
    if (id === null || username === null  || password === null || !id || !username || !password || username == "" || password == "") {
        return res.status(400).json({ success: false, message: 'Missing data, please enter completely' });
    }
    else  {
        next();
    }

};







const checkConfirmPassword = (req, res, next) => {
    const { password,confirmPassword } = req.body;
    if (password !== confirmPassword) {
        return res.status(400).json({ success: false, message: 'Confirmpassword is not asynchronous' });
    }
    else  {
        next();
    }
};





module.exports = {
     checkExistingUser,
     checkNullFields,
     checkConfirmPassword

 };