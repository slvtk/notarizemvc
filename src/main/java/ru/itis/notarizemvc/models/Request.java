package ru.itis.notarizemvc.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Request {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String title;
    private String description;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Notary notary;
    @OneToMany
    @JoinColumn(name="request_id")
    private List<File> files;
    @CreationTimestamp
    private Date createdAt;
    @Enumerated(value = EnumType.STRING)
    private RequestStatus status;
}
