package ru.ners.userclient.client.impl;

import com.google.protobuf.Empty;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.ners.userclient.client.UserClient;
import ru.ners.usermanagement.model.UserMessage;
import ru.ners.usermanagement.service.UserServiceGrpc;

@Service
@RequiredArgsConstructor
public class UserClientImpl implements UserClient {
    @GrpcClient("user-service")
    private final UserServiceGrpc.UserServiceBlockingStub blockingStub;

    @Override
    public void addUser(UserMessage.AddUserRequest user) {
        blockingStub.addUser(user);
    }

    @Override
    public UserMessage.GetUserResponse getUserByName(UserMessage.GetUserRequest request) {
        return blockingStub.getUserByName(request);
    }

    @Override
    public UserMessage.GetUsersResponse getUsers() {
        return blockingStub.getUsers(Empty.getDefaultInstance());
    }
}
