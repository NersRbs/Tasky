package ru.ners.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ners.userservice.model.User;
import ru.ners.userservice.repository.UserRepository;
import ru.ners.userservice.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getUser(id)
                .orElseThrow();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }
}
