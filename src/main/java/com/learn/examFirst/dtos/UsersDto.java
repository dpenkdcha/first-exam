package com.scott.betaexam.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.scott.betaexam.annotations.AddressFieldValidation;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@AddressFieldValidation()
public class UsersDto {

    private UUID id;

    @NotEmpty(message = "Name can note be empty")
    private String name;

    @NotEmpty(message = "Email can not be empty")
    private String email;

    @JsonProperty("address line 1")
    private String address;

    private String city;

    private String state;

    @JsonProperty("create date")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createDate;

}



