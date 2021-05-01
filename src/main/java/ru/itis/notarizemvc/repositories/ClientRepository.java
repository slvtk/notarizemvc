package ru.itis.notarizemvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.notarizemvc.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
