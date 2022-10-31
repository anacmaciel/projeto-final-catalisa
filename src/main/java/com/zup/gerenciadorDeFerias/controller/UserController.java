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


    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {
       try {
           UserResponseDto userResponseDto = userService.registerUser(userRequestDto);
           return new ResponseEntity(userResponseDto, HttpStatus.CREATED);
       }catch (Exception e){
           return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping
    public ResponseEntity<List<User>> displayRegisteredUsers() {
        return ResponseEntity.ok(userService.displayRegisteredUsers());
    }


    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping(path = "/{id}")
    public ResponseEntity<User> displayUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.displayUserById(id));
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponseDto> updateRegisteredUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(userService.changeRegisteredUser(id, userUpdateDto));
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @DeleteMapping(path = "/inactive/{id}")
    public void userInactiveStatus(@PathVariable Long id) {
        userService.updateStatusUser(id);

    }
}
