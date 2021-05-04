package ru.itis.notarizemvc.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.notarizemvc.models.Client;
import ru.itis.notarizemvc.models.Role;
import ru.itis.notarizemvc.models.User;
import ru.itis.notarizemvc.repositories.RequestRepository;
import ru.itis.notarizemvc.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final NotaryService notaryService;
    private final ClientService clientService;

    @Override
    public List<? extends User> getAllUserPartners(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new EntityNotFoundException();
        }
        User user = userOpt.get();
        if (user.getRole().equals(Role.ROLE_CLIENT)) {
            return notaryService.getAll();
        }
        return clientService.getAll();
    }
}
