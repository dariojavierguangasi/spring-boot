package com.rpconsulting.restfull.dtos;

import lombok.Data;

@Data
public class StudentCreationRequestDto {

    private String firstName;
    private String lastName;
    private String birthday;

}
