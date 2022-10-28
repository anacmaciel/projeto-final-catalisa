package com.zup.gerenciadorDeFerias.controller;

import com.zup.gerenciadorDeFerias.dto.VacationRequestDto;
import com.zup.gerenciadorDeFerias.dto.VacationResponseDto;
import com.zup.gerenciadorDeFerias.model.VacationRequest;
import com.zup.gerenciadorDeFerias.service.VacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/vacationsrequest")
public class VacationRequestController {

    @Autowired
    VacationRequestService vacationRequestService;

    @PostMapping
    public ResponseEntity<VacationResponseDto> registerVacationRequest(@Valid @RequestBody VacationRequestDto vacationRequestDto) {
        VacationResponseDto vacationResponseDto = vacationRequestService.registerVacationRequest(vacationRequestDto);
        return new ResponseEntity<>(vacationResponseDto, HttpStatus.CREATED);
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
    public ResponseEntity<VacationResponseDto> changeRegisteredVacationRequest(@RequestBody Long id, VacationRequest vacationRequest) {
        return ResponseEntity.ok(vacationRequestService.changeRegisteredVacationRequest(id, vacationRequest));
    }

    @DeleteMapping(path = "/cancel/{id}")
    public void cancelRegisteredVacationRequest(@PathVariable Long id) {
        vacationRequestService.cancelRegisteredVacationRequest(id);
    }

}
