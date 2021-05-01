package ru.itis.notarizemvc.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.notarizemvc.dto.SignUpDto;
import ru.itis.notarizemvc.services.SignUpService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final SignUpService signUpService;

    @GetMapping("/signup")
    public String signUpPage(@ModelAttribute SignUpDto signUpDto) {
        return "sign_up";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute SignUpDto signUpDto) {
        log.info(signUpDto.toString());
        signUpService.signUp(signUpDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String signIn() {
        return "login";
    }

}
