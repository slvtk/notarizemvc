package ru.itis.notarizemvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MessageDto {
    private String sender;
    private String receiver;
    private String text;
    private Date createdAt;
}
