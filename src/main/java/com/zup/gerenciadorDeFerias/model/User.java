package com.zup.gerenciadorDeFerias.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zup.gerenciadorDeFerias.enumeration.ProfileEnum;
import com.zup.gerenciadorDeFerias.enumeration.StatusUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails, Serializable {
    private static final long seriaVersionUID = 1L;

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

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;


    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<VacationRequest> vacationRequests;




    public User(String name, String email, LocalDate birthDate, LocalDate hiringDate, ProfileEnum profileEnum, String login, String password) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.hiringDate = hiringDate;
        this.profileEnum = profileEnum;
        this.login=login;
        this.password=password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
