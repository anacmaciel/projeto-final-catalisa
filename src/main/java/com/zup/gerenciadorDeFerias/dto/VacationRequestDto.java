package com.zup.gerenciadorDeFerias.dto;

import com.zup.gerenciadorDeFerias.enumeration.StatusVacationRequest;
import com.zup.gerenciadorDeFerias.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated

public class VacationRequestDto {

    @NotNull(message = "Error: the field 'vacation days' was not informed")
    private Integer vacationDays;

    @NotNull(message = "Error: the 'holiday start' field was not informed")
    private LocalDate startAt;

    @NotNull(message = "Error: the field 'back from vacation' was not informed")
    private LocalDate endAt;

    @NotNull(message = "Error: 'StatusVocationRequest' field was not informed")
    private StatusVacationRequest statusVacationRequest;


    private User user;
}
