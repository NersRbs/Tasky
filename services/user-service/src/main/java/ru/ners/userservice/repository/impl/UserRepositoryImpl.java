package ru.ners.userservice.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ners.userservice.model.User;
import ru.ners.userservice.repository.UserRepository;
import ru.ners.userservice.repository.mapper.UserMapper;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;

    @Override
    public boolean existsUserByUsername(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, username) > 0;
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users (email, username, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getUsername(), user.getPassword());
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ? LIMIT 1";
        List<User> users = jdbcTemplate.query(sql, userMapper, username);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.getFirst());
    }

    @Override
    public List<User> getUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, userMapper);
    }
}
