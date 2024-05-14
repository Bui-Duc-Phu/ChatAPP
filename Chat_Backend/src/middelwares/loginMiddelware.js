const {pool}  = require('../configs/connectionSQL')






const checkNullFields = (req, res, next) => {
    const { username,password } = req.body;
    
    if (username === null  || password === null  || !username || !password) {
        return res.status(400).json({ success: false, message: 'Missing data, please enter completely' });
    }
    else  {
        next();
    
    }

};

module.exports = {
    checkNullFields
};