package com.rpconsulting.restfull.repositories;

import com.rpconsulting.restfull.repositories.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstructorsRepository extends JpaRepository<Instructor, UUID> {

    @Query("select i from Instructor i where i.dni = :dni")
    Optional<Instructor> findFirstByDni(String dni);

}
