package ru.itis.notarizemvc.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String path;
    @Enumerated(EnumType.STRING)
    private FileStatus status;
    @CreationTimestamp
    private Date createdAt;
    @JsonIgnore
    @ManyToOne
    private Client client;

    public File(String name, String path, FileStatus status) {
        this.name = name;
        this.path = path;
        this.status = status;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
