var stompClient = null;

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        var roomId = document.getElementById("roomId").value;
        stompClient.subscribe(`/topic/chat/${roomId}`, function (message) {
            displayMessage(JSON.parse(message.body).content);
        });
    });
}
function createRoom()
{

    connect();
}
function sendMessage() {

    var messageContent = document.getElementById("messageInput").value;
    var roomId = document.getElementById("roomId").value;
    if(messageContent && stompClient) {
        var chatMessage = {
            content: messageContent,
            sender: "User", // Modify this as needed
            roomId: roomId,
            type: 'CHAT' // You might need to adjust this depending on your ChatMessage class
        };
        const string = `/app/chat/${roomId}/send`;
        stompClient.send(string, {}, JSON.stringify(chatMessage));
        document.getElementById("messageInput").value = '';
    }
}

function displayMessage(message) {
    var chatBox = document.getElementById("chatBox");
    chatBox.innerHTML += "<div>" + message + "</div>";
}


