package com.zup.gerenciadorDeFerias.service;

import com.zup.gerenciadorDeFerias.dto.UserResponseDto;
import com.zup.gerenciadorDeFerias.enumeration.StatusUser;
import com.zup.gerenciadorDeFerias.exception.ObjectNotFoundException;
import com.zup.gerenciadorDeFerias.model.User;
import com.zup.gerenciadorDeFerias.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
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
        return userRepository.findAllStatusUser();
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

    public User updateStatusUser(User user, Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user1 = userOptional.get();
        if (user1.getStatusUser() == StatusUser.ACTIVE) {
            throw new ObjectNotFoundException("Unable to deliver this action");
        } else if (user1.getStatusUser() == StatusUser.ON_VACATION) {
            throw new ObjectNotFoundException("Unable to deliver this action");
        }
        return userRepository.save(user);
    }


//    public Optional<User> changeCharacter(Long id) {
//        return userRepository.findById(id);
//    }
}
