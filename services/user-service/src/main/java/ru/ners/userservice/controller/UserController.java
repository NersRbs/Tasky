package ru.ners.userservice.controller;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.ners.service.UserManagement;
import ru.ners.service.UserServiceGrpc;
import ru.ners.userservice.model.User;
import ru.ners.userservice.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController extends UserServiceGrpc.UserServiceImplBase {
    private final UserService userService;

    @Override
    public void saveUser(UserManagement.UserRequest request, StreamObserver<Empty> responseObserver) {
        
    }

    @Override
    public void getUser(UserManagement.UserRequest request, StreamObserver<UserManagement.UserResponse> responseObserver) {
        super.getUser(request, responseObserver);
    }

    @Override
    public void getUsers(Empty request, StreamObserver<UserManagement.UsersResponse> responseObserver) {
        super.getUsers(request, responseObserver);
    }
}
