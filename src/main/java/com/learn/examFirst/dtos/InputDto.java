package com.learn.examFirst.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputDto {

    @JsonProperty(value = "orders")
    @Valid
    private List<Orders> orders;
}
