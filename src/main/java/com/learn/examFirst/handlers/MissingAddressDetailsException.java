package com.scott.betaexam.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class MissingAddressDetailsException {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<List> handleException(MethodArgumentNotValidException ex) {
//        List list = ex.getBindingResult().getAllErrors().stream()
//                .map(fieldError -> fieldError.getDefaultMessage())
//                .collect(Collectors.toList());
//
//        return new ResponseEntity(list, HttpStatus.OK);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<ErrorDetails> detailsList = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErrorDetails("invalid", fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        List<Error> errorList = new ArrayList<>();
        errorList.add(new Error("Desccc", "Typeee", detailsList));

        return new ResponseEntity(new ErrorResponse("requestId", errorList), HttpStatus.BAD_REQUEST);

        //BindingResult result = ex.getBindingResult();
//        result.getFieldErrors().forEach((fieldError) -> {
//            //errorList.add(fieldError.getObjectName()+"."+fieldError.getField()+" : " +fieldError.getDefaultMessage() +" : rejected value [" +fieldError.getRejectedValue() +"]" );
//            System.out.println(fieldError);
//        });

        //System.out.println(detailsList);


        //System.out.println(errorList);

//        errorList : [
//                {
//            desc : a,
//            type : b,
//            detailList :
//            [{},{}]
//
//        }
//        ]

    }
}
