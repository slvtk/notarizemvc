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
import ru.itis.notarizemvc.models.Request;
import ru.itis.notarizemvc.services.NotaryService;
import ru.itis.notarizemvc.services.RequestService;

@Slf4j
@Controller
@RequestMapping("/notaries")
@RequiredArgsConstructor
public class NotaryController {

    private final NotaryService notaryService;
    private final RequestService requestService;

    @GetMapping("/{notaryId}")
    public String notaryPage(@PathVariable Long notaryId,
                             Model model) {
        model.addAttribute("notary", notaryService.getNotaryById(notaryId));
        return "notary/notary";
    }

    @GetMapping("/{notaryId}/requests")
    public String notaryRequests(@PathVariable Long notaryId,
                                 @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(required = false) String status,
                                 Model model) {
        Page<Request> requests = requestService.getRequestsPageByNotaryIdAndStatus(notaryId, status, page, 5);
        log.info(requests.toString());
        model.addAttribute("requests", requests);
        model.addAttribute("currentPage", page);
        model.addAttribute("status", status);
        return "notary/requests_page";
    }
}
