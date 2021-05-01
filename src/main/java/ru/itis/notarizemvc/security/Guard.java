package ru.itis.notarizemvc.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.itis.notarizemvc.models.File;
import ru.itis.notarizemvc.models.Role;
import ru.itis.notarizemvc.models.User;
import ru.itis.notarizemvc.repositories.FileRepository;
import ru.itis.notarizemvc.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Guard {

    private final UserRepository userRepository;
    private final FileRepository fileRepository;

    public boolean checkUserId(Authentication authentication, Long userId) {

        Optional<User> userOpt = userRepository.findByUsername(authentication.getName());
        return userOpt.map(user -> user.getId().equals(userId)).orElse(false);

    }

    public boolean checkFileAccess(Authentication authentication, Long fileId) {
        if (((User) authentication.getPrincipal()).getRole().equals(Role.ROLE_NOTARY)) {
            return true;
        }
        Optional<File> fileOpt = fileRepository.findById(fileId);
        return fileOpt.map(file -> file.getClient().getUsername()
                .equals(authentication.getName()))
                .orElse(false);
    }
}
