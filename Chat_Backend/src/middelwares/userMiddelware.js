

const checkNullFields = (req, res, next) => {
    const {id} = req.params;
    if (id  == 0   || id === undefined || id === null) {
        return res.status(400).json({ success: false, message: 'Confirmpassword is not asynchronous' });
    }
    else  {
        next();
    }
};


module.exports = {checkNullFields}