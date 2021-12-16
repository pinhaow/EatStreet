package com.odhiambopaul.demo.controllers;

import com.odhiambopaul.demo.model.Todo;
import com.odhiambopaul.demo.model.User;
import com.odhiambopaul.demo.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping({"/{userId}"})
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PostMapping({"/login"})
    public ResponseEntity<User> login(@RequestBody User user) {
        String name = user.getName();
        String password = user.getPassword();
        try {
            User found = userService.getUsers().stream().filter(u -> name.equals(u.getName()) && password.equals(u.getPassword())).findFirst().get();
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(found);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User user1 = userService.insert(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("user", "/api/v1/user/" + user1.getId().toString());
        return new ResponseEntity<>(user1, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(userService.getUserById(user.getId()), HttpStatus.OK);
    }

    @DeleteMapping({"/{userId}"})
    public ResponseEntity<Todo> deleteTodo(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
