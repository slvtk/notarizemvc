package ru.itis.notarizemvc.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.notarizemvc.models.Client;
import ru.itis.notarizemvc.models.File;
import ru.itis.notarizemvc.models.FileStatus;
import ru.itis.notarizemvc.repositories.ClientRepository;
import ru.itis.notarizemvc.repositories.FileRepository;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final ClientRepository clientRepository;
    private final String UPLOAD_DIR = "C:\\StudyProjects\\notarizemvc\\uploads";

    @Override
    public File upload(Long clientId, MultipartFile multipartFile) {


        String encodedFileName = UUID.randomUUID().toString();
        log.info(multipartFile.toString());
        Path path = Paths.get(UPLOAD_DIR, encodedFileName +
                multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().indexOf(".")));
        try {
            Files.write(path, multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            File file = new File();
            file.setStatus(FileStatus.NOT_NOTARIZED);
            file.setClient(client);
            file.setName(multipartFile.getOriginalFilename());
            file.setPath("/uploads/" + path.getFileName().toString());
            return fileRepository.save(file);
        }
        throw new EntityNotFoundException();
    }

    @Override
    public List<File> getAll() {
        return fileRepository.findAll();
    }

    @Override
    public File getFileById(Long fileId) {
        Optional<File> fileOpt = fileRepository.findById(fileId);
        if (fileOpt.isPresent()) {
            return fileOpt.get();
        }
        throw new EntityNotFoundException();
    }

    @Override
    public List<File> getFilesByClientIdAndStatus(Long clientId, FileStatus status) {
        return fileRepository.findFilesByClientIdAndStatus(clientId, status);
    }

    @Override
    public Page<File> getFilesPageByClientIdAndStatus(Long clientId, String fileStatus, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return fileStatus != null ?
                fileRepository.findFilesByClientIdAndStatus(clientId, FileStatus.valueOf(fileStatus), pageable) :
                fileRepository.findFilesByClientId(clientId, pageable);
    }

    @Override
    public Path getFilePathById(Long fileId) {
        Optional<File> fileOpt = fileRepository.findById(fileId);
        if (fileOpt.isPresent()) {
            String[] splitPath = fileOpt.get().getPath().split("/");
            return Paths.get(UPLOAD_DIR, splitPath[splitPath.length - 1]);
        }
        throw new EntityNotFoundException();
    }
}
