package ru.itis.notarizemvc.services;

import ru.itis.notarizemvc.models.Notary;

import java.util.List;

public interface NotaryService {

    Notary getNotaryById(Long notaryId);

    List<Notary> getAll();

}
