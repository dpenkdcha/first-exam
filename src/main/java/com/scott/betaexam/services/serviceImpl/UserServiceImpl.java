package com.scott.betaexam.services.serviceImpl;

import com.scott.betaexam.domain.Users;
import com.scott.betaexam.dtos.UsersDto;
import com.scott.betaexam.repositories.UserRepository;
import com.scott.betaexam.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

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
