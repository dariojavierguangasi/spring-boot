package com.rpconsulting.restfull.services;

import com.rpconsulting.restfull.dtos.InstructorCreationRequestDto;
import com.rpconsulting.restfull.dtos.InstructorCreationResponseDto;
import com.rpconsulting.restfull.repositories.InstructorsRepository;
import com.rpconsulting.restfull.repositories.entities.Instructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorsService {

    private final InstructorsRepository instructorsRepository;

    public InstructorCreationResponseDto create(InstructorCreationRequestDto requestDto) {
        Instructor instructor = this.instructorsRepository.save(toEntity(requestDto));
        return toDto(instructor);
    }

    public InstructorCreationResponseDto update(UUID id, InstructorCreationRequestDto requestDto) {
        this.findFirstById(id);
        Instructor instructor = toEntity(requestDto);
        instructor.setId(id);
        instructor = instructorsRepository.save(instructor);
        return toDto(instructor);
    }

    public void delete(UUID id) {
        this.findFirstById(id);
        instructorsRepository.deleteById(id);
    }

    public InstructorCreationResponseDto findById(UUID id) {
        Instructor instructor = findFirstById(id);
        return toDto(instructor);
    }

    public Page<InstructorCreationResponseDto> findAll(Pageable pageable) {
        Page<Instructor> instructors = instructorsRepository.findAll(pageable);

        return new PageImpl<>(
                instructors.getContent()
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList()),
                instructors.getPageable(),
                instructors.getTotalElements());
    }

    private Instructor findFirstById(UUID id) {
        Optional<Instructor> optionalInstructor = instructorsRepository.findById(id);

        if (!optionalInstructor.isPresent()) {
            throw new RuntimeException("No existe un instructor con id: " + id);
        }

        return optionalInstructor.get();
    }


    private Instructor toEntity(InstructorCreationRequestDto dto) {
        Instructor instructor = new Instructor();
        instructor.setFirstName(dto.getFirstName());
        instructor.setLastName(dto.getLastName());
        instructor.setInsertionDate(LocalDateTime.now());
        return instructor;
    }

    private InstructorCreationResponseDto toDto(Instructor instructor) {
        InstructorCreationResponseDto dto = new InstructorCreationResponseDto();
        dto.setId(instructor.getId().toString());
        dto.setFirstName(instructor.getFirstName());
        dto.setLastName(instructor.getLastName());
        return dto;
    }

}
