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
        return ResponseEntity.ok(userService.displayRegisteredUser());
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<User>> displayUsersById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.displayUserById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<User> updateRegisteredUsers(@Valid @RequestBody User user, @PathVariable Long id) {
        return ResponseEntity.ok(userService.changeRegisteredUser(user, id));
    }


    @PutMapping(path = "/userstatus/{id}")
    public ResponseEntity<User> userInactiveStatus(@Valid @RequestBody User user, @PathVariable Long id ){
        return ResponseEntity.ok(userService.updateStatusUser(user, id));
    }



}
