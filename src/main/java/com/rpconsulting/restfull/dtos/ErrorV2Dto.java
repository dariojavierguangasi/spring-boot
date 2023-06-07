package com.rpconsulting.restfull.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorV2Dto {

    private List<String> messages;
    private Integer code;
    private String url;
    private LocalDateTime time;
}
