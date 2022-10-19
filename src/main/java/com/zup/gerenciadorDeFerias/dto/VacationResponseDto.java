package com.zup.gerenciadorDeFerias.dto;

import com.zup.gerenciadorDeFerias.enumeration.StatusVacationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationResponseDto {


    private Long userId;

    private Integer vacationDays;

    private LocalDate starTat;

    private LocalDate endAt;

    private StatusVacationRequest statusVacationRequest;

}
