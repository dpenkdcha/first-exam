package com.scott.betaexam.controllers;

import com.scott.betaexam.dtos.EmpUsageDto;
import com.scott.betaexam.dtos.InputDto;
import com.scott.betaexam.dtos.SimpleResponse;
import com.scott.betaexam.dtos.UsersDto;
import com.scott.betaexam.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = UserController.BASE_ENDPOINT)
public class UserController {

    public static final String BASE_ENDPOINT = "/api/v1";
    public static final String ALL_USER_DETAILS_ENDPOINT = "/users/";
    public static final String USER_DETAILS_ENDPOINT = "/users/{filterBy}";
    public static final String ADD_ALL_USERS_ENDPOINT = "/add-users";
    public static final String ADD_USER_ENDPOINT = "/users";
    public static final String GET_USER_BY_NAME_DATA = "/userBYNameDate";

    private final UserService userService;

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

    @GetMapping(value = GET_USER_BY_NAME_DATA)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity getUserByNameDate(
                                            @RequestParam(value = "name") String name,
                                            @RequestParam(value = "startdate") String startDate,
                                            @RequestParam(value = "enddate") String endDate) throws ParseException {

//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

//        LocalDate date = LocalDate.parse(sDate, DateTimeFormatter.BASIC_ISO_DATE);
//        System.out.println(date);

//        LocalDateTime dateTime = LocalDateTime.parse("2018-05-05T11:50:55", dateTimeFormatter);
//        System.out.println(dateTime);

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
//        Date date1 = simpleDateFormat.parse("07-Jan-2020");
//        System.out.println(date1);

        EmpUsageDto empUsageDto = this.userService.getUserByNameDate(name, startDate, endDate);

        return new ResponseEntity(empUsageDto, HttpStatus.OK);
    }

    @PostMapping(value = "/orders")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity getOrders(
            @RequestHeader UUID requestId,
            @RequestHeader String createdAt,
            @Valid @RequestBody InputDto inputDto) {

        System.out.println("" + " " + createdAt + " " + " " + requestId);
        //this.userService.getOrders(inputDto, createdAt, requestId);

        return new ResponseEntity<>(new SimpleResponse(requestId, createdAt), HttpStatus.CREATED);
    }
}
