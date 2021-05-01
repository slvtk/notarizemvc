package ru.itis.notarizemvc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Notary extends User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String fullName;
    private String city;
    private String company;
    private String bio;
    private Integer age;

    public Notary(String username,
                  String password,
                  Role role,
                  String fullName,
                  String city,
                  String company,
                  String bio) {
        super(username, password, role);
        this.fullName = fullName;
        this.city = city;
        this.company = company;
        this.bio = bio;
    }

    public Notary(String username,
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
