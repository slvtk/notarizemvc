package ru.itis.notarizemvc.dto;

import lombok.Data;
import ru.itis.notarizemvc.models.Role;

@Data
public class SignUpDto {
    private String username;
    private String password;
    private String fullName;
    private String city;
    private Integer age;
    private Role role;
}
