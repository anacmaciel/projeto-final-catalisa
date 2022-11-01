package com.zup.gerenciadorDeFerias.dto;

import com.zup.gerenciadorDeFerias.enumeration.StatusVacationRequest;
import com.zup.gerenciadorDeFerias.model.User;
import com.zup.gerenciadorDeFerias.model.VacationRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class VacationResponseDto {
    private Long id;

    private Integer vacationDays;

    private LocalDate startAt;

    private LocalDate endAt;

    private StatusVacationRequest statusVacationRequest;

    private User user;

    public VacationResponseDto(VacationRequest vacationRequest) {
        this.id = vacationRequest.getId();
        this.vacationDays = vacationRequest.getVacationDays();
        this.startAt = vacationRequest.getStartAt();
        this.endAt = vacationRequest.getEndAt();
        this.statusVacationRequest = vacationRequest.getStatusVacationRequest();
        this.user = vacationRequest.getUser();
    }

    public static VacationResponseDto convertToVacationRequestResponse(VacationRequest vacationRequest) {

        return new VacationResponseDto(vacationRequest.getId(), vacationRequest.getVacationDays(), vacationRequest.getStartAt(), vacationRequest.getEndAt(), vacationRequest.getStatusVacationRequest(), vacationRequest.getUser());
    }

}
