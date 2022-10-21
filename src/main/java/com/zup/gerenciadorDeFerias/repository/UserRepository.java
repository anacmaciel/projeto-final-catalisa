package com.zup.gerenciadorDeFerias.repository;

import com.zup.gerenciadorDeFerias.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query(value = "select * from status_user where = ACTIVE, ON_VACACION", nativeQuery = true)
    List<User> findAllStatusUser();


    @Query(value = "select user_id from users where = ACTIVE, ON_ VACATION", nativeQuery = true)
    List<User> findBYStatusUsers();

}
