package ru.ners.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    Long id;
    String username;
    String passwordHash;
    Map<String, String> contacts;
}
