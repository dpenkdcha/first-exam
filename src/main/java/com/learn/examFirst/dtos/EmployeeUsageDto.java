package com.scott.betaexam.dtos;

import com.scott.betaexam.domain.ConsumptionMaster;
import com.scott.betaexam.domain.EmployeeConsumption;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeUsageDto {

    private String name;

    private String city;

    private float unit;

    private float totalPrice;

}
