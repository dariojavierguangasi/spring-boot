package com.rpconsulting.restfull.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDto {

    private String message;
    private Integer code;
    private String url;
    private LocalDateTime time;

}
