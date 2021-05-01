package ru.itis.notarizemvc.utils;

import org.mapstruct.Mapper;
import ru.itis.notarizemvc.dto.RequestDto;
import ru.itis.notarizemvc.models.Request;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    Request fromDto(RequestDto requestDto);

    RequestDto toDto(Request request);

}
