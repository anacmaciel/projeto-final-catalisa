package com.zup.gerenciadorDeFerias.dto;

import com.zup.gerenciadorDeFerias.enumeration.ProfileEnum;
import com.zup.gerenciadorDeFerias.enumeration.StatusUser;
import com.zup.gerenciadorDeFerias.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
@NoArgsConstructor
@Getter
public class UserRequestDto {
    @NotBlank(message = "Name not informed error")
    @Min(value = 5, message = "Error, the number of characters informed must be greater than 5 characters")
    @Max(value = 60, message = "Error, the number of characters informed must be less than or equal to 60 characters")
    private String name;

    @Email(message = "Error, invalid email")
    @NotBlank(message = "Error, 'email' field not informed")
    private String email;

    @NotNull(message = "Error, the field 'date of birth' was not informed")
    private LocalDate birthDate;

    @NotNull(message = "Error, the field 'hiring date' was not informed")
    private LocalDate hiringDate;
    @NotNull(message = "Error, the profile field was not informed")
    private ProfileEnum profileEnum;

private StatusUser statusUser;

public User convertToUserRequestDto() {
    return new User(name, email, birthDate, hiringDate, profileEnum, statusUser);
}
}
