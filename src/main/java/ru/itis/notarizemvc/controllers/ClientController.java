package ru.itis.notarizemvc.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.notarizemvc.models.File;
import ru.itis.notarizemvc.models.Request;
import ru.itis.notarizemvc.services.ClientService;
import ru.itis.notarizemvc.services.FileService;
import ru.itis.notarizemvc.services.RequestService;
import ru.itis.notarizemvc.utils.ClientMapper;

@Slf4j
@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final FileService fileService;
    private final ClientMapper clientMapper;
    private final RequestService requestService;

    @GetMapping("/{clientId}")
    public String pageClient(@PathVariable Long clientId,
                             Model model) {
        model.addAttribute("client", clientMapper.toDto(clientService.getClientById(clientId)));
        return "client/client";
    }

    @GetMapping("/{clientId}/files")
    public String listFiles(@PathVariable Long clientId,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(required = false) String status,
                            Model model) {
        Page<File> files = fileService.getFilesPageByClientIdAndStatus(clientId, status, page, 5);
        model.addAttribute("files", files);
        model.addAttribute("currentPage", page);
        model.addAttribute("status", status);
        return "client/files_page";
    }

    @GetMapping("/{clientId}/requests")
    public String listRequests(@PathVariable Long clientId,
                               @RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(required = false) String status,
                               Model model) {
        Page<Request> requests = requestService.getRequestsPageByClientIdAndStatus(clientId, status, page, 5);
        log.info(requests.toString());
        model.addAttribute("requests", requests);
        model.addAttribute("currentPage", page);
        model.addAttribute("status", status);
        return "client/requests_page";
    }
}
