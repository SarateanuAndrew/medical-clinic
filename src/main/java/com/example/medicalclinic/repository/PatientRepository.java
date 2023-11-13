package com.example.medicalclinic.repository;

import com.example.medicalclinic.model.dbo.Medication;
import com.example.medicalclinic.model.dbo.Patient;
import com.example.medicalclinic.model.projection.PatientGetByIdResponse;
import com.example.medicalclinic.model.projection.PatientGetByIdResponseDtoP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    @Query("SELECT p FROM Patient p " +
            "WHERE p.lastname LIKE %:search% OR " +
            "p.firstname LIKE %:search%")
    List<Patient> getAllPatients(@Param("search") String search);

    @Query("SELECT p " +
            "FROM Patient p " +
            "JOIN FETCH p.medications " +
            "WHERE p.id = :id")
    Patient getPatientById(@Param("id") UUID id);

    //todo replace with left join

//    @Query("SELECT p.firstname as firstname, " +
//            "p.lastname as lastname, " +
//            "p.gender as gender, " +
//            "p.dateOfBirth as dateOfBirth, " +
//            "p.medications as medications " +
//            "FROM Patient p " +
//            "JOIN FETCH p.medications " +
//            "WHERE p.id = :id")
//    PatientGetByIdResponse getPatientById(@Param("id") UUID id);
}
