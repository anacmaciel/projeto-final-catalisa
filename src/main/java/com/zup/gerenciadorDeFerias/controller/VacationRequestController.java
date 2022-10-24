package com.zup.gerenciadorDeFerias.controller;

import com.zup.gerenciadorDeFerias.dto.VacationRequestDto;
import com.zup.gerenciadorDeFerias.model.VacationRequest;
import com.zup.gerenciadorDeFerias.service.VacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/vacationsrequest")
public class VacationRequestController {

    @Autowired
    VacationRequestService vacationRequestService;

    @PostMapping
    public ResponseEntity<VacationRequest> registerVacationRequest(@Valid @RequestBody VacationRequestDto vacationRequestDto) {
        VacationRequest vacationRequest = vacationRequestService.registerVacationRequest(vacationRequestDto);
        return new ResponseEntity<>(vacationRequest, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VacationRequest>> viewRegisteredVacations() {
        return ResponseEntity.ok(vacationRequestService.viewRegisteredVacations());
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<VacationRequest> displayVacationRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(vacationRequestService.displayVacationRequestById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<VacationRequest> changeRegisteredVacationRequest(@RequestBody VacationRequest vacationRequest) {
        return ResponseEntity.ok(vacationRequestService.changeRegisteredVacationRequest(vacationRequest));
    }

    @PutMapping(path = "/inativar/{id}")
    public ResponseEntity<VacationRequest> cancelRegisteredVacationRequest(@RequestBody VacationRequest vacationRequest) {
        return ResponseEntity.ok(vacationRequestService.changeRegisteredVacationRequest(vacationRequest));
    }

//    @PatchMapping(path = "/{id}")
//    public ResponseEntity<VacationRequest> changeCharacter(@PathVariable Long id){
//        return ResponseEntity.ok(vacationRequestService.changeCharacter(id));
//    }

}
