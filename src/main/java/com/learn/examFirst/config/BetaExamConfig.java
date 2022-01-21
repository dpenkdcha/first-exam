package com.learn.examFirst.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BetaExamConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
