package com.zup.gerenciadorDeFerias.service;

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
        return vacationRequestRepository.findAll();
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
