var stompClient = null;

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/chat/room1', function (message) {
            displayMessage(JSON.parse(message.body).content);
        });
    });
}

function sendMessage() {
    var messageContent = document.getElementById("messageInput").value;
    if(messageContent && stompClient) {
        var chatMessage = {
            content: messageContent,
            sender: "User", // Modify this as needed
            type: 'CHAT' // You might need to adjust this depending on your ChatMessage class
        };
        stompClient.send("/app/chat/room1/send", {}, JSON.stringify(chatMessage));
        document.getElementById("messageInput").value = '';
    }
}

function displayMessage(message) {
    var chatBox = document.getElementById("chatBox");
    chatBox.innerHTML += "<div>" + message + "</div>";
}

connect();
