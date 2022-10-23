package com.zup.gerenciadorDeFerias.service;

import com.zup.gerenciadorDeFerias.dto.VacationRequestDto;
import com.zup.gerenciadorDeFerias.model.VacationRequest;
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

    @Autowired
    VacationRequestRepository vacationRequestRepository;


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

    public VacationRequest registerVacationRequest(VacationRequestDto vacationRequestDto) {

        LocalDate validatStartAt = checkIfTheRoundTripIsNotABusinessDay(vacationRequestDto.getStartAt());
        LocalDate validEndAt = checkIfTheRoundTripIsNotABusinessDay(vacationRequestDto.getEndAt());

        VacationRequest vacationRequest = vacationRequestDto.convertToVacationRequest();

        vacationRequest.setStartAt(validatStartAt);
        vacationRequest.setEndAt(validEndAt);

        return vacationRequestRepository.save(vacationRequest);
    }

    public List<VacationRequest> viewRegisteredVacations() {
        return vacationRequestRepository.findAllStatusVacationRequest();
    }



    //se usuario está com status INACTIVE nao pode fazer buscas - atraves do login, checar se esta ativo, se estiver inativo, desabilita o botao de pequisa
    //usuario nao pode ter acesso a nenhum outro usuario - logica seria via seu acesso, Login/passorwd ja setar esse id nao deixar ele setar o id
    public Optional<VacationRequestDto> displayVacationRequestById(Long id) {

        VacationRequest vacationRequest= vacationRequestRepository.findById(id).get();
        VacationRequestDto objDto = new VacationRequestDto(
                vacationRequest.getVacationDays(),vacationRequest.getStartAt(),vacationRequest.getEndAt(),
                vacationRequest.getStatusVacationRequest(), vacationRequest.getUser());
        return Optional.of(objDto);

      // return vacationRequestRepository.findById(id);

    }

    public VacationRequest changeRegisteredVacationRequest(VacationRequest vacationRequest) {
        return vacationRequestRepository.save(vacationRequest);
    }

//    public Object changeCharacter(Long id) {
//
//    }
}
