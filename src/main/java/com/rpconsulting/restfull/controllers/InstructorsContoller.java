package com.rpconsulting.restfull.controllers;

import com.rpconsulting.restfull.dtos.InstructorCreationRequestDto;
import com.rpconsulting.restfull.dtos.InstructorCreationResponseDto;
import com.rpconsulting.restfull.services.InstructorsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1.0/instructors")
public class InstructorsContoller {

    private final InstructorsService instructorsService;

    public InstructorsContoller(InstructorsService instructorsService) {
        this.instructorsService = instructorsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstructorCreationResponseDto create(@RequestBody InstructorCreationRequestDto instructor) {
        return instructorsService.create(instructor);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public InstructorCreationResponseDto update(
            @PathVariable("id") UUID id,
            @RequestBody InstructorCreationRequestDto instructor) {
        return instructorsService.update(id, instructor);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        instructorsService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<InstructorCreationResponseDto> findAll(Pageable pageable) {
        return instructorsService.findAll(pageable);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public InstructorCreationResponseDto findById(@PathVariable("id") UUID id) {
        return instructorsService.findById(id);
    }

}
