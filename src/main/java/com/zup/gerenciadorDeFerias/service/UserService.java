package com.zup.gerenciadorDeFerias.service;

import com.zup.gerenciadorDeFerias.dto.UserRequestDto;
import com.zup.gerenciadorDeFerias.dto.UserResponseDto;
import com.zup.gerenciadorDeFerias.enumeration.StatusUser;
import com.zup.gerenciadorDeFerias.exception.BadRequest;
import com.zup.gerenciadorDeFerias.exception.ObjectNotFoundException;
import com.zup.gerenciadorDeFerias.model.User;
import com.zup.gerenciadorDeFerias.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    private boolean checkAge18(LocalDate birthDate) {
     LocalDate localDate=  birthDate.plusYears(18);
        LocalDate now = LocalDate.now();
        return localDate.isBefore(now);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }


    public UserResponseDto registerUser(UserRequestDto userRequestDto){

       if(userRequestDto.getHiringDate().isAfter(LocalDate.now())){
           throw new BadRequest("Data de contratação é maior que data de hoje");
       }


        Boolean validationAge = checkAge18(userRequestDto.getBirthDate());
        if (validationAge) {

            User user = userRequestDto.convertToUserRequestDto();
            user.setStatusUser(StatusUser.ACTIVE);
            user.setDaysBalance(0);

            User userModel = userRepository.save(user);
            return UserResponseDto.convertToUser(userModel);

        } else {
            throw new BadRequest("Idade menor que 18 anos não é permitido");
        }

        }

        public List<User> displayRegisteredUsers() {
            return userRepository.findAllStatusUser();
        }


        public Optional<User> displayUserById (Long id){
            return userRepository.findById(id);
        }


        public User changeRegisteredUser (User user, Long id){

            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isEmpty()) {
                throw new RuntimeException("The informed user was not found in the system");
            }
            return userRepository.save(user);
        }

        public User updateStatusUser (User user, Long id){
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

    //Boolean validationHiring = isDatefuture(userRequestDto.getHiringDate());

//    /    private boolean isDatefuture(LocalDate hiringDate){return hiringDate.isAfter(LocalDate.now());
//    }


    }
