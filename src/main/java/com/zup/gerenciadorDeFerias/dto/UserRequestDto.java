package com.zup.gerenciadorDeFerias.dto;

import com.zup.gerenciadorDeFerias.enumeration.ProfileEnum;
import com.zup.gerenciadorDeFerias.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class UserRequestDto {

    @Length(min = 5, message = "Error, the number of characters informed must be greater than 5 characters")
    @Length(max = 60, message = "Error, the number of characters informed must be less than or equal to 60 characters")
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


    public User convertToUserRequestDto() {
        return new User(name, email, birthDate, hiringDate, profileEnum);
    }
}
