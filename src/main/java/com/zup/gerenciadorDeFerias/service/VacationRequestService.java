package com.zup.gerenciadorDeFerias.service;

import com.zup.gerenciadorDeFerias.dto.VacationRequestDto;
//import com.zup.gerenciadorDeFerias.dto.VacationResponseDto;
import com.zup.gerenciadorDeFerias.model.VacationRequest;
import com.zup.gerenciadorDeFerias.repository.VacationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacationRequestService {

    @Autowired
    VacationRequestRepository vacationRequestRepository;

    public VacationRequest registerVacationRequest(VacationRequest vacationRequest) {
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
