package ru.itis.notarizemvc.utils;

import org.mapstruct.Mapper;
import ru.itis.notarizemvc.dto.FileDto;
import ru.itis.notarizemvc.models.File;

@Mapper(componentModel = "spring")
public interface FileMapper {

    File fromDto(FileDto fileDto);

    FileDto toDto(File file);

}
