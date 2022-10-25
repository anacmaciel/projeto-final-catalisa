package com.zup.gerenciadorDeFerias.repository;

import com.zup.gerenciadorDeFerias.model.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {
    @Query(value = "select * from vacation_request where status_vacation_request in ('CREATED', 'ONGOING', 'CONCLUDED')", nativeQuery = true)
    List<VacationRequest> findAllStatusVacationRequest();

}
