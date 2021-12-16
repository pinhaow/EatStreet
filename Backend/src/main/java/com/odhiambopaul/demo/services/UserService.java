package com.odhiambopaul.demo.services;

import com.odhiambopaul.demo.model.Todo;
import com.odhiambopaul.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUserById(Long userId);

    User insert(User user);

    void updateUser(User user);

    void deleteUser(Long userId);
}
