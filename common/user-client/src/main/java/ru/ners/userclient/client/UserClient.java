package ru.ners.userclient.client;

import ru.ners.usermanagement.model.UserMessage;

public interface UserClient {
    void addUser(UserMessage.AddUserRequest user);

    UserMessage.GetUserResponse getUserByName(UserMessage.GetUserRequest request);

    UserMessage.GetUsersResponse getUsers();
}
