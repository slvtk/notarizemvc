package ru.itis.notarizemvc.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@ToString(callSuper = true, exclude = "files")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String city;
    private Integer age;
    @OneToMany
    @JoinColumn(name = "client_id")
    private List<File> files;

    public Client(String username,
                  String password,
                  Role role,
                  String fullName,
                  String city,
                  Integer age) {
        super(username, password, role);
        this.fullName = fullName;
        this.city = city;
        this.age = age;
    }
}
