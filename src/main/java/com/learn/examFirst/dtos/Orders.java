package com.learn.examFirst.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Length(max = 8, min = 8, message = "order length")
    private String orderId;

    @Size(max = 8, min = 8, message = "mandatory product id {max}" )
    private String productId;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date date1;

    //@NotBlank
    @Length(max = 8, min = 8, message = "mandatory order name")
    private String orderName;
}
