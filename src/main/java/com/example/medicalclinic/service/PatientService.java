package com.example.medicalclinic.service;

import com.example.medicalclinic.model.dto.request.MedicationRequestDto;
import com.example.medicalclinic.model.dto.request.PatientRequestDto;
import com.example.medicalclinic.model.dto.request.PatientUpdateRequestDto;
import com.example.medicalclinic.model.dto.request.SortedPatients;
import com.example.medicalclinic.model.dto.response.MedicationResponseDto;
import com.example.medicalclinic.model.dto.response.PatientGetAllResponseDto;
import com.example.medicalclinic.model.projection.PatientGetByIdResponseDtoP;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    void createPatient(PatientRequestDto patientRequestDto);
    void updatePatient(UUID id, PatientUpdateRequestDto patientRequestDto);
    void removePatient(UUID id);
    List<PatientGetAllResponseDto> getAll(SortedPatients search);
    PatientGetByIdResponseDtoP getPatientGetById(UUID id);
    void addMedicationsToPatient(UUID patientId, List<MedicationRequestDto> medication);
    void updateMedication(UUID medicationId, MedicationRequestDto medicationRequestDto);
    void deleteMedication(UUID medicamentId);
    List<MedicationResponseDto> getMedicationByPatient(UUID patientId);
}
