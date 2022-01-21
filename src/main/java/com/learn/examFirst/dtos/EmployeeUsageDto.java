package com.learn.examFirst.dtos;

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
