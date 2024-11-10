package ru.ners.userservice.service.impl;

import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ners.userservice.exception.NotFoundException;
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
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }
}
