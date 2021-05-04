package ru.itis.notarizemvc.services;

import ru.itis.notarizemvc.models.Message;

import java.util.List;

public interface MessageService {

    Message save(Message message);

    List<Message> getPrivateChatMessages(String sender, String receiver);

}
