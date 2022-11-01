package com.zup.gerenciadorDeFerias.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zup.gerenciadorDeFerias.enumeration.ProfileEnum;
import com.zup.gerenciadorDeFerias.enumeration.StatusUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(unique = true, nullable = false, length = 45)
    private String email;

    @Column(nullable = false)
    private LocalDate birthDate;

    private LocalDate hiringDate;

    private Integer daysBalance;

    @Enumerated(EnumType.STRING)
    private ProfileEnum profileEnum;

    @Enumerated(EnumType.STRING)
    private StatusUser statusUser;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<VacationRequest> vacationRequests;

    public User(String name, String email, LocalDate birthDate, LocalDate hiringDate, ProfileEnum profileEnum) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.hiringDate = hiringDate;
        this.profileEnum = profileEnum;
    }

    public User(String name, LocalDate birthDate, LocalDate hiringDate, ProfileEnum profileEnum, StatusUser statusUser, Integer daysBalance) {
        this.name = name;
        this.birthDate = birthDate;
        this.hiringDate = hiringDate;
        this.daysBalance = daysBalance;
        this.profileEnum = profileEnum;
        this.statusUser = statusUser;
    }
}
