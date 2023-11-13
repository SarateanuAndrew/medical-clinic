package com.example.medicalclinic.model.projection;

import com.example.medicalclinic.model.dbo.Medication;
import com.example.medicalclinic.model.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public interface PatientGetByIdResponse {
    String getFirstname();
    String getLastname();
    Gender getGender();
    LocalDate getDateOfBirth();
    List<Medication> getMedications();


}
