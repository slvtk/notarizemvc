package ru.itis.notarizemvc.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.itis.notarizemvc.dto.MessageDto;
import ru.itis.notarizemvc.models.Message;
import ru.itis.notarizemvc.models.User;
import ru.itis.notarizemvc.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageMapperImpl implements MessageMapper {

    private final UserRepository userRepository;

    @Override
    public Message fromDto(MessageDto messageDto) {
        log.info(messageDto.getReceiver());
        log.info(messageDto.getSender());
        Optional<User> senderOpt = userRepository.findByUsername(messageDto.getSender());
        Optional<User> receiverOpt = userRepository.findByUsername(messageDto.getReceiver());
        if (senderOpt.isPresent() && receiverOpt.isPresent()) {
            return new Message(
                    messageDto.getText(),
                    senderOpt.get(),
                    receiverOpt.get(),
                    messageDto.getCreatedAt()
            );
        }
        throw new EntityNotFoundException();
    }

    @Override
    public MessageDto toDto(Message message) {
        return new MessageDto(
                message.getSender().getUsername(),
                message.getReceiver().getUsername(),
                message.getText(),
                message.getCreatedAt()
        );
    }
}
