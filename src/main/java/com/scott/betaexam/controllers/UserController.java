package com.scott.betaexam.controllers;

import com.scott.betaexam.dtos.UsersDto;
import com.scott.betaexam.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = UserController.BASE_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    public static final String BASE_ENDPOINT = "/api/v1";
    public static final String ALL_USER_DETAILS_ENDPOINT = "/users/";
    public static final String USER_DETAILS_ENDPOINT = "/users/{filterBy}";
    public static final String ADD_ALL_USERS_ENDPOINT = "/add-users";
    public static final String ADD_USER_ENDPOINT = "/users";

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = {USER_DETAILS_ENDPOINT, ALL_USER_DETAILS_ENDPOINT})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity getUserDetails(@PathVariable(value = "filterBy", required = false) Optional<String> filterBy ) {
        try{
            List<UsersDto> usersDto = this.userService.getUserDetails(filterBy);
            return new ResponseEntity(usersDto,HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity("Failed to retrieve data: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = ADD_USER_ENDPOINT)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity addUserDetails(@Valid @RequestBody UsersDto userData) {
        UsersDto usersDto = this.userService.addUserData(userData);
        return new ResponseEntity(usersDto, HttpStatus.OK);
    }

    /*@PostMapping(value = ADD_ALL_USERS_ENDPOINT)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity addAllData(@Valid @RequestBody List<UsersDto> userData) {
        //this.userService.addAllData(userData);
        return new ResponseEntity(HttpStatus.OK);
    }*/
}
