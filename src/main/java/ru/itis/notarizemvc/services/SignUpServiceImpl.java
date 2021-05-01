package ru.itis.notarizemvc.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.notarizemvc.dto.SignUpDto;
import ru.itis.notarizemvc.models.Client;
import ru.itis.notarizemvc.models.Notary;
import ru.itis.notarizemvc.models.Role;
import ru.itis.notarizemvc.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpDto signUpDto) {
        if (signUpDto.getRole().equals(Role.ROLE_CLIENT)) {
            userRepository.save(new Client(
                    signUpDto.getUsername(),
                    passwordEncoder.encode(signUpDto.getPassword()),
                    signUpDto.getRole(),
                    signUpDto.getFullName(),
                    signUpDto.getCity(),
                    signUpDto.getAge()
            ));
        } else if (signUpDto.getRole().equals(Role.ROLE_NOTARY)) {
            userRepository.save(new Notary(
                    signUpDto.getUsername(),
                    passwordEncoder.encode(signUpDto.getPassword()),
                    signUpDto.getRole(),
                    signUpDto.getFullName(),
                    signUpDto.getCity(),
                    signUpDto.getAge()
            ));
        }
    }
}
