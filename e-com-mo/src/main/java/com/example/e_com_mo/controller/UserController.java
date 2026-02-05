package com.example.e_com_mo.controller;

import com.example.e_com_mo.model.User;
import com.example.e_com_mo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

private final UserService userService;


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return  new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){

       return userService.fetchUser(id)
               .map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    private ResponseEntity<String> createUser(@RequestBody User user) {
        userService.addUser(user);

        return ResponseEntity.ok("User added successfully");
    }

    @PutMapping("/{id}")
    private ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {

        boolean updated = userService.updateUser(id, updatedUser);
        if (updated)
            return ResponseEntity.ok("User added successfully");
        return ResponseEntity.notFound().build();

    }
}