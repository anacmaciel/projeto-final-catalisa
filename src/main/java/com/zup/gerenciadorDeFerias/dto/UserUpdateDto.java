package com.zup.gerenciadorDeFerias.dto;

import com.zup.gerenciadorDeFerias.enumeration.ProfileEnum;
import com.zup.gerenciadorDeFerias.enumeration.StatusUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserUpdateDto {

    @Length(min = 5, message = "Error, the number of characters informed must be greater than 5 characters")
    @Length(max = 60, message = "Error, the number of characters informed must be less than or equal to 60 characters")
    private String name;

    @NotNull(message = "Error, the field 'date of birth' was not informed")
    private LocalDate birthDate;

    @NotNull(message = "Error, the field 'hiring date' was not informed")
    private LocalDate hiringDate;

    @Max(value = 30, message = "the maximum amount of days balance is 30")
    private Integer daysBalance;


    @NotNull(message = "Error, the profile field was not informed")
    private ProfileEnum profileEnum;

    @NotNull(message = "Error, the status field was not informed")
    private StatusUser statusUser;

}