package com.rpconsulting.restfull.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class AlreadyExistsExceptionV2 extends RuntimeException{

    private List<String> errors;
    public  AlreadyExistsExceptionV2(List<String> errors) {
        this.errors = errors;
    }
}
