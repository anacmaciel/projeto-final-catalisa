package com.zup.gerenciadorDeFerias.controller;

import com.zup.gerenciadorDeFerias.model.User;
import com.zup.gerenciadorDeFerias.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> displayRegisteredUsers() {
        return ResponseEntity.ok(userService.displayRegisteredUsers());
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<User>> displayUsersById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.displayUsersById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<User> changeRegisteredUsers(@RequestBody User user) {
        return ResponseEntity.ok(userService.changeRegisteredUsers(user));
    }

//    @PatchMapping(path = "/{id}")
//    public ResponseEntity<User> changeCharacter(@PathVariable Long id){
//        return ResponseEntity.ok(userService.changeCharacter(id));
//    }


}
