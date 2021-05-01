package ru.itis.notarizemvc.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.notarizemvc.models.Message;
import ru.itis.notarizemvc.models.Role;
import ru.itis.notarizemvc.models.User;
import ru.itis.notarizemvc.services.ClientService;
import ru.itis.notarizemvc.services.NotaryService;

import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/chats")
@RequiredArgsConstructor
@CrossOrigin
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NotaryService notaryService;
    private final ClientService clientService;

    @GetMapping
    public String index(@AuthenticationPrincipal User user, Model model) {
        if (user.getRole().equals(Role.ROLE_CLIENT)){
            model.addAttribute("notaries", notaryService.getAll().stream().map(User::getUsername).collect(Collectors.toList()));
        }else {
            model.addAttribute("clients", clientService.getAll().stream().map(User::getUsername).collect(Collectors.toList()));
        }
        return "/chat/chat";
    }

    @MessageMapping("/chat/{receiver}")
    public Message say(@DestinationVariable String receiver,
                       Message message) {
        log.info("Message: " + message + " to " + receiver + " received");
        log.info(simpMessagingTemplate.getUserDestinationPrefix());
        simpMessagingTemplate.convertAndSend("/topic/messages/" + receiver, message);
        return message;

    }
}
