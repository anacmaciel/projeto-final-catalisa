package com.zup.gerenciadorDeFerias.service;

import com.zup.gerenciadorDeFerias.dto.VacationRequestDto;
import com.zup.gerenciadorDeFerias.dto.VacationResponseDto;
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
import java.util.List;
import java.util.Optional;

@Service
public class VacationRequestService {

    private final Integer rangeOfDay = 45;

    private final Integer itsSevenDays = 7;

    @Autowired
    private VacationRequestRepository vacationRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private boolean validateIfTheDayOfTheWeekIsSaturdayOrSunday(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    private LocalDate checkIfTheRoundTripIsNotABusinessDay(LocalDate date) {
        LocalDate newDate = date;
        if (validateIfTheDayOfTheWeekIsSaturdayOrSunday(newDate)) {
            newDate = newDate.plusDays(1);
            while (validateIfTheDayOfTheWeekIsSaturdayOrSunday(newDate)) {
                newDate = newDate.plusDays(2);
            }
            return newDate;
        }
        return newDate;
    }

    private LocalDate checkReturnToWorkDay(LocalDate startAt, Integer daysVacation) {
        return startAt.plusDays(daysVacation);
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


    private boolean checkHolidayRequestBackground(LocalDate startAt) {
        LocalDate localDate = LocalDate.now().plusDays(rangeOfDay);
        return localDate.isBefore(startAt);
    }


    public VacationResponseDto registerVacationRequest(VacationRequestDto vacationRequestDto) {
        User userFound = checkIfTheUserIsActive(vacationRequestDto.getUser().getId());
        LocalDate validateStartAt = checkIfTheRoundTripIsNotABusinessDay(vacationRequestDto.getStartAt());
        VacationRequest vacationRequest = vacationRequestDto.convertToVacationRequest();
        vacationRequest.setUser(userFound);
        vacationRequest.setStartAt(validateStartAt);
        boolean validDate = checkHolidayRequestBackground(vacationRequest.getStartAt());
        if (validDate) {
            vacationRequest.setEndAt(checkReturnToWorkDay(vacationRequest.getStartAt(), vacationRequest.getVacationDays()));
            LocalDate validEndAt = checkIfTheRoundTripIsNotABusinessDay(vacationRequest.getEndAt());
            vacationRequest.setEndAt(validEndAt);
            vacationRequest.setStatusVacationRequest(StatusVacationRequest.CREATED);
            userService.updateDaysBalance(vacationRequest.getUser(), vacationRequest.getVacationDays());
            VacationRequest vacation = vacationRequestRepository.save(vacationRequest);
            return VacationResponseDto.convertToVacationRequestResponse(vacation);
        } else {
            throw new UnprocessableEntityException("it was not possible to process this request, the request must be made at least " + rangeOfDay + " days in advance");
        }
    }


    public List<VacationRequest> viewRegisteredVacations() {
        return vacationRequestRepository.findAllStatusVacationRequest();
    }

    public VacationRequest displayVacationRequestById(Long id) {

        Optional<VacationRequest> optionalVacationRequest = vacationRequestRepository.findById(id);
        if (optionalVacationRequest.isEmpty()) {
            throw new ObjectNotFoundException("no request with the id {id} was found in the system");
        }
        VacationRequest vacationRequestFound = optionalVacationRequest.get();
        if (vacationRequestFound.getUser().getStatusUser().equals(StatusUser.INACTIVE)) { //||vacationRequestFound.getUser().getStatusUser().equals(StatusUser.ON_VACATION)
            throw new UnprocessableEntityException("Error, cannot access this user's data");
        }

        return vacationRequestFound;
    }

    public VacationRequest changeRegisteredVacationRequest(VacationRequest vacationRequest) {
        return vacationRequestRepository.save(vacationRequest);
    }

    private boolean checkItsSevenDaysBackground(VacationRequest vacationRequest) {
        LocalDate checkStartAt = vacationRequest.getStartAt();
        LocalDate localDate = LocalDate.now().plusDays(itsSevenDays);
        return localDate.isAfter(checkStartAt);
    }

    public void cancelRegisteredVacationRequest(Long id) {
        Optional<VacationRequest> optionalVacationRequest = vacationRequestRepository.findById(id);
        if (optionalVacationRequest.isEmpty()) {
            throw new ObjectNotFoundException("no request with the id {id} was found in the system");
        }

        VacationRequest requestFound = optionalVacationRequest.get();

        boolean validDateSevenDays = checkItsSevenDaysBackground(requestFound);
        if (validDateSevenDays) {
            throw new UnprocessableEntityException(
                    "It is not possible to process your vacation cancellation request, as you must have " + itsSevenDays + " days prior to the start date of the vacation.");
        }

        if (requestFound.getStatusVacationRequest().equals(StatusVacationRequest.CREATED)) {
            requestFound.setStatusVacationRequest(StatusVacationRequest.CANCELED);
            userService.updateDaysBalancePlus(requestFound.getUser(), requestFound.getVacationDays());

            vacationRequestRepository.save(requestFound);

        } else if (requestFound.getStatusVacationRequest().equals(StatusVacationRequest.CANCELED)) {
            throw new ObjectNotFoundException("Request is already canceled");
        }

    }

}
