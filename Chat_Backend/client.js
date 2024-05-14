const io = require("socket.io-client");

// Địa chỉ máy chủ của bạn, thay thế bằng địa chỉ thực tế của máy chủ
const serverUrl = "http://192.168.0.102:3001";

// Kết nối đến máy chủ
const socket = io.connect(serverUrl);



// Lắng nghe sự kiện 'connect'
socket.on("connect", () => {
  console.log("Connected to server");
  socket.emit('joinRoom', '123456');

  // Gửi một tin nhắn đến máy chủ
  const message = "123456";
  socket.emit("key", message);
});

// Lắng nghe sự kiện 'message' từ máy chủ
socket.on("123", (data) => {
  console.log("Received response from server:", data);
});

// Lắng nghe sự kiện 'disconnect'
socket.on("disconnect", () => {
  console.log("Disconnected from server");
});
