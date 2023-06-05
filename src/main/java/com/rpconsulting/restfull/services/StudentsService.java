package com.rpconsulting.restfull.services;

import com.rpconsulting.restfull.dtos.StudentCreationRequestDto;
import com.rpconsulting.restfull.dtos.StudentCreationResponseDto;
import com.rpconsulting.restfull.repositories.StudentsRepository;
import com.rpconsulting.restfull.repositories.entities.Student;
import com.rpconsulting.restfull.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentsService {

    private final StudentsRepository studentsRepository;

    public StudentCreationResponseDto create(StudentCreationRequestDto request) {
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

    public List<StudentCreationResponseDto> findAll() {
        List<Student> students = studentsRepository.findAll();

        List<StudentCreationResponseDto> responseDtos = students
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return responseDtos;
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
        return student;
    }

    private StudentCreationResponseDto toDto(Student entity) {
        StudentCreationResponseDto student = new StudentCreationResponseDto();
        student.setId(entity.getId());
        student.setFirstName(entity.getFirstName());
        student.setLastName(entity.getLastName());
        student.setBirthday(DateUtils.toString(entity.getBirthday()));
        return student;
    }

}