package ru.itis.notarizemvc.dto;

import lombok.Data;
import ru.itis.notarizemvc.models.File;

import java.util.List;

@Data
public class ClientDto {
    private Long id;
    private String fullName;
    private String city;
    private Integer age;
    private List<File> files;
}
