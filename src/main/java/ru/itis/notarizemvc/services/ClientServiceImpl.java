package ru.itis.notarizemvc.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.notarizemvc.models.Client;
import ru.itis.notarizemvc.repositories.ClientRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client getClientById(Long clientId) {
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        if (clientOpt.isPresent()) {
            return clientOpt.get();
        }
        throw new EntityNotFoundException();
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

}
