package com.zup.gerenciadorDeFerias.controller;

import com.zup.gerenciadorDeFerias.service.VacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/vacationsrequest")
public class VacationRequestController {

    @Autowired
    VacationRequestService vacationRequestService;
}
