package com.learn.examFirst.services;

import com.learn.examFirst.dtos.EmpUsageDto;
import com.learn.examFirst.dtos.EmployeeUsageDto;
import com.learn.examFirst.dtos.InputDto;
import com.learn.examFirst.dtos.UsersDto;

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
