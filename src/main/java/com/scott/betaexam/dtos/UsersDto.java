package com.scott.betaexam.dtos;

import com.scott.betaexam.annotations.AddressFieldValidation;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@AddressFieldValidation()
public class UsersDto {

    private UUID id;

    @NotEmpty(message = "Name can note be empty")
    private String name;

    @NotEmpty(message = "Email can not be empty")
    private String email;

    private String address;

    private String city;

    private String state;

    private LocalDateTime createDate;

}
