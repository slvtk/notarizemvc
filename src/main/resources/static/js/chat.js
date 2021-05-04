const url = 'http://localhost'
let stompClient
let userName = $('#userName').text()
let selectedUser

function connectToChat() {
    let href = $(location).attr('href')
    let socket = new SockJS(url + '/chat')
    selectedUser = href.substring(href.lastIndexOf("/") + 1, href.length)

    scrollToBottom()
    $('#receiver').text(selectedUser)
    stompClient = Stomp.over(socket)
    console.log("connecting to chat...")
    console.log('Selected: ' + userName)
    //берем из url имя получателя
    stompClient.connect({}, function (frame) {
        console.log("connected to: " + frame)
        stompClient.subscribe("/topic/messages/" + userName, function (response) {
            let data = JSON.parse(response.body)
            if (selectedUser === data.sender){
                render(data.text, data.sender)
            }
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
    if (message.trim() === '') {
        alert("Пожалуйста, введите сообщение")
        return false
    }

    stompClient.send("/app/chat/" + selectedUser, {}, JSON.stringify({
        sender: userName,
        receiver: selectedUser,
        text: message,
        createdAt: new Date()
    }))
    return true
}