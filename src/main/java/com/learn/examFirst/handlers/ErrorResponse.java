package com.learn.examFirst.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ErrorResponse {

    private String requestId;

    private List<Error> error;
}
