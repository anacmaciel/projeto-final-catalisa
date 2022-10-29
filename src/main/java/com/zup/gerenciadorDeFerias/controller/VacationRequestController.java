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
@RequestMapping(path = "/vacationsrequest")
public class VacationRequestController {

    @Autowired
    VacationRequestService vacationRequestService;

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping
    public ResponseEntity<VacationResponseDto> registerVacationRequest(@Valid @RequestBody VacationRequestDto vacationRequestDto) {
        VacationResponseDto vacationResponseDto = vacationRequestService.registerVacationRequest(vacationRequestDto);
        return new ResponseEntity<>(vacationResponseDto, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping
    public ResponseEntity<List<VacationRequest>> viewRegisteredVacations() {
        return ResponseEntity.ok(vacationRequestService.viewRegisteredVacations());
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping(path = "/{id}")
    public ResponseEntity<VacationRequest> displayVacationRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(vacationRequestService.displayVacationRequestById(id));
    }


    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PutMapping(path = "/{id}")
    public ResponseEntity<VacationResponseDto> changeRegisteredVacationRequest(@PathVariable Long id, @RequestBody @Valid VacationUpdateDto vacationUpdateDto) {
        return ResponseEntity.ok(vacationRequestService.changeRegisteredVacationRequest(id, vacationUpdateDto));
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @DeleteMapping(path = "/cancel/{id}")
    public void cancelRegisteredVacationRequest(@PathVariable Long id) {
        vacationRequestService.cancelRegisteredVacationRequest(id);
    }

}
