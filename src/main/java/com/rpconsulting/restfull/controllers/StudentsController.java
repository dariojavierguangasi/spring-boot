package com.rpconsulting.restfull.controllers;

import com.rpconsulting.restfull.dtos.StudentCreationRequestDto;
import com.rpconsulting.restfull.dtos.StudentCreationResponseDto;
import com.rpconsulting.restfull.services.StudentsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1.0/students")
public class StudentsController {

    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentCreationResponseDto create(@RequestBody StudentCreationRequestDto student) {
        return studentsService.create(student);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentCreationResponseDto update(
            @PathVariable("id") Long id,
            @RequestBody StudentCreationRequestDto student) {
        return studentsService.update(id, student);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        studentsService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<StudentCreationResponseDto> findAll(Pageable pageable) {
        return studentsService.findAll(pageable);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentCreationResponseDto findById(@PathVariable("id") Long id) {
        return studentsService.findById(id);
    }
}
