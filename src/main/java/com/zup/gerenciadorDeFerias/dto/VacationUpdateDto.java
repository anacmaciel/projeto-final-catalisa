package com.zup.gerenciadorDeFerias.dto;

import com.zup.gerenciadorDeFerias.enumeration.StatusVacationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Validated

public class VacationUpdateDto {

    @NotNull(message = "Error: the field 'vacation days' was not informed")
    @Min(value = 5, message = "error, it is not possible to request less than five days of vacation")
    @Max(value = 30, message = "error, it is not possible to request more than thirty days of vacation")
    private Integer vacationDays;

    @NotNull(message = "Error: the 'holiday start' field was not informed")
    private LocalDate startAt;

    @NotNull(message = "field 'statusRequestVacation' cannot be empty")
    private StatusVacationRequest statusVacationRequest;

}
