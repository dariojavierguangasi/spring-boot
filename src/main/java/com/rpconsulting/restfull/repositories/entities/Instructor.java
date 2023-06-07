package com.rpconsulting.restfull.repositories.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    @GeneratedValue
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "insertion_date")
    private LocalDateTime insertionDate;

    @Column(name = "dni")
    private String dni;

}
