package ru.itis.notarizemvc.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itis.notarizemvc.models.FileStatus;
import ru.itis.notarizemvc.models.Notary;
import ru.itis.notarizemvc.models.Request;
import ru.itis.notarizemvc.models.RequestStatus;
import ru.itis.notarizemvc.repositories.RequestRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final ClientService clientService;
    private final FileService fileService;
    private final NotaryService notaryService;

    @Override
    public List<Request> getAllReq() {
        return requestRepository.findAll();
    }

    @Override
    public Request createReq(Long clientId, Request request) {
        request.setClient(clientService.getClientById(clientId));
        request.setStatus(RequestStatus.DRAFT);
        return requestRepository.save(request);
    }

    @Override
    public Request getReqById(Long requestId) {
        Optional<Request> requestOpt = requestRepository.findById(requestId);
        if (requestOpt.isPresent()) {
            return requestOpt.get();
        }
        throw new EntityNotFoundException();
    }

    @Override
    public Request addFileToReq(Long requestId, Long fileId) {
        Optional<Request> requestOpt = requestRepository.findById(requestId);
        if (requestOpt.isPresent()) {
            Request request = requestOpt.get();
            request.getFiles().add(fileService.getFileById(fileId));
            return requestRepository.save(request);
        }
        throw new EntityNotFoundException();
    }

    @Override
    public Request removeFileFromReq(Long requestId, Long fileId) {
        Optional<Request> requestOpt = requestRepository.findById(requestId);
        if (requestOpt.isPresent()) {
            Request request = requestOpt.get();
            request.getFiles().remove(fileService.getFileById(fileId));
            return requestRepository.save(request);
        }
        throw new EntityNotFoundException();
    }

    @Override
    public Request publishRequest(Long requestId) {
        Optional<Request> requestOpt = requestRepository.findById(requestId);
        if (requestOpt.isPresent()) {
            Request request = requestOpt.get();
            if (request.getFiles().isEmpty()) {
                throw new IllegalStateException();
            }
            request.setStatus(RequestStatus.FREE);
            return requestRepository.save(request);
        }
        throw new EntityNotFoundException();
    }

    @Override
    public Request notarizeRequest(Long requestId) {
        Optional<Request> requestOpt = requestRepository.findById(requestId);
        if (requestOpt.isPresent()) {
            Request request = requestOpt.get();
            request.setStatus(RequestStatus.DONE);
            request.getFiles().forEach(f -> f.setStatus(FileStatus.NOTARIZED));
            return requestRepository.save(request);
        }
        throw new EntityNotFoundException();
    }

    @Override
    public Request takeReq(Long requestId, Long notaryId) {
        Optional<Request> requestOpt = requestRepository.findById(requestId);
        if (requestOpt.isPresent()) {
            Request request = requestOpt.get();
            Notary notary = notaryService.getNotaryById(notaryId);
            request.setNotary(notary);
            request.setStatus(RequestStatus.IN_PROGRESS);
            return requestRepository.save(request);
        }
        throw new EntityNotFoundException();
    }

    @Override
    public Page<Request> getRequestsPageByClientIdAndStatus(Long clientId, String status, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return status != null ?
                requestRepository.findRequestsByClientIdAndStatus(clientId, RequestStatus.valueOf(status), pageable) :
                requestRepository.findRequestsByClientId(clientId, pageable);
    }

    @Override
    public Page<Request> getRequestsPageByNotaryIdAndStatus(Long notaryId, String status, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return status != null ?
                requestRepository.findRequestsByNotaryIdAndStatus(notaryId, RequestStatus.valueOf(status), pageable) :
                requestRepository.findRequestsByNotaryId(notaryId, pageable);
    }
}
