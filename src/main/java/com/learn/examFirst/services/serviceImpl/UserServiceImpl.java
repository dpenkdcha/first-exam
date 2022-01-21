package com.scott.betaexam.services.serviceImpl;

import com.scott.betaexam.domain.ConsumptionMaster;
import com.scott.betaexam.domain.EmployeeConsumption;
import com.scott.betaexam.domain.Users;
import com.scott.betaexam.dtos.EmpUsageDto;
import com.scott.betaexam.dtos.EmployeeUsageDto;
import com.scott.betaexam.dtos.InputDto;
import com.scott.betaexam.dtos.UsersDto;
import com.scott.betaexam.repositories.ConsumptionMasterRepository;
import com.scott.betaexam.repositories.EmployeeConsumptionRepository;
import com.scott.betaexam.repositories.UserRepository;
import com.scott.betaexam.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmployeeConsumptionRepository employeeConsumptionRepository;
    private final ConsumptionMasterRepository consumptionMasterRepository;
    private final ModelMapper modelMapper;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public List<UsersDto> getUserDetails(Optional<String> filterBy) {
        return findByNameOrEmail(filterBy).stream().map(entity -> modelMapper.map(entity, UsersDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UsersDto addUserData(UsersDto userDto) {
        Users user = userRepository.save(modelMapper.map(userDto, Users.class));
        return modelMapper.map(user, UsersDto.class);
    }

    @Override
    public List<EmployeeUsageDto> getUserByNameCity(String name, String sDate, String eDate) {
        List<EmployeeConsumption> employeeConsumptionsList = employeeConsumptionRepository.findByNameAndCity(name, sDate);
        List<String> cityList = new ArrayList<>();
        List<String> monthList = new ArrayList<>();
        System.out.println(employeeConsumptionsList.size());

        employeeConsumptionsList.forEach(e -> {
            System.out.println(e.getCity());
            System.out.println(e.getMonth());
                    cityList.add(e.getCity());
                    monthList.add(e.getMonth());
                }
        );

        List<ConsumptionMaster> consumptionMastersList = consumptionMasterRepository.findByCityInAndMonthIn(cityList, monthList);

        Map<String, EmployeeConsumption> EmpConsumeByMonth = employeeConsumptionsList.stream()
                .collect(Collectors.toMap(EmployeeConsumption::getMonth, Function.identity()));

        List<EmployeeUsageDto> empUsages = consumptionMastersList.stream()
                .map(conMaster -> {
                    String empName = EmpConsumeByMonth.get(conMaster.getMonth()).getName();
                    String empCity = EmpConsumeByMonth.get(conMaster.getMonth()).getCity();
                    float empUnit = EmpConsumeByMonth.get(conMaster.getMonth()).getUnit();
                    float totalPrice = empUnit * conMaster.getUnitPrice();
                    return new EmployeeUsageDto(empName, empCity, empUnit, totalPrice);
                })
                .collect(Collectors.toList());

        EmployeeUsageDto reducedEmp = empUsages.stream().reduce(new EmployeeUsageDto(), (
                (employeeUsageDto, e) -> {
                    return new EmployeeUsageDto(e.getName(), e.getCity(),
                            employeeUsageDto.getUnit() + e.getUnit(),
                            employeeUsageDto.getTotalPrice() + e.getTotalPrice());
                    }
                ));

        List<EmployeeUsageDto> reducedEmpList = new ArrayList<>();
        reducedEmpList.add(reducedEmp);

        return reducedEmpList;
    }

    @Override
    public EmpUsageDto getUserByNameDate(String name, String startDate, String endDate) {
        LocalDateTime startTime = LocalDateTime.of(LocalDate.parse(startDate, DateTimeFormatter.BASIC_ISO_DATE), LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(LocalDate.parse(endDate, DateTimeFormatter.BASIC_ISO_DATE), LocalTime.MIN);
        List<EmployeeConsumption> employeeConsumptionList = employeeConsumptionRepository.findByNameAndCheckInDateBetween(
                name, startTime, endTime);

        ConsumptionMaster consumptionMaster = consumptionMasterRepository.findByCity(employeeConsumptionList.get(0).getCity());


        List<EmpUsageDto> empUsageDto = employeeConsumptionList.stream().map(
                consumption -> {
                    return new EmpUsageDto(consumption.getUnit(),
                            consumptionMaster.getUnitPrice(),
                            consumption.getUnit() * consumptionMaster.getUnitPrice(),
                            employeeConsumptionList
                            );
                }
        ).collect(Collectors.toList());

        EmpUsageDto returnEmpUsageDto = empUsageDto.stream().reduce(new EmpUsageDto(),
                ((empUsageDto1, employeeConsumption) -> {
                    return new EmpUsageDto(
                            (empUsageDto1.getTotalDays() + employeeConsumption.getTotalDays()),
                            employeeConsumption.getCostPerDay(),
                            empUsageDto1.getTotalPrice() + employeeConsumption.getTotalPrice(),
                            employeeConsumptionList
                    );
                    }
                ));

        return returnEmpUsageDto;
    }

    @Override
    public void getOrders(InputDto inputDto, String createdAt, UUID requestId) {
        System.out.println(inputDto + " " + createdAt + " " + " " + requestId);
    }

    public List<Users> findByNameOrEmail(Optional<String> filterBy) {
        if (!filterBy.isPresent()) {
            return userRepository.findAll();
        } else if(VALID_EMAIL_ADDRESS_REGEX.matcher(filterBy.get()).find()) {
            return userRepository.findByEmailContains(filterBy.get());
        } else {
            return userRepository.findByNameContains(filterBy.get());
        }
    }
}
