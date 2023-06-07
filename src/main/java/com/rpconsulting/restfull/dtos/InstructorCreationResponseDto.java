package com.rpconsulting.restfull.dtos;

import lombok.Data;

@Data
public class InstructorCreationResponseDto {

    public String id;
    private String firstName;
    private String lastName;
    private String dni;

}
