package ru.itis.notarizemvc.dto;

import lombok.Data;
import ru.itis.notarizemvc.models.Client;
import ru.itis.notarizemvc.models.File;
import ru.itis.notarizemvc.models.RequestStatus;

import java.util.Date;
import java.util.List;

@Data
public class RequestDto {
    private Long id;
    private String title;
    private String description;
    private Date createdAt;
    private RequestStatus status;
    private Client client;
    private List<File> files;
}
