package ru.itis.notarizemvc.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.notarizemvc.dto.MessageDto;
import ru.itis.notarizemvc.models.Role;
import ru.itis.notarizemvc.models.User;
import ru.itis.notarizemvc.services.ClientService;
import ru.itis.notarizemvc.services.MessageService;
import ru.itis.notarizemvc.services.NotaryService;
import ru.itis.notarizemvc.services.UserService;
import ru.itis.notarizemvc.utils.MessageMapper;

import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/chats")
@RequiredArgsConstructor
@CrossOrigin
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserService userService;
    private final NotaryService notaryService;
    private final ClientService clientService;
    private final MessageService messageService;
    private final MessageMapper messageMapper;

    @GetMapping
    public String index(@AuthenticationPrincipal User user,
                        Model model) {
        model.addAttribute("partners",userService.getAllUserPartners(user.getId()));
        return "chat/chats";
    }


    @GetMapping("/{receiverUsername}")
    public String chat(@AuthenticationPrincipal User user,
                       @PathVariable String receiverUsername,
                       Model model) {
        model.addAttribute("chat", messageService.getPrivateChatMessages(user.getUsername(), receiverUsername)
                .stream().map(messageMapper::toDto)
                .collect(Collectors.toList()));
        return "/chat/chat";
    }

    @MessageMapping("/chat/{receiver}")
    public MessageDto say(@DestinationVariable String receiver,
                          MessageDto messageDto) {
        log.info("Message: " + messageDto + " to " + receiver + " received");
        messageService.save(messageMapper.fromDto(messageDto));
        simpMessagingTemplate.convertAndSend("/topic/messages/" + receiver, messageDto);
        return messageDto;
    }
}
