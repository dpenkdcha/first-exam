package com.learn.examFirst.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDetails {

    private String code;
    private String field;
    private String msg;

}
