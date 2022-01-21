package com.learn.examFirst.unit;

import com.learn.examFirst.dtos.UsersDto;
import com.learn.examFirst.domain.Users;
import com.learn.examFirst.repositories.ConsumptionMasterRepository;
import com.learn.examFirst.repositories.EmployeeConsumptionRepository;
import com.learn.examFirst.repositories.UserRepository;
import com.learn.examFirst.services.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.learn.examFirst.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class UserServiceTests {

    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    EmployeeConsumptionRepository employeeConsumptionRepository;

    @Mock
    ConsumptionMasterRepository consumptionMasterRepository;

    @BeforeEach
    public void setup(){
        openMocks(this);
        userService = new UserServiceImpl(userRepository, employeeConsumptionRepository, consumptionMasterRepository, modelMapper);
    }

    @Test
    public void givenFilterByName_whenGetUserDetails_thenReturnFilteredUser() {
        //Arrange
        List<Users> createdTestUser = createdTestUsersList();
        when(userRepository.findByNameContains(FILTER_BY_NAME)).thenReturn(createdTestUser);

        //Act
        List<UsersDto> usersDto = userService.getUserDetails(Optional.of(FILTER_BY_NAME));

        //Assert
        assertEquals(1, usersDto.size());

    }

    @Test
    public void givenFilterByEmail_whenGetUserDetails_thenReturnFilteredUser() {
        //Arrange
        List<Users> createdTestUser = createdTestUsersList();
        when(userRepository.findByEmailContains(FILTER_BY_EMAIL)).thenReturn(createdTestUser);

        //Act
        List<UsersDto> usersDto = userService.getUserDetails(Optional.of(FILTER_BY_EMAIL));

        //Assert
        assertEquals(1, usersDto.size());

    }

    @Test
    public void givenEmptyFilter_whenGetUserDetails_thenReturnEmptyArray() {
        //Act
        List<UsersDto> usersDto = userService.getUserDetails(Optional.of(""));

        //Assert
        assertEquals(0, usersDto.size());

    }

    @Test
    public void givenNoMatchingFilter_whenGetUserDetails_thenReturnEmptyArray() {
        //Arrange
        List<Users> createdTestUser = createdTestUsersList();
        when(userRepository.findByNameContains(FILTER_BY_NO_MATCHING_NAME)).thenReturn(new ArrayList<>());

        //Act
        List<UsersDto> usersDto = userService.getUserDetails(Optional.of(FILTER_BY_NO_MATCHING_NAME));

        //Assert
        assertEquals(0, usersDto.size());
    }

    @Test
    public void givenUser_whenAddUserDetails_thenReturnAddedUser() {
        //Arrange
        UsersDto userData = createUserDataDto();
        when(userService.addUserData(userData)).thenReturn(userData);

        //Act
        UsersDto usersDto = userService.addUserData(userData);

        //Assert
        assertEquals(userData, usersDto);

    }
}
