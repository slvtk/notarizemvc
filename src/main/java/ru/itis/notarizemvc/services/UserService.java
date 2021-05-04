package ru.itis.notarizemvc.services;

import ru.itis.notarizemvc.models.User;

import java.util.List;

public interface UserService {

     List<? extends User> getAllUserPartners(Long userId);

}
