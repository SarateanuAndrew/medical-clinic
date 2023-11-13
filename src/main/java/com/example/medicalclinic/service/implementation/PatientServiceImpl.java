package com.example.medicalclinic.service.implementation;

import com.example.medicalclinic.model.dbo.Medication;
import com.example.medicalclinic.model.dbo.Patient;
import com.example.medicalclinic.model.dto.request.MedicationRequestDto;
import com.example.medicalclinic.model.dto.request.PatientRequestDto;
import com.example.medicalclinic.model.dto.request.PatientUpdateRequestDto;
import com.example.medicalclinic.model.dto.request.SortedPatients;
import com.example.medicalclinic.model.dto.response.MedicationResponseDto;
import com.example.medicalclinic.model.dto.response.PatientGetAllResponseDto;
import com.example.medicalclinic.model.projection.PatientGetByIdResponseDtoP;
import com.example.medicalclinic.repository.MedicationRepository;
import com.example.medicalclinic.repository.PatientRepository;
import com.example.medicalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final MedicationRepository medicationRepository;

    @Override
    public void createPatient(PatientRequestDto patientRequestDto) {
        List<Medication> medicationList = patientRequestDto.getMedications().stream()
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
                .medications(medicationList)
                .build());
    }

    @Override
    public void updatePatient(UUID id, PatientUpdateRequestDto patientRequestDto) {
        patientRepository.save(patientRepository.findById(id).orElseThrow()
                .withFirstname(patientRequestDto.getFirstname())
                .withLastname(patientRequestDto.getLastname())
                .withGender(patientRequestDto.getGender())
                .withDateOfBirth(patientRequestDto.getDateOfBirth()));
    }

    @Override
    public void removePatient(UUID id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<PatientGetAllResponseDto> getAll(SortedPatients sortedPatients) {
        List<PatientGetAllResponseDto> allPatients =
                patientRepository.getAllPatients(sortedPatients.getSearch()).stream()
                        .map(patient -> PatientGetAllResponseDto.builder()
                                .lastname(patient.getLastname())
                                .gender(patient.getGender())
                                .firstname(patient.getFirstname())
                                .dateOfBirth(patient.getDateOfBirth())
                                .build())
                        .toList();
        if (sortedPatients.getSortByFirstName() && sortedPatients.getSortByLastName()) {
            return allPatients.stream()
                    .sorted(Comparator.comparing(PatientGetAllResponseDto::getFirstname)
                            .thenComparing(PatientGetAllResponseDto::getLastname))
                    .toList();
        } else if (sortedPatients.getSortByFirstName()) {
            return allPatients.stream()
                    .sorted(Comparator.comparing(PatientGetAllResponseDto::getFirstname))
                    .toList();
        } else if (sortedPatients.getSortByLastName()) {
            return allPatients.stream()
                    .sorted(Comparator.comparing(PatientGetAllResponseDto::getLastname))
                    .toList();
        }

        return allPatients;
    }

    @Override
    public PatientGetByIdResponseDtoP getPatientGetById(UUID id) {
        Patient patientById = patientRepository.getPatientById(id);
        return PatientGetByIdResponseDtoP.builder()
                .dateOfBirth(patientById.getDateOfBirth())
                .firstname(patientById.getFirstname())
                .gender(patientById.getGender())
                .lastname(patientById.getLastname())
                .medications(patientById.getMedications())
                .build();
    }

    @Override
    public void addMedicationsToPatient(UUID patientId, List<MedicationRequestDto> medication) {
        Patient patient = patientRepository.getPatientById(patientId);
        patientRepository.save(patient.withMedications(Stream.concat(
                        patient.getMedications().stream(),
                        medication.stream()
                                .map(m -> Medication.builder()
                                        .time(m.getTime())
                                        .dosage(m.getDosage())
                                        .unit(m.getUnit())
                                        .description(m.getDescription())
                                        .build()))
                .toList()));
    }

    @Override
    public void updateMedication(UUID medicationId, MedicationRequestDto medicationRequestDto) {
        medicationRepository.save(medicationRepository.findById(medicationId).orElseThrow()
                .withTime(medicationRequestDto.getTime())
                .withDescription(medicationRequestDto.getDescription())
                .withDosage(medicationRequestDto.getDosage())
                .withUnit(medicationRequestDto.getUnit()));
    }

    @Override
    public void deleteMedication(UUID medicamentId) {
        medicationRepository.deleteById(medicamentId);
    }

    @Override
    public List<MedicationResponseDto> getMedicationByPatient(UUID patientId) {
        return patientRepository.getPatientById(patientId).getMedications().stream()
                .map(medication -> MedicationResponseDto.builder()
                        .dosage(medication.getDosage())
                        .unit(medication.getUnit())
                        .time(medication.getTime())
                        .description(medication.getDescription())
                        .build())
                .toList();
    }

}
