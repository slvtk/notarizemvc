package ru.itis.notarizemvc.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.notarizemvc.models.Message;
import ru.itis.notarizemvc.models.User;
import ru.itis.notarizemvc.repositories.MessageRepository;
import ru.itis.notarizemvc.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getPrivateChatMessages(String sender, String receiver) {
        Optional<User> senderOpt = userRepository.findByUsername(sender);
        Optional<User> receiverOpt = userRepository.findByUsername(receiver);
        if (senderOpt.isPresent() && receiverOpt.isPresent()) {
            log.info(messageRepository.findChatUsers(senderOpt.get(), receiverOpt.get()).toString());
            return messageRepository.findChatUsers(senderOpt.get(), receiverOpt.get());
        }
        throw new EntityNotFoundException();
    }
}
