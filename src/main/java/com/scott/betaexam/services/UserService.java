package com.scott.betaexam.services;

import com.scott.betaexam.dtos.UsersDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<UsersDto> getUserDetails(Optional<String> name);

    UsersDto addUserData(UsersDto userData);

    //public List<UsersDto> getAllUsers(); //No Testing

    //Void addAllData(List<UsersDto> userData); //No Testing
}
