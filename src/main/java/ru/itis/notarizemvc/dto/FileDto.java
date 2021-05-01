package ru.itis.notarizemvc.dto;

import lombok.Data;
import ru.itis.notarizemvc.models.Client;
import ru.itis.notarizemvc.models.FileStatus;

import java.util.Date;

@Data
public class FileDto {
    private Long id;
    private String name;
    private String path;
    private FileStatus status;
    private Date createdAt;
    private Client client;
}
