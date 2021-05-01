package ru.itis.notarizemvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.notarizemvc.models.Notary;

@Repository
public interface NotaryRepository extends JpaRepository<Notary, Long> {
}
