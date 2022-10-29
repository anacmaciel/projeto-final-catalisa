package com.zup.gerenciadorDeFerias.controller;

import com.zup.gerenciadorDeFerias.dto.VacationRequestDto;
import com.zup.gerenciadorDeFerias.dto.VacationResponseDto;
import com.zup.gerenciadorDeFerias.dto.VacationUpdateDto;
import com.zup.gerenciadorDeFerias.model.VacationRequest;
import com.zup.gerenciadorDeFerias.service.VacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class VacationRequestController {

    @Autowired
    VacationRequestService vacationRequestService;

    @PostMapping(path = "/user/{id}/vacationsrequest")
    public ResponseEntity<VacationResponseDto> registerVacationRequest(@PathVariable Long id, @RequestBody @Valid VacationRequestDto vacationRequestDto) {
        VacationResponseDto vacationResponseDto = vacationRequestService.registerVacationRequest(id, vacationRequestDto);
        return new ResponseEntity<>(vacationResponseDto, HttpStatus.CREATED);
    }

    @GetMapping(path = "/vacationsrequest")
    public ResponseEntity<List<VacationRequest>> viewRegisteredVacations() {
        return ResponseEntity.ok(vacationRequestService.viewRegisteredVacations());
    }

    @GetMapping(path = "/vacationsrequest/{id}")
    public ResponseEntity<VacationRequest> displayVacationRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(vacationRequestService.displayVacationRequestById(id));
    }

    @PutMapping(path = "/vacationsrequest/{id}")
    public ResponseEntity<VacationResponseDto> changeRegisteredVacationRequest(@PathVariable Long id, @RequestBody @Valid VacationUpdateDto vacationUpdateDto) {
        return ResponseEntity.ok(vacationRequestService.changeRegisteredVacationRequest(id, vacationUpdateDto));
    }

    @DeleteMapping(path = "/vacationsrequest/cancel/{id}")
    public void cancelRegisteredVacationRequest(@PathVariable Long id) {
        vacationRequestService.cancelRegisteredVacationRequest(id);
    }
}
