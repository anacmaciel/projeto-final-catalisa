package com.zup.gerenciadorDeFerias.service;

import com.zup.gerenciadorDeFerias.enumeration.StatusUser;
import com.zup.gerenciadorDeFerias.exception.ObjectNotFoundException;
import com.zup.gerenciadorDeFerias.model.User;
import com.zup.gerenciadorDeFerias.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public List<User> displayRegisteredUsers() {

        return userRepository.findAllSTATUSUSER();
    }


    public Optional<User> displayUserById(Long id) {
        return userRepository.findById(id);
    }



    public User changeRegisteredUser(User user, Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new ObjectNotFoundException("The informed user was not found in the system");
        }
        return userRepository.save(user);
    }


//    public Optional<User> changeCharacter(Long id) {
//        return userRepository.findById(id);
//    }
}
