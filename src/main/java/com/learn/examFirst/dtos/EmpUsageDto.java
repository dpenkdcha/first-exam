package com.scott.betaexam.dtos;

import com.scott.betaexam.domain.EmployeeConsumption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class EmpUsageDto {

    private float totalDays;

    private float costPerDay;

    private float totalPrice;

    private List<EmployeeConsumption> employeeConsumptionList;
}
