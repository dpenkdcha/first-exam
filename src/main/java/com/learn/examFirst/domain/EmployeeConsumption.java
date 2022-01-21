package com.scott.betaexam.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class EmployeeConsumption {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    private UUID id;

    private String name;

    private float unit;

    private String month;

    private String city;

    private LocalDateTime checkInDate;

}
