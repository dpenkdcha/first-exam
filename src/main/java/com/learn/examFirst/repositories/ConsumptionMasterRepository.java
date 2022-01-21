package com.scott.betaexam.repositories;

import com.scott.betaexam.domain.ConsumptionMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConsumptionMasterRepository extends JpaRepository<ConsumptionMaster, UUID> {

    List<ConsumptionMaster> findByCityInAndMonthIn(List<String> city, List<String> month);

    ConsumptionMaster findByCity(String city);
}
