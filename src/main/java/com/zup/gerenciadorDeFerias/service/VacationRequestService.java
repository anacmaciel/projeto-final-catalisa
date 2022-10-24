package com.zup.gerenciadorDeFerias.service;

import com.zup.gerenciadorDeFerias.dto.VacationRequestDto;
import com.zup.gerenciadorDeFerias.enumeration.StatusUser;
import com.zup.gerenciadorDeFerias.enumeration.StatusVacationRequest;
import com.zup.gerenciadorDeFerias.exception.ObjectNotFoundException;
import com.zup.gerenciadorDeFerias.exception.UnprocessableEntityException;
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

    public VacationRequest displayVacationRequestById(Long id) {

        Optional<VacationRequest> optionalVacationRequest = vacationRequestRepository.findById(id);
        if (optionalVacationRequest.isEmpty()) {
            throw new ObjectNotFoundException("no request with the id {id} was found in the system");
        }
        VacationRequest vacationRequestFound = optionalVacationRequest.get();
        if (vacationRequestFound.getUser().getStatusUser().equals(StatusUser.INACTIVE)){ //||vacationRequestFound.getUser().getStatusUser().equals(StatusUser.ON_VACATION)
            throw new UnprocessableEntityException("Error, cannot access this user's data");
        }

        return vacationRequestFound;
    }


    public VacationRequest changeRegisteredVacationRequest(VacationRequest vacationRequest) {
        return vacationRequestRepository.save(vacationRequest);
    }

    public VacationRequest cancelRegisteredVacationRequest(VacationRequest vacationRequest, Long id) {
        Optional<VacationRequest> optionalVacationRequest = vacationRequestRepository.findById(id);
        if (optionalVacationRequest.isEmpty()) {
            throw new ObjectNotFoundException("no request with the id {id} was found in the system");
        }

        VacationRequest vacation1 = optionalVacationRequest.get();
        if(vacation1.getStatusVacationRequest().equals(StatusVacationRequest.CREATED)) {
            vacation1.setStatusVacationRequest(StatusVacationRequest.CANCELED);
            vacationRequestRepository.save(vacation1);

        } else if (vacation1.getStatusVacationRequest().equals(StatusVacationRequest.CANCELED)) {
            throw new ObjectNotFoundException("Request is already inactive");
        }

        return vacationRequestRepository.save(vacationRequest);

        // aqui tem uma regra que se cancelado um pedido de ferias:
        // mas cancelado antes de aprovado, nao subtraiu os dias pedidos com o saldo;  para isso teria que ter sido concluido
        // se concluido, ou um pedido aprovado, pode depois ser cancelado?  sim até 7 dias é isso?
        // se era autorizado, tinha subtraido, gerou um novo daysbalance; quando foi cancelado, tem que devolver os dias somando ao days balance
    }




//    public Object changeCharacter(Long id) {
//
//    }
}
