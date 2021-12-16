package com.odhiambopaul.demo.services.imp;

import com.odhiambopaul.demo.model.User;
import com.odhiambopaul.demo.repositories.UserRepository;
import com.odhiambopaul.demo.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        User userFromDb = userRepository.findById(user.getId()).get();
        System.out.println(userFromDb.toString());
        userFromDb.setName(user.getName());
        userFromDb.setPassword(user.getPassword());
        userRepository.save(userFromDb);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
