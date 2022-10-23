package com.zup.gerenciadorDeFerias.dto;

import com.zup.gerenciadorDeFerias.enumeration.StatusVacationRequest;
import com.zup.gerenciadorDeFerias.model.User;
import com.zup.gerenciadorDeFerias.model.VacationRequest;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class VacationResponseDto {


    private Integer vacationDays;

    private LocalDate starTat;

    private LocalDate endAt;

    private StatusVacationRequest statusVacationRequest;
    private User user;

    public VacationResponseDto(VacationRequest vacationRequest) {
        this.vacationDays = vacationRequest.getVacationDays();
        this.starTat = vacationRequest.getStartAt();
        this.endAt = vacationRequest.getEndAt();
        this.statusVacationRequest = vacationRequest.getStatusVacationRequest();
        this.user = vacationRequest.getUser();
    }

    public static VacationResponseDto convertToVacationRequestResponse(VacationRequest vacationRequest) {

        return new VacationResponseDto(vacationRequest.getVacationDays(), vacationRequest.getStartAt(), vacationRequest.getEndAt(), vacationRequest.getStatusVacationRequest(), vacationRequest.getUser());
    }
}
