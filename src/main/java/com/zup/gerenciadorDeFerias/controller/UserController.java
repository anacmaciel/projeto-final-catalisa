package com.zup.gerenciadorDeFerias.controller;

import com.zup.gerenciadorDeFerias.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserService userService;
}
