package com.zup.gerenciadorDeFerias.controller;

import com.zup.gerenciadorDeFerias.dto.UserRequestDto;
import com.zup.gerenciadorDeFerias.dto.UserResponseDto;
import com.zup.gerenciadorDeFerias.dto.UserUpdateDto;
import com.zup.gerenciadorDeFerias.model.User;
import com.zup.gerenciadorDeFerias.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping

    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.registerUser(userRequestDto);
        return new ResponseEntity(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> displayRegisteredUsers() {
        return ResponseEntity.ok(userService.displayRegisteredUsers());
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<User> displayUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.displayUserById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponseDto> updateRegisteredUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(userService.changeRegisteredUser(id, userUpdateDto));
    }

    @DeleteMapping(path = "/inactive/{id}")
    public void userInactiveStatus(@PathVariable Long id) {
        userService.updateStatusUser(id);

    }
}
