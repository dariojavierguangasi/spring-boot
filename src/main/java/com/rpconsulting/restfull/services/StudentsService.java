package com.rpconsulting.restfull.services;

import com.rpconsulting.restfull.dtos.StudentCreationRequestDto;
import com.rpconsulting.restfull.dtos.StudentCreationResponseDto;
import com.rpconsulting.restfull.exceptions.AlreadyExistsException;
import com.rpconsulting.restfull.exceptions.AlreadyExistsExceptionV2;
import com.rpconsulting.restfull.repositories.StudentsRepository;
import com.rpconsulting.restfull.repositories.entities.Student;
import com.rpconsulting.restfull.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentsService {

    private final StudentsRepository studentsRepository;

    public StudentCreationResponseDto create(StudentCreationRequestDto request) {

        Optional<Student> optionalStudent = studentsRepository.findFirstByDni(request.getDni());

        if (optionalStudent.isPresent()) {
            throw new AlreadyExistsExceptionV2(
                    Arrays.asList(
                            "Ya existe un estudiante con el dni: " + request.getDni()
                    ));
        }

        Student createdStudent = studentsRepository.save(toEntity(request));
        return toDto(createdStudent);
    }

    public StudentCreationResponseDto update(Long id, StudentCreationRequestDto dto) {
        this.findFistById(id);
        Student student = toEntity(dto);
        student.setId(id);
        Student updateStudent = studentsRepository.save(student);
        return toDto(updateStudent);
    }

    public Page<StudentCreationResponseDto> findAll(Pageable pageable) {
        Page<Student> students = studentsRepository.findAll(pageable);

        return new PageImpl<>(
                students.getContent()
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList()),
                students.getPageable(),
                students.getTotalElements()
        );
    }

    public StudentCreationResponseDto findById(Long id) {
        Student student = findFistById(id);
        return toDto(student);
    }

    public void delete(Long id) {
        Student student = findFistById(id);
        studentsRepository.deleteById(student.getId());
    }

    private Student findFistById(Long id) {
        Optional<Student> optionalStudent = studentsRepository.findById(id);

        if (!optionalStudent.isPresent()) {
            throw new RuntimeException("No existe un estudiante con id: " + id);
        }

        return optionalStudent.get();
    }

    private Student toEntity(StudentCreationRequestDto request) {
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setBirthday(DateUtils.toLocalDate(request.getBirthday()));
        student.setInsertionDate(LocalDateTime.now());
        student.setDni(request.getDni());
        return student;
    }

    private StudentCreationResponseDto toDto(Student entity) {
        StudentCreationResponseDto student = new StudentCreationResponseDto();
        student.setId(entity.getId());
        student.setFirstName(entity.getFirstName());
        student.setLastName(entity.getLastName());
        student.setBirthday(DateUtils.toString(entity.getBirthday()));
        student.setDni(entity.getDni());
        return student;
    }

}