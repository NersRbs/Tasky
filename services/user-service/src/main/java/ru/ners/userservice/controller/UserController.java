package ru.ners.userservice.controller;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.ners.service.UserManagement;
import ru.ners.service.UserServiceGrpc;
import ru.ners.userservice.model.User;
import ru.ners.userservice.service.UserService;

@GrpcService
@RequiredArgsConstructor
public class UserController extends UserServiceGrpc.UserServiceImplBase {
    private final UserService userService;

    @Override
    public void saveUser(UserManagement.SaveUserRequest request, StreamObserver<Empty> responseObserver) {
        var user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        userService.saveUser(user);
    }

    @Override
    public void getUser(UserManagement.GetUserRequest request, StreamObserver<UserManagement.GetUserResponse> responseObserver) {
        var user = userService.getUserByUsername(request.getUsername());

        var response = UserManagement.GetUserResponse.newBuilder()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUsers(Empty request, StreamObserver<UserManagement.GetUsersResponse> responseObserver) {
        var users = userService.getUsers();

        var responseBuilder = UserManagement.GetUsersResponse.newBuilder();
        users.forEach(user -> responseBuilder.addUsers(
                UserManagement.GetUserResponse.newBuilder()
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
