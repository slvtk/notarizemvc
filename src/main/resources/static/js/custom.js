let $chatHistory
let $button
let $textarea
let $chatHistoryList

function init() {
    cacheDOM()
    bindEvents()
}

function bindEvents() {
    $button.on('click', addMessage.bind(this))
    $textarea.on('keyup', addMessageEnter.bind(this))
}

function cacheDOM() {
    $chatHistory = $('.chat-history')
    $button = $('#sendBtn')
    $textarea = $('#message-to-send')
    $chatHistoryList = $chatHistory.find('ul')
}

function render(message, userName) {
    scrollToBottom()
    const templateResponse = Handlebars.compile($("#message-response-template").html());
    const contextResponse = {
        response: message,
        time: getCurrentTime(),
        userName: userName
    };

    setTimeout(function () {
        $chatHistoryList.append(templateResponse(contextResponse))
        scrollToBottom()
    }.bind(this), 1500)
}

//Отправка сообщения
function sendMessage(message) {
    if (sendMsg(message)) {
        scrollToBottom()
        let template = Handlebars.compile($("#message-template").html())
        let context = {
            messageOutput: message,
            time: getCurrentTime(),
            toUserName: selectedUser
        }
        $chatHistoryList.append(template(context))
        scrollToBottom()
        $textarea.val('')
    }
}

//Получаем текущее время
function getCurrentTime() {
    return new Date().toLocaleTimeString().replace(/([\d]+:[\d]{2})(:[\d]{2})(.*)/, "$1$3")
}

function addMessage() {
    sendMessage($textarea.val())
}

function scrollToBottom() {
    $chatHistory.scrollTop($chatHistory[0].scrollHeight);
}

function addMessageEnter(event) {
    // enter was pressed
    if (event.keyCode === 13) {
        addMessage()
    }
}

init() 