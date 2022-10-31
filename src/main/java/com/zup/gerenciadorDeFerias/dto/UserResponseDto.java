package com.zup.gerenciadorDeFerias.dto;

import com.zup.gerenciadorDeFerias.enumeration.ProfileEnum;
import com.zup.gerenciadorDeFerias.enumeration.StatusUser;
import com.zup.gerenciadorDeFerias.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserResponseDto {
    private Long id;

    private String username;

    private String email;

    private LocalDate birthDate;

    private LocalDate hiringDate;

    private Integer daysBalance;

    private ProfileEnum profileEnum;

    private StatusUser statusUser;

    private String login;


    private String senha;


    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.birthDate = user.getBirthDate();
        this.hiringDate = user.getHiringDate();
        this.daysBalance = user.getDaysBalance();
        this.profileEnum = user.getProfileEnum();
        this.statusUser = user.getStatusUser();
        this.login = user.getLogin();
        this.senha = user.getSenha();
    }

    public static UserResponseDto convertToUser(User user) {

        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(),
                user.getBirthDate(), user.getHiringDate(), user.getDaysBalance(),
                user.getProfileEnum(), user.getStatusUser(), user.getLogin(),user.getSenha());

    }
}
