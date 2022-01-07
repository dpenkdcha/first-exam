package com.scott.betaexam;

import com.scott.betaexam.domain.Users;
import com.scott.betaexam.dtos.UsersDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestData {

    public static final String BASE_ENDPOINT = "/api/v1";
    public static final String USER_DETAILS_ENDPOINT = "/users/{filterBy}";
    public static final String FILTER_BY_NAME = "Sams";
    public static final String FILTER_BY_NO_MATCHING_NAME = "sams";
    public static final String FILTER_BY_EMAIL = "samsdan@accn.com";
    public static final String ADD_USER_ENDPOINT = "/users";
    public static final String EXPECTED_TEST_GET_USER_DETAILS = "[{\"id\":\"14773ddd-1e47-4183-a03c-ad9323304fb2\",\"name\":\"Sams Dan\",\"email\":\"samsdan@accn.com\",\"address line 1\":\"Anglican House\",\"city\":\"DC\",\"state\":\"NYK\",\"create date\":\"2022-01-06T01:10:17.968\"}]";
    public static final String VALID_ADD_USER_DETAILS = "{\"name\":\"Sams Dan\",\"email\":\"samsdan@accn.com\",\"address line 1\":\"Anglican House\",\"city\":\"DC\",\"state\":\"NYK\"}";
    public static final String INVALID_ADD_USER_DETAILS = "{\"name\":\"\",\"email\":\"samsdan@accn.com\",\"address line 1\":\"Anglican House\",\"city\":\"DC\",\"state\":\"NYK\"}";
    public static final String INVALID_ADDRESS_ADD_USER_DETAILS = "{\"name\":\"Sams Dan\",\"email\":\"samsdan@accn.com\",\"address line 1\":\"\",\"city\":\"DC\",\"state\":\"NYK\"}";
    public static final String INVALID_CITY_ADD_USER_DETAILS = "{\"name\":\"Sams Dan\",\"email\":\"samsdan@accn.com\",\"address line 1\":\"Anglican House\",\"city\":\"\",\"state\":\"NYK\"}";
    public static final String INVALID_STATE_ADD_USER_DETAILS = "{\"name\":\"Sams Dan\",\"email\":\"samsdan@accn.com\",\"address line 1\":\"Anglican House\",\"city\":\"DC\",\"state\":\"\"}";
    public static final String MISSING_FIELD_TEST = "[\"Name can note be empty\"]";
    public static final String MISSING_ADDRESS_FIELD_TEST = "[\"Invalid Details: Address, City, State must be present\"]";

    public static List<UsersDto> createdTestUserDtoList(){
        UsersDto usersDto = new UsersDto(
                UUID.fromString("14773ddd-1e47-4183-a03c-ad9323304fb2"), "Sams Dan", "samsdan@accn.com", "Anglican House","DC", "NYK", LocalDateTime.parse("2022-01-06T01:10:17.968"));
        List<UsersDto> usersList = new ArrayList<>();
        usersList.add(usersDto);
        return usersList;
    }

    public static UsersDto createUserDataDto(){
        return new UsersDto(
                UUID.fromString("14773ddd-1e47-4183-a03c-ad9323304fb2"), "Sams Dan", "samsdan@accn.com", "Anglican House","DC", "NYK", LocalDateTime.parse("2022-01-06T01:10:17.968"));
    }

    public static List<Users> createdTestUsersList(){
        Users users = new Users(
                UUID.fromString("14773ddd-1e47-4183-a03c-ad9323304fb2"), "Sams Dan", "samsdan@accn.com", "Anglican House","DC", "NYK", LocalDateTime.parse("2022-01-06T01:10:17.968"));
        List<Users> usersList = new ArrayList<>();
        usersList.add(users);
        return usersList;
    }

    public static Users createUserData(){
        return new Users(
                UUID.fromString("14773ddd-1e47-4183-a03c-ad9323304fb2"), "Sams Dan", "samsdan@accn.com", "Anglican House","DC", "NYK", LocalDateTime.parse("2022-01-06T01:10:17.968"));
    }

    public static UsersDto createInvalidUserDataDto(){
        return new UsersDto(
                UUID.fromString("14773ddd-1e47-4183-a03c-ad9323304fb2"), "", "samsdan@accn.com", "Anglican House","DC", "NYK", LocalDateTime.parse("2022-01-06T01:10:17.968"));
    }

}
