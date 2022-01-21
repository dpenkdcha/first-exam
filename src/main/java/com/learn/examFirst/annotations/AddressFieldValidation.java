package com.scott.betaexam.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Constraint(validatedBy = AddressFieldValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.TYPE, FIELD, PARAMETER })
public @interface AddressFieldValidation {
    public String message() default "Invalid Details: Address, City, State must be present";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
