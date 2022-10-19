package com.zup.gerenciadorDeFerias.service;

import com.zup.gerenciadorDeFerias.repository.VacationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacationRequestService {

    @Autowired
    VacationRequestRepository vacationRequestRepository;
}
