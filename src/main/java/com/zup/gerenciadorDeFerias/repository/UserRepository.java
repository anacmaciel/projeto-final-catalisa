package com.zup.gerenciadorDeFerias.repository;

import com.zup.gerenciadorDeFerias.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
