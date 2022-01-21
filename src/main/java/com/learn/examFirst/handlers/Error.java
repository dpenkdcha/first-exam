package com.learn.examFirst.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@AllArgsConstructor
@Data
public class Error {

    private String des;
    private String type;
    private List<ErrorDetails> details;
}
