package ru.itis.notarizemvc.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.notarizemvc.dto.FileDto;
import ru.itis.notarizemvc.models.Client;
import ru.itis.notarizemvc.services.FileService;
import ru.itis.notarizemvc.utils.FileMapper;

import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final FileMapper fileMapper;

    @GetMapping
    public String fileList(Model model) {
        model.addAttribute("files", fileService.getAll()
                .stream().map(fileMapper::toDto)
                .collect(Collectors.toList()));
        return "file/files";
    }

    @GetMapping("/new")
    public String fileCreate() {
        return "file/file_new";
    }

    @PostMapping("/upload")
    @ResponseBody
    public FileDto fileUpload(@AuthenticationPrincipal Client client,
                              MultipartFile file) {
        return fileMapper.toDto(fileService.upload(client.getId(), file));
    }

    @GetMapping(value = "/{fileId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public FileSystemResource fileLoad(@PathVariable Long fileId){
        return new FileSystemResource(fileService.getFilePathById(fileId));
    }
}
