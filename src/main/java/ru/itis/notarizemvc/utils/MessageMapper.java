package ru.itis.notarizemvc.utils;

import ru.itis.notarizemvc.dto.MessageDto;
import ru.itis.notarizemvc.models.Message;

public interface MessageMapper {

    Message fromDto(MessageDto messageDto);

    MessageDto toDto(Message message);

}
