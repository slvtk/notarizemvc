package ru.itis.notarizemvc.utils;

import org.mapstruct.Mapper;
import ru.itis.notarizemvc.dto.ClientDto;
import ru.itis.notarizemvc.models.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client clientFromDto(ClientDto clientDto);

    ClientDto toDto(Client client);
}
