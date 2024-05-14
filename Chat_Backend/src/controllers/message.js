const { pool } = require('../configs/connectionSQL');

const postMessage = (req, res) => {
    const { sender_id, receiver_id, message_type, message_content } = req.body;

    // Tạo truy vấn SQL để chèn tin nhắn mới vào cơ sở dữ liệu
    const query = `INSERT INTO Message (sender_id, receiver_id, message_type, message_content) VALUES (?, ?, ?, ?)`;
    
    // Thực thi truy vấn SQL với các tham số được cung cấp
    pool.query(query, [sender_id, receiver_id, message_type, message_content], (error, results) => {
        if (error) {
            console.error('Error posting message:', error);
            // Xử lý lỗi
            res.status(500).json({ error: 'An error occurred while posting message' });
        } else {
            console.log('Message posted successfully');
            // Xử lý khi chèn tin nhắn thành công
            res.status(200).json({ message: 'Message posted successfully' });
        }
    });
};



const getMessage = (req, res) => {
    const { sender_id, receiver_id } = req.params;

    const query = `SELECT * FROM Message WHERE (sender_id = ? OR receiver_id = ?) AND (sender_id = ? OR receiver_id = ?)`;
    pool.query(query, [sender_id, sender_id, receiver_id, receiver_id], (error, results) => {
        if (error) {
            console.error('Error retrieving messages:', error);
            res.status(500).json({ error: 'An error occurred while retrieving messages' });
        } else {
            console.log('Messages retrieved successfully');
            const responseData = JSON.stringify(results); 
            res.send(responseData); 
        }
    });
};









module.exports = { postMessage, getMessage};
