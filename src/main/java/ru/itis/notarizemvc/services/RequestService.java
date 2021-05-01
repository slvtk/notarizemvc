package ru.itis.notarizemvc.services;

import org.springframework.data.domain.Page;
import ru.itis.notarizemvc.models.Request;

import java.util.List;

public interface RequestService {

    List<Request> getAllReq();

    Request createReq(Long userId, Request request);

    Request getReqById(Long requestId);

    Request addFileToReq(Long requestId, Long fileId);

    Request removeFileFromReq(Long requestId, Long fileId);

    Request publishRequest(Long requestId);

    Request notarizeRequest(Long requestId);

    Request takeReq(Long requestId, Long notaryId);

    Page<Request> getRequestsPageByClientIdAndStatus(Long clientId, String status, Integer page, Integer size);

    Page<Request> getRequestsPageByNotaryIdAndStatus(Long notaryId, String status, Integer page, Integer size);
}
