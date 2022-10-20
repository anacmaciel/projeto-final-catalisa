package com.zup.gerenciadorDeFerias.repository;

import com.zup.gerenciadorDeFerias.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from status_ where = ACTIVE, ON_ VACASTION", nativeQuery = true)
    List<User> findAllSTATUSUSER();

    @Query(value = "select user_id from susers where = ACTIVE, ON_ VACATION", nativeQuery = true)
    List<User> findBYStatusUsers();

}
