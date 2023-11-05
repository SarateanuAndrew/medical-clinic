package com.example.medicalclinic.service.implementation;

import com.example.medicalclinic.model.dbo.Medication;
import com.example.medicalclinic.model.dbo.Patient;
import com.example.medicalclinic.model.dto.request.MedicationRequestDto;
import com.example.medicalclinic.model.dto.request.PatientRequestDto;
import com.example.medicalclinic.model.dto.response.MedicationResponseDto;
import com.example.medicalclinic.model.dto.response.PatientGetAllResponseDto;
import com.example.medicalclinic.model.dto.response.PatientGetByIdResponseDto;
import com.example.medicalclinic.repository.MedicationRepository;
import com.example.medicalclinic.repository.PatientRepository;
import com.example.medicalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final MedicationRepository medicationRepository;

    @Override
    public void createPatient(PatientRequestDto patientRequestDto) {
        List<Medication> list = patientRequestDto.getMedications().stream()
                .map(m -> Medication.builder()
                        .description(m.getDescription())
                        .unit(m.getUnit())
                        .dosage(m.getDosage())
                        .time(m.getTime())
                        .build())
                .toList();
        patientRepository.save(Patient.builder()
                .dateOfBirth(patientRequestDto.getDateOfBirth())
                .gender(patientRequestDto.getGender())
                .firstname(patientRequestDto.getFirstname())
                .lastname(patientRequestDto.getLastname())
                .medications(list)
                .build());
    }

    @Override
    public void updatePatient(UUID id, PatientRequestDto patientRequestDto) {
        Patient patient = patientRepository.findById(id).orElseThrow();
        patient.setFirstname(patientRequestDto.getFirstname());
        patient.setGender(patientRequestDto.getGender());
        patient.setLastname(patientRequestDto.getLastname());
//        patient.setMedications(patientRequestDto.getMedications());
        patient.setDateOfBirth(patientRequestDto.getDateOfBirth());
        patient.setModificationDate(LocalDateTime.now());
        patientRepository.save(patient);
    }

    @Override
    public void removePatient(UUID id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<PatientGetAllResponseDto> getAll(String search) {
        return null;
    }

    @Override
    public PatientGetByIdResponseDto getPatientGetById(UUID id) {
        return null;
    }

    @Override
    public void addMedicationsToPatient(UUID patientId, List<MedicationRequestDto> medication) {

    }

    @Override
    public void updateMedication(UUID id, MedicationRequestDto medicationRequestDto) {

    }

    @Override
    public void deleteMedication(UUID patientId, UUID medicamentId) {

    }

    @Override
    public List<MedicationResponseDto> getMedicationByPatient(UUID patientId) {
        return null;
    }

}
