const url = 'http://localhost'
let stompClient
let userName = $('#userName').text()
let selectedUser

function connectToChat() {
    let socket = new SockJS(url + '/chat');
    stompClient = Stomp.over(socket)
    console.log("connecting to chat...")
    stompClient.connect({}, function (frame) {
        console.log("connected to: " + frame)
        stompClient.subscribe("/topic/messages/" + userName, function (response) {
            alert("New message from:  " + userName)
            let data = JSON.parse(response.body)
            render(data.text, data.sender)
        });
    });
}

function sendMsg(message) {
    console.log("Selected user: " + selectedUser)
    if (typeof selectedUser === "undefined") {
        alert("Пожалуйста, выберите собеседника")
        return false
    }
    console.log("Message : " + message)
    console.log(message.trim() !== '')
    if (message.trim() === ''){
        alert("Пожалуйста, введите сообщение")
        return false
    }

    stompClient.send("/app/chat/" + selectedUser, {}, JSON.stringify({
        sender: userName,
        text: message
    }))
    return true
}

function selectUser(userName) {
    console.log('Selected: ' + userName)
    selectedUser = userName
}