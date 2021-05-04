package ru.itis.notarizemvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itis.notarizemvc.models.Message;
import ru.itis.notarizemvc.models.User;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "select m from Message m where (m.sender = ?1 and m.receiver = ?2) or (m.sender = ?2 and m.receiver = ?1) order by m.createdAt asc")
    List<Message> findChatUsers(User sender, User receiver);

}
