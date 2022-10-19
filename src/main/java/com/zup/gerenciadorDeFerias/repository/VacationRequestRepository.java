package com.zup.gerenciadorDeFerias.repository;

import com.zup.gerenciadorDeFerias.model.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {
}
