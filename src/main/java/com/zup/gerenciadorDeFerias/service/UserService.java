package com.zup.gerenciadorDeFerias.service;

import com.zup.gerenciadorDeFerias.dto.UserRequestDto;
import com.zup.gerenciadorDeFerias.dto.UserResponseDto;
import com.zup.gerenciadorDeFerias.enumeration.StatusUser;
import com.zup.gerenciadorDeFerias.exception.BadRequest;
import com.zup.gerenciadorDeFerias.exception.ObjectNotFoundException;
import com.zup.gerenciadorDeFerias.exception.UnprocessableEntityException;
import com.zup.gerenciadorDeFerias.model.User;
import com.zup.gerenciadorDeFerias.model.VacationRequest;
import com.zup.gerenciadorDeFerias.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> displayRegisteredUsers() {
        return userRepository.findAllStatusActiveOrOnVacation();
    }


    public User displayUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new ObjectNotFoundException("no user with the id {id} was found in the system");
        }

        User userFound = optionalUser.get();
        if (userFound.getStatusUser().equals(StatusUser.INACTIVE)) {
            throw new UnprocessableEntityException("Error, cannot access this user's data");
        }
        return userFound;
    }

    private void checkAge18(LocalDate birthDate) {
        LocalDate localDate = birthDate.plusYears(18);//.plusMonths(12).plusDays(365);
        LocalDate now = LocalDate.now();

        if(localDate.isAfter(now)){
            throw new BadRequest("The informed age is under 18 and is not allowed");
        }

    }

    protected User updateDaysBalancePlus(User user, Integer vacationDays) {
        user.setDaysBalance(user.getDaysBalance() + vacationDays);
        return userRepository.save(user);
    }

    public UserResponseDto registerUser(UserRequestDto userRequestDto) {

        userRequestDto.getEmail();
        Optional<User> optionalUser = userRepository.findByEmail(userRequestDto.getEmail());
        if (optionalUser.isPresent()) {throw new BadRequest("email already exists"); }

       if(userRequestDto.getHiringDate().isAfter(LocalDate.now())){
           throw new BadRequest("Hire date is greater than today's date");
       }

        userRequestDto.getEmail();

        checkAge18(userRequestDto.getBirthDate());

            User user = userRequestDto.convertToUserRequestDto();
            user.setStatusUser(StatusUser.ACTIVE);
            user.setDaysBalance(0);

            User userModel = userRepository.save(user);
            return UserResponseDto.convertToUser(userModel);

    }

    public User changeRegisteredUser(User user, Long id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new ObjectNotFoundException("The informed user was not found in the system");
        }

        optionalUser.get();

        return userRepository.save(user);
    }

    public User updateStatusUser(Long id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new ObjectNotFoundException("User does not exist");
        }
        User user1 = optionalUser.get();
        if (user1.getStatusUser().equals(StatusUser.ACTIVE)) {
            user1.setStatusUser(StatusUser.INACTIVE);
            return userRepository.save(user1);
        } else if (user1.getStatusUser().equals(StatusUser.INACTIVE)) {
            throw new ObjectNotFoundException("User is already inactive");
        }

        return userRepository.save(user);

    }

}
