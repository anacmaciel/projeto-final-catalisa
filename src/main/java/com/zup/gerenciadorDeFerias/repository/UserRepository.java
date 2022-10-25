package com.zup.gerenciadorDeFerias.repository;

import com.zup.gerenciadorDeFerias.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from users where status_user in ('ACTIVE', 'ON_VACATION')", nativeQuery = true)
    List<User> findAllStatusActiveOrOnVacation();

    //public boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);


}


