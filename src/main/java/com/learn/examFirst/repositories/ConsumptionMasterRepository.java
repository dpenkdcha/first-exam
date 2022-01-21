package com.learn.examFirst.repositories;

import com.learn.examFirst.domain.ConsumptionMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConsumptionMasterRepository extends JpaRepository<ConsumptionMaster, UUID> {

    List<ConsumptionMaster> findByCityInAndMonthIn(List<String> city, List<String> month);

    ConsumptionMaster findByCity(String city);
}
