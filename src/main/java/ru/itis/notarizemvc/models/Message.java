package ru.itis.notarizemvc.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
    private Date createdAt;

    public Message(String text, User sender, User receiver, Date createdAt) {
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
        this.createdAt = createdAt;
    }
}
