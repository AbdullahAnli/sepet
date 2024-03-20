package com.sepet.controller;

import com.sepet.entity.User;
import com.sepet.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User>getAllUser(){
        return userService.getAllUser();
    }
    @GetMapping("/{id}")
    public Optional<User> getByUserID(@PathVariable Long id){
        return userService.getByUserId(id);
    }
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @DeleteMapping
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }



}
