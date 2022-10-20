package com.zup.gerenciadorDeFerias.service;

import com.zup.gerenciadorDeFerias.dto.UserRequestDto;
import com.zup.gerenciadorDeFerias.dto.UserResponseDto;
import com.zup.gerenciadorDeFerias.enumeration.StatusUser;
import com.zup.gerenciadorDeFerias.model.User;
import com.zup.gerenciadorDeFerias.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public boolean checkAge18(User user){
        LocalDate date = user.getBirthDate().plusYears(18);
        LocalDate now = LocalDate.now();
        return date.isBefore(now);
    }


    public UserResponseDto registerUser(User user) {

        Boolean validationAge = checkAge18(user);

        if (validationAge){
            userRepository.save(user);

        UserResponseDto userResponseDto =new UserResponseDto(user.getName(),user.getEmail(),user.getBirthDate(),
                user.getHiringDate(), user.getDaysBalance(),
                user.getProfileEnum(),user.setStatusUser(StatusUser.ACTIVE));
        return userResponseDto;
        }else {
            return null;
        }

    public List<User> displayRegisteredUsers() {
        return userRepository.findAll();
    }


    public Optional<User> displayUsersById(Long id) {
        return userRepository.findById(id);
    }



    public User changeRegisteredUsers(User user) {
        return userRepository.save(user);
    }



//    public Optional<User> changeCharacter(Long id) {
//        return userRepository.findById(id);
//    }
}
