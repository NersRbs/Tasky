package ru.ners.userservice.controller;

import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.TestcontainersConfiguration;
import ru.ners.usermanagement.model.UserMessage;
import ru.ners.usermanagement.service.UserServiceGrpc;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
public class UserControllerIntegrationTests {
    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub blockingStub;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void cleanDatabase() {
        jdbcTemplate.execute("DELETE FROM users");
    }

    @Test
    public void testAddUserAndGetByUsername() {
        // Arrange
        UserMessage.AddUserRequest addUserRequest = UserMessage.AddUserRequest.newBuilder()
                .setEmail("user@gmail.com")
                .setUsername("user123")
                .setPassword("password")
                .build();

        // Act
        Empty empty = blockingStub.addUser(addUserRequest);
        UserMessage.GetUserResponse getUserResponse = blockingStub.getUserByUsername(UserMessage.GetUserByUsernameRequest.newBuilder()
                .setUsername("user123")
                .build());

        // Assert
        assertEquals(Empty.getDefaultInstance(), empty);

        assertEquals("user@gmail.com", getUserResponse.getEmail());
        assertEquals("user123", getUserResponse.getUsername());
        assertEquals("password", getUserResponse.getPassword());
    }

    @Test
    public void testAddUserTwice() {
        // Arrange
        UserMessage.AddUserRequest addFirstUserRequest = UserMessage.AddUserRequest.newBuilder()
                .setEmail("user@gmail.com")
                .setUsername("user123")
                .setPassword("password")
                .build();

        UserMessage.AddUserRequest addSecondUserRequest = UserMessage.AddUserRequest.newBuilder()
                .setEmail("person@mail.com")
                .setUsername("user123")
                .setPassword("password")
                .build();

        // Act
        Empty empty = blockingStub.addUser(addFirstUserRequest);
        StatusRuntimeException exception = assertThrows(StatusRuntimeException.class, () -> blockingStub.addUser(addSecondUserRequest));

        // Assert
        assertEquals(Empty.getDefaultInstance(), empty);

        assertEquals(Status.ALREADY_EXISTS.getCode(), exception.getStatus().getCode());
        assertEquals("User already exists", exception.getStatus().getDescription());
    }

    @Test
    public void testGetNonExistentUser() {
        // Arrange
        UserMessage.GetUserByUsernameRequest getUserByUsernameRequest = UserMessage.GetUserByUsernameRequest.newBuilder()
                .setUsername("user123")
                .build();

        // Act
        StatusRuntimeException exception = assertThrows(StatusRuntimeException.class, () -> blockingStub.getUserByUsername(getUserByUsernameRequest));

        // Assert
        assertEquals(Status.NOT_FOUND.getCode(), exception.getStatus().getCode());
        assertEquals("User not found", exception.getStatus().getDescription());
    }

    @Test
    public void testGetUsers() {
        // Arrange
        UserMessage.AddUserRequest addUserRequest = UserMessage.AddUserRequest.newBuilder()
                .setEmail("user@gmail.com")
                .setUsername("user123")
                .setPassword("password")
                .build();

        UserMessage.AddUserRequest addSecondUserRequest = UserMessage.AddUserRequest.newBuilder()
                .setEmail("person@mail.com")
                .setUsername("user456")
                .setPassword("password")
                .build();

        UserMessage.AddUserRequest addThirdUserRequest = UserMessage.AddUserRequest.newBuilder()
                .setEmail("human@gmail.com")
                .setUsername("user789")
                .setPassword("password")
                .build();

        // Act
        Empty empty = blockingStub.addUser(addUserRequest);
        Empty empty2 = blockingStub.addUser(addSecondUserRequest);
        Empty empty3 = blockingStub.addUser(addThirdUserRequest);
        UserMessage.GetUsersResponse getUsersResponse = blockingStub.getUsers(Empty.getDefaultInstance());

        // Assert
        assertEquals(Empty.getDefaultInstance(), empty);
        assertEquals(Empty.getDefaultInstance(), empty2);
        assertEquals(Empty.getDefaultInstance(), empty3);

        assertEquals(3, getUsersResponse.getUsersCount());
        assertEquals("user123", getUsersResponse.getUsers(0).getUsername());
        assertEquals("user456", getUsersResponse.getUsers(1).getUsername());
        assertEquals("user789", getUsersResponse.getUsers(2).getUsername());
    }
}
