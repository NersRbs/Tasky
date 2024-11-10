package ru.ners.userservice.repository;

import ru.ners.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void saveUser(User user);

    Optional<User> getUserByUsername(String username);

    List<User> getUsers();
}
