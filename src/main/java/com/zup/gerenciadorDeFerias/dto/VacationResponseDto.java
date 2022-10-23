package com.zup.gerenciadorDeFerias.dto;

import com.zup.gerenciadorDeFerias.enumeration.StatusVacationRequest;
import com.zup.gerenciadorDeFerias.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationResponseDto {


    private Long Id;

    private Integer vacationDays;

    private LocalDate startAt;

    private LocalDate endAt;

    private StatusVacationRequest statusVacationRequest;

    //private User user;
}
