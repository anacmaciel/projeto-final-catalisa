package com.zup.gerenciadorDeFerias.model;

import com.zup.gerenciadorDeFerias.enumeration.StatusVacationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "vacation_request")

public class VacationRequest implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer vacationDays;

    @Column(nullable = false)
    private LocalDate startAt;

    @Column(nullable = false)
    private LocalDate endAt;

    @Enumerated(EnumType.STRING)
    private StatusVacationRequest statusVacationRequest;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    public VacationRequest(Integer vacationDays, LocalDate startAt) {
        this.vacationDays = vacationDays;
        this.startAt = startAt;
    }

    public VacationRequest(Integer vacationDays, LocalDate startAt, StatusVacationRequest statusVacationRequest) {
        this.vacationDays = vacationDays;
        this.startAt = startAt;
        this.statusVacationRequest = statusVacationRequest;
    }

}
