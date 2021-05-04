package ru.itis.notarizemvc.utils;

import org.mapstruct.Mapper;
import ru.itis.notarizemvc.dto.FileDto;
import ru.itis.notarizemvc.models.File;

@Mapper(componentModel = "spring")
public interface FileMapper {

    FileDto toDto(File file);

}
