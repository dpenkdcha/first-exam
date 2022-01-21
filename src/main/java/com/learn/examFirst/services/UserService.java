package com.scott.betaexam.services;

import com.scott.betaexam.dtos.EmpUsageDto;
import com.scott.betaexam.dtos.EmployeeUsageDto;
import com.scott.betaexam.dtos.InputDto;
import com.scott.betaexam.dtos.UsersDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    public List<UsersDto> getUserDetails(Optional<String> name);

    UsersDto addUserData(UsersDto userData);

    List<EmployeeUsageDto> getUserByNameCity(String name, String sDate, String eDate);

    EmpUsageDto getUserByNameDate(String name, String startDate, String endDate);

    void getOrders(InputDto inputDto, String createdAt, UUID requestId);

    //public List<UsersDto> getAllUsers(); //No Testing

    //Void addAllData(List<UsersDto> userData); //No Testing
}
