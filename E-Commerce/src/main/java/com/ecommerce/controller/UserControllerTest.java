package com.ecommerce.controller;

import com.ecommerce.entity.User;
import com.ecommerce.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserControllerTest {

    private final UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("User API is running...");
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody @Valid User user) {
        User savedUser = userService.registerUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody @Valid User user) {
        userService.removeUser(user);
        return new ResponseEntity<>(
                "user deleted: " + user.getUserid(),
                HttpStatus.OK
        );
    }

    @PutMapping("/update/{userid}")
    public ResponseEntity<User> deleteUser(@PathVariable String userid, @RequestBody User user) {

        return new ResponseEntity<>(
                userService.updateUser(userid, user),
                HttpStatus.OK
        );
    }

    @GetMapping("/signin")
    public ResponseEntity<Boolean> signIn(@RequestBody @Valid User user) {

        return new ResponseEntity<>(
                userService.isAuthenticated(user),
                HttpStatus.OK
        );
    }
}