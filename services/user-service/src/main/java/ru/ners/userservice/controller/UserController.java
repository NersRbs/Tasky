package ru.ners.userservice.controller;

import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.ners.usermanagement.model.UserMessage;
import ru.ners.usermanagement.service.UserServiceGrpc;
import ru.ners.userservice.model.User;
import ru.ners.userservice.service.UserService;

@GrpcService
@RequiredArgsConstructor
public class UserController extends UserServiceGrpc.UserServiceImplBase {
    private final UserService userService;

    @Override
    public void addUser(UserMessage.AddUserRequest request, StreamObserver<Empty> responseObserver) {
        var user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        userService.addUser(user);

        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void getUserByUsername(UserMessage.GetUserByUsernameRequest request, StreamObserver<UserMessage.GetUserResponse> responseObserver) {
        var user = userService.getUserByUsername(request.getUsername());

        var response = UserMessage.GetUserResponse.newBuilder()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUsers(Empty request, StreamObserver<UserMessage.GetUsersResponse> responseObserver) {
        var users = userService.getUsers();

        var responseBuilder = UserMessage.GetUsersResponse.newBuilder();
        users.forEach(user -> responseBuilder.addUsers(
                UserMessage.GetUserResponse.newBuilder()
                        .setId(user.getId())
                        .setEmail(user.getEmail())
                        .setUsername(user.getUsername())
                        .setPassword(user.getPassword())
                        .build()
                )
        );

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}
