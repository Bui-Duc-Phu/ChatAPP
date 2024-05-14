const checkNullFields = (req, res, next) => {
    const { sender_id, receiver_id, message_type, message_content } = req.body;

    // Kiểm tra nếu bất kỳ tham số nào bị thiếu hoặc không hợp lệ
    if (sender_id === undefined || receiver_id === undefined || message_type === undefined || message_content === undefined ||
        sender_id === null || receiver_id === null || message_type === null || message_content === null ||
        typeof sender_id !== 'number' || typeof receiver_id !== 'number' || typeof message_type !== 'string' || typeof message_content !== 'string'
    ) {
        return res.status(400).json({ error: 'message_Null' });
    } else {
        // Nếu tất cả các tham số đều hợp lệ, chuyển sang middleware tiếp theo hoặc hàm xử lý chính
        next();
    }
};



const checkNullgetMessage = (req, res, next) => {
    const { sender_id, receiver_id } = req.params;
    // console.log('senderid  = ' + sender_id + 'receiverid = ' + receiver_id)
    if (sender_id  == 0   || sender_id === undefined || sender_id === null  || 
        receiver_id  == 0   || receiver_id === undefined || receiver_id === null
    ) {
        return res.status(400).json({ success: false, message: 'Confirmpassword is not asynchronous' });
    }
    else  {
        next();
    }
};

module.exports = { checkNullFields,checkNullgetMessage };
