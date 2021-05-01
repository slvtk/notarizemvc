package ru.itis.notarizemvc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.notarizemvc.models.Request;
import ru.itis.notarizemvc.models.RequestStatus;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    Page<Request> findRequestsByClientIdAndStatus(Long clientId, RequestStatus requestStatus, Pageable pageable);

    Page<Request> findRequestsByClientId(Long clientId, Pageable pageable);

    Page<Request> findRequestsByNotaryId(Long notaryId, Pageable pageable);

    Page<Request> findRequestsByNotaryIdAndStatus(Long notaryId, RequestStatus requestStatus, Pageable pageable);
}
