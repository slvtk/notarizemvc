package ru.itis.notarizemvc.services;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.notarizemvc.models.File;
import ru.itis.notarizemvc.models.FileStatus;

import java.nio.file.Path;
import java.util.List;

public interface FileService {

    File upload(Long clientId, MultipartFile multipartFile);

    List<File> getAll();

    File getFileById(Long fileId);

    List<File> getFilesByClientIdAndStatus(Long clientId, FileStatus status);

    Page<File> getFilesPageByClientIdAndStatus(Long clientId, String fileStatus, Integer page, Integer size);

    Path getFilePathById(Long fileId);

}
