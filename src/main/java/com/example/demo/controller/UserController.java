package com.example.demo.controller;

import com.example.demo.model.UserEntity;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping(produces = "application/json")
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public UserEntity getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity userEntity) {
        UserEntity createdUserEntity = userService.addUser(userEntity);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUserEntity.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdUserEntity);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity userEntityDetails) {
        userService.updateUser(id, userEntityDetails);
        return ResponseEntity.noContent().build();
    }
}
