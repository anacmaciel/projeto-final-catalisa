package com.zup.gerenciadorDeFerias.model;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zup.gerenciadorDeFerias.enumeration.ProfileEnum;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
public class RolesModel implements Serializable, GrantedAuthority {
    private static final long seriaVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;

    @Enumerated
    @Column(nullable = false, unique = true)
    private ProfileEnum role;

    @Override
    public String getAuthority() {
        return this.role.toString();
    }

    @OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<User> userPeople;

}
