package ru.itis.notarizemvc.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.notarizemvc.models.Notary;
import ru.itis.notarizemvc.repositories.NotaryRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotaryServiceImpl implements NotaryService {

    private final NotaryRepository notaryRepository;

    @Override
    public Notary getNotaryById(Long notaryId) {
        Optional<Notary> notaryOpt = notaryRepository.findById(notaryId);
        if (notaryOpt.isPresent()) {
            return notaryOpt.get();
        }
        throw new EntityNotFoundException();
    }

    @Override
    public List<Notary> getAll() {
        return notaryRepository.findAll();
    }
}
