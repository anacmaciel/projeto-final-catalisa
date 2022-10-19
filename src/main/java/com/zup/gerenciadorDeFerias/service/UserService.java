package com.zup.gerenciadorDeFerias.service;

import com.zup.gerenciadorDeFerias.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
}
