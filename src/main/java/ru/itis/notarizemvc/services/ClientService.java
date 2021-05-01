package ru.itis.notarizemvc.services;

import ru.itis.notarizemvc.models.Client;

import java.util.List;

public interface ClientService {

    Client getClientById(Long clientId);

    List<Client> getAll();

}
