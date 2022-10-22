package com.zup.gerenciadorDeFerias.repository;

import com.zup.gerenciadorDeFerias.model.User;
import com.zup.gerenciadorDeFerias.model.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from status_user where = ACTIVE, ON_VACATION", nativeQuery = true)
    List<User> findAllStatusUser();

}
