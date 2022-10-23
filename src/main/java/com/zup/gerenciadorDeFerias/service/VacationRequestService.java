package com.zup.gerenciadorDeFerias.service;

import com.zup.gerenciadorDeFerias.dto.VacationRequestDto;
import com.zup.gerenciadorDeFerias.enumeration.StatusUser;
import com.zup.gerenciadorDeFerias.enumeration.StatusVacationRequest;
import com.zup.gerenciadorDeFerias.exception.ObjectNotFoundException;
import com.zup.gerenciadorDeFerias.exception.UnprocessableEntityException;
import com.zup.gerenciadorDeFerias.model.User;
import com.zup.gerenciadorDeFerias.model.VacationRequest;
import com.zup.gerenciadorDeFerias.repository.UserRepository;
import com.zup.gerenciadorDeFerias.repository.VacationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class VacationRequestService {

    private Integer rangeOfDays = 45;

    @Autowired
    VacationRequestRepository vacationRequestRepository;
    @Autowired
    UserRepository userRepository;

    private boolean validateIfTheDayOfTheWeekIsSaturdayOrSunday(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    private LocalDate checkIfTheRoundTripIsNotABusinessDay(LocalDate date) {
        LocalDate newDate = date.plus(2, ChronoUnit.DAYS);
        while (validateIfTheDayOfTheWeekIsSaturdayOrSunday(newDate)) {
            newDate = newDate.plus(1, ChronoUnit.DAYS);
        }

        return newDate;
    }

    private LocalDate checkReturnToWorkDay(LocalDate startAt, Integer daysVacation) {
        return startAt.plus(daysVacation, ChronoUnit.DAYS);
    }

    private User checkIfTheUserIsActive(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new ObjectNotFoundException("no user with this id was found in the system");
        }
        User userFound = optionalUser.get();
        if (!userFound.getStatusUser().equals(StatusUser.ACTIVE)) {
            throw new UnprocessableEntityException("unable to process this request");
        }
        return userFound;
    }

    public VacationRequest registerVacationRequest(VacationRequestDto vacationRequestDto) {
        checkIfTheUserIsActive(vacationRequestDto.getUser().getId());
          LocalDate validateStartAt = checkIfTheRoundTripIsNotABusinessDay(vacationRequestDto.getStartAt());
        VacationRequest vacationRequest = vacationRequestDto.convertToVacationRequest();
        vacationRequest.setUser(vacationRequest.getUser());
        vacationRequest.setStartAt(validateStartAt);
        vacationRequest.setEndAt(checkReturnToWorkDay(vacationRequest.getStartAt(), vacationRequest.getVacationDays()));
        LocalDate validEndAt = checkIfTheRoundTripIsNotABusinessDay(vacationRequest.getEndAt());
        vacationRequest.setEndAt(validEndAt);
        vacationRequest.setStatusVacationRequest(StatusVacationRequest.CREATED);
        return vacationRequestRepository.save(vacationRequest);
    }


    private VacationRequest checkHolidayRequestBackground(LocalDate startAt) {
        if (startAt >= rangeOfDays.)
    }

    public List<VacationRequest> viewRegisteredVacations() {
        return vacationRequestRepository.findAllStatusVacationRequest();
    }

    public Optional<VacationRequest> displayVacationRequestById(Long id) {
        return vacationRequestRepository.findById(id);
    }

    public VacationRequest changeRegisteredVacationRequest(VacationRequest vacationRequest) {
        return vacationRequestRepository.save(vacationRequest);
    }

//    public Object changeCharacter(Long id) {
//
//    }
}
