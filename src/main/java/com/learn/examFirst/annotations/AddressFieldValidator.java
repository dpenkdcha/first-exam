package com.learn.examFirst.annotations;

import com.learn.examFirst.dtos.UsersDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddressFieldValidator implements ConstraintValidator<AddressFieldValidation, UsersDto> {
    @Override
    public void initialize(AddressFieldValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UsersDto usersDto, ConstraintValidatorContext context) {
        if(usersDto.getAddress().equals("") &&
                usersDto.getCity().equals("") &&
                usersDto.getState().equals("")) {
            return true;
        } else if(!usersDto.getAddress().equals("") &&
                !usersDto.getCity().equals("") &&
                !usersDto.getState().equals("")) {
            return true;
        } else
            return false;
    }

}
