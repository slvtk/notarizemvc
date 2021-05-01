package ru.itis.notarizemvc.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.notarizemvc.dto.RequestDto;
import ru.itis.notarizemvc.models.*;
import ru.itis.notarizemvc.services.FileService;
import ru.itis.notarizemvc.services.RequestService;
import ru.itis.notarizemvc.utils.RequestMapper;

import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;
    private final RequestMapper requestMapper;
    private final FileService fileService;

    @GetMapping
    public String listReq(Model model) {
        model.addAttribute("requests", requestService.getAllReq()
                .stream().map(requestMapper::toDto)
                .collect(Collectors.toList()));
        log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return "request/requests";
    }

    @GetMapping("/new")
    public String newReq(@ModelAttribute RequestDto requestDto) {
        return "request/request_new";
    }

    @GetMapping("/{requestId}")
    public String pageReq(@PathVariable Long requestId,
                          @AuthenticationPrincipal User user,
                          Model model) {
        model.addAttribute("request", requestMapper.toDto(requestService.getReqById(requestId)));
        if (user.getRole().equals(Role.ROLE_CLIENT)) {
            model.addAttribute("files", fileService.getFilesByClientIdAndStatus(user.getId(), FileStatus.NOT_NOTARIZED));
        }
        return "request/request";
    }

    @PostMapping
    public String createReq(@ModelAttribute RequestDto requestDto,
                            @AuthenticationPrincipal User user) {
        log.info(requestMapper.fromDto(requestDto).toString());
        Request createdReq = requestService.createReq(user.getId(), requestMapper.fromDto(requestDto));
        log.info("Created request: " + createdReq.toString());
        return "redirect:/requests/" + createdReq.getId();
    }

    @PostMapping("/{requestId}/publish")
    public String publishReq(@PathVariable Long requestId) {
        Request request = requestService.publishRequest(requestId);
        log.info("Published request: " + request.toString());
        return "redirect:/requests/" + request.getId();
    }

    @PostMapping("/{requestId}/add/{fileId}")
    public String addFileToRequest(@PathVariable Long requestId,
                                   @PathVariable Long fileId) {
        Request request = requestService.addFileToReq(requestId, fileId);
        return "redirect:/requests/" + request.getId();
    }

    @PostMapping("/{requestId}/remove/{fileId}")
    public String removeFileFromRequest(@PathVariable Long requestId,
                                        @PathVariable Long fileId) {
        Request request = requestService.removeFileFromReq(requestId, fileId);
        return "redirect:/requests/" + request.getId();
    }

    @PostMapping("/{requestId}/take")
    public String takeReq(@AuthenticationPrincipal Notary notary,
                          @PathVariable Long requestId) {
        requestService.takeReq(requestId, notary.getId());
        return "redirect:/requests/" + requestId;
    }

    @PostMapping("/{requestId}/notarize")
    public String takeReq(@PathVariable Long requestId) {
        requestService.notarizeRequest(requestId);
        return "redirect:/requests/" + requestId;
    }

}
