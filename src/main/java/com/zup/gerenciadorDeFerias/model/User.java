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
import java.util.ArrayList;
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
    private String username;

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
    private String senha;


    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<VacationRequest> vacationRequests;


    @ManyToMany
    @JoinTable(name = "TB_USERES_ROLES", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "rode_id"))
    private List<RolesModel> roles;


    public User(String username, String email, LocalDate birthDate, LocalDate hiringDate, ProfileEnum profileEnum, String login, String senha) {
        this.username = username;
        this.email = email;
        this.birthDate = birthDate;
        this.hiringDate = hiringDate;
        this.profileEnum = profileEnum;
        this.login=login;
        this.senha=senha;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       Role role = new Role();
       role.setAuthority(profileEnum.name());

        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}