package com.learn.examFirst.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor @AllArgsConstructor
public class SimpleResponse {

    private UUID requestId;

    private String createdAt;
}
