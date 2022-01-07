package com.scott.betaexam.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("address line 1")
    private String address;

    private String city;

    private String state;

    @JsonProperty("create date")
    private LocalDateTime createDate;

}
