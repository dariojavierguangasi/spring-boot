package com.rpconsulting.restfull.repositories;

import com.rpconsulting.restfull.repositories.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s where s.dni = :dni")
    Optional<Student> findFirstByDni(String dni);
}
