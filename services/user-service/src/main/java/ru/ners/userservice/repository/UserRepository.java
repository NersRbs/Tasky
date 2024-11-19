package ru.ners.userservice.repository;

import ru.ners.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void addUser(User user);

    Optional<User> getUserByName(String name);

    List<User> getUsers();
}
