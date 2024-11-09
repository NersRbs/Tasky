package ru.ners.userservice.service;

import ru.ners.userservice.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    User getUser(Long id);

    List<User> getUsers();
}