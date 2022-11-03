package com.zup.gerenciadorDeFerias.service;

import com.zup.gerenciadorDeFerias.dto.UserRequestDto;
import com.zup.gerenciadorDeFerias.dto.UserResponseDto;
import com.zup.gerenciadorDeFerias.dto.UserUpdateDto;
import com.zup.gerenciadorDeFerias.enumeration.StatusUser;
import com.zup.gerenciadorDeFerias.exception.BadRequest;
import com.zup.gerenciadorDeFerias.exception.ObjectNotFoundException;
import com.zup.gerenciadorDeFerias.exception.UnprocessableEntityException;
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

    protected User checkIfTheUserIsActive(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new ObjectNotFoundException("No user with this id was found in the system");
        }
        User userFound = optionalUser.get();
        if (!userFound.getStatusUser().equals(StatusUser.ACTIVE)) {
            throw new UnprocessableEntityException("Unable to process this request");
        }
        return userFound;
    }

    protected User checkUserActiveByEmail(String email) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new ObjectNotFoundException("No user with this id was found in the system");
        }

        User userFound = optionalUser.get();

        if (!userFound.getStatusUser().equals(StatusUser.ACTIVE)) {
            throw new UnprocessableEntityException("Unable to process this request");
        }
        return userFound;
    }


    public List<User> displayRegisteredUsers() {
        return userRepository.findAllStatusActiveOrOnVacation();
    }


    protected void updateDaysBalance(User user, Integer vacationDays) {
        user.setDaysBalance(user.getDaysBalance() - vacationDays);

        userRepository.save(user);
    }

    public User displayUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
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

        if (localDate.isAfter(now)) {
            throw new BadRequest("The informed age is under 18 and is not allowed");
        }

    }

    protected void updateDaysBalancePlus(User user, Integer vacationDays) {
        user.setDaysBalance(user.getDaysBalance() + vacationDays);
        userRepository.save(user);
    }

    public UserResponseDto registerUser(UserRequestDto userRequestDto) throws Exception{
        Optional<User> optionalUser = userRepository.findByEmail(userRequestDto.getEmail());
        if (optionalUser.isPresent()) {
            throw new BadRequest("Email already exists");
        }

        if (userRequestDto.getHiringDate().isAfter(LocalDate.now())) {
            throw new BadRequest("Hire date is greater than today's date");
        }

        checkAge18(userRequestDto.getBirthDate());

        User user = userRequestDto.convertToUserRequestDto();
        user.setStatusUser(StatusUser.ACTIVE);
        user.setDaysBalance(0);

        User userModel = userRepository.save(user);
        return UserResponseDto.convertToUser(userModel);
    }

    public UserResponseDto changeRegisteredUser(Long id, UserUpdateDto userUpdateDto){
        User userFound = checkIfTheUserIsActive(id);
        if (userUpdateDto.getHiringDate().isAfter(LocalDate.now())) {
            throw new BadRequest("Hire date is greater than today's date");
        }

        checkAge18(userUpdateDto.getBirthDate());
        userFound.setName(userUpdateDto.getName());
        userFound.setHiringDate(userUpdateDto.getHiringDate());
        userFound.setBirthDate(userUpdateDto.getBirthDate());
        userFound.setStatusUser(userUpdateDto.getStatusUser());
        userFound.setProfileEnum(userUpdateDto.getProfileEnum());
        userFound.setDaysBalance(userUpdateDto.getDaysBalance());
        User userModel = userRepository.save(userFound);
        return UserResponseDto.convertToUser(userModel);
    }

    public void updateStatusUser(String email) {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new ObjectNotFoundException("User does not exist");
        }

        User user1 = optionalUser.get();
        if (user1.getStatusUser().equals(StatusUser.ACTIVE) && (!user1.getStatusUser().equals(StatusUser.ON_VACATION))) {
            user1.setStatusUser(StatusUser.INACTIVE);
            userRepository.save(user1);
        } else if (user1.getStatusUser().equals(StatusUser.INACTIVE)) {
            throw new ObjectNotFoundException("User is already inactive");
        }
    }

}
