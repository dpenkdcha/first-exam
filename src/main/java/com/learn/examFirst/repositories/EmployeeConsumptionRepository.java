package com.learn.examFirst.repositories;


import com.learn.examFirst.domain.EmployeeConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeConsumptionRepository extends JpaRepository<EmployeeConsumption, UUID> {

    List<EmployeeConsumption> findByNameAndCity(@Param("name") String name, @Param("city") String city);

    List<EmployeeConsumption> findByNameAndCheckInDateBetween(String name, LocalDateTime startDate, LocalDateTime endDate);
}
