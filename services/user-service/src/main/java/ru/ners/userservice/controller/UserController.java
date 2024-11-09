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
                .username(request.getUsername())
                .passwordHash(request.getPassword())
                .contacts(request.getContactsMap())
                .build();

        userService.saveUser(user);
    }

    @Override
    public void getUser(UserManagement.GetUserRequest request, StreamObserver<UserManagement.GetUserResponse> responseObserver) {
        var user = userService.getUser(request.getId());

        var response = UserManagement.GetUserResponse.newBuilder()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPasswordHash(user.getPasswordHash())
                .putAllContacts(user.getContacts())
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
                        .setUsername(user.getUsername())
                        .setPasswordHash(user.getPasswordHash())
                        .putAllContacts(user.getContacts())
                        .build()
                )
        );

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}
