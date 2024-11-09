package ru.ners.userservice.repository.impl;

import org.springframework.stereotype.Repository;
import ru.ners.userservice.model.User;
import ru.ners.userservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public void saveUser(User user) {

    }

    @Override
    public Optional<User> getUser(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getUsers() {
        return List.of();
    }
}
