package ru.itis.notarizemvc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.notarizemvc.models.File;
import ru.itis.notarizemvc.models.FileStatus;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findFilesByClientIdAndStatus(Long clientId, FileStatus status);

    Page<File> findFilesByClientIdAndStatus(Long clientId, FileStatus status, Pageable pageable);

    Page<File> findFilesByClientId(Long clientId, Pageable pageable);

}
