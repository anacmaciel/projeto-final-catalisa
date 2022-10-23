package com.zup.gerenciadorDeFerias.controller;

import com.zup.gerenciadorDeFerias.dto.VacationRequestDto;
import com.zup.gerenciadorDeFerias.model.VacationRequest;
import com.zup.gerenciadorDeFerias.service.VacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<VacationRequest> registerVacationRequest(@Valid @RequestBody VacationRequest vacationRequest) {
        return ResponseEntity.ok(vacationRequestService.registerVacationRequest(vacationRequest));
    }

    @GetMapping
    public ResponseEntity<List<VacationRequest>> viewRegisteredVacations() {
        return ResponseEntity.ok(vacationRequestService.viewRegisteredVacations());
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<VacationRequestDto>> displayVacationRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(vacationRequestService.displayVacationRequestById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<VacationRequest> changeRegisteredVacationRequest(@RequestBody VacationRequest vacationRequest) {
        return ResponseEntity.ok(vacationRequestService.changeRegisteredVacationRequest(vacationRequest));
    }

//    @PatchMapping(path = "/{id}")
//    public ResponseEntity<VacationRequest> changeCharacter(@PathVariable Long id){
//        return ResponseEntity.ok(vacationRequestService.changeCharacter(id));
//    }

}
