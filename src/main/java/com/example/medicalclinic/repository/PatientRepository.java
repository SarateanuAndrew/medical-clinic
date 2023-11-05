package com.example.medicalclinic.repository;

import com.example.medicalclinic.model.dbo.Patient;
import com.example.medicalclinic.model.dto.request.PatientRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

}
