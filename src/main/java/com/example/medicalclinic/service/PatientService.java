package com.example.medicalclinic.service;

import com.example.medicalclinic.model.dbo.Medication;
import com.example.medicalclinic.model.dto.request.MedicationRequestDto;
import com.example.medicalclinic.model.dto.request.PatientRequestDto;
import com.example.medicalclinic.model.dto.response.MedicationResponseDto;
import com.example.medicalclinic.model.dto.response.PatientGetAllResponseDto;
import com.example.medicalclinic.model.dto.response.PatientGetByIdResponseDto;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    void createPatient(PatientRequestDto patientRequestDto);
    void updatePatient(UUID id, PatientRequestDto patientRequestDto);
    void removePatient(UUID id);
    List<PatientGetAllResponseDto> getAll(String search);
    PatientGetByIdResponseDto getPatientGetById(UUID id);
    void addMedicationsToPatient(UUID patientId, List<MedicationRequestDto> medication);
    void updateMedication(UUID id, MedicationRequestDto medicationRequestDto);
    void deleteMedication(UUID patientId, UUID medicamentId);
    List<MedicationResponseDto> getMedicationByPatient(UUID patientId);
}
