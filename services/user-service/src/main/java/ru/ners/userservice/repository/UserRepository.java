package ru.ners.userservice.repository;

import ru.ners.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    boolean existsUserByUsername(String username);

    void addUser(User user);

    Optional<User> getUserByUsername(String username);

    List<User> getUsers();
}
