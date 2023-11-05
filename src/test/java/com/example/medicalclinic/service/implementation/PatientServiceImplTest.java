package com.example.medicalclinic.service.implementation;

import com.example.medicalclinic.model.dto.request.MedicationRequestDto;
import com.example.medicalclinic.model.dto.request.PatientRequestDto;
import com.example.medicalclinic.model.enums.Gender;
import com.example.medicalclinic.model.enums.Unit;
import com.example.medicalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
class PatientServiceImplTest {
    @Autowired
    private PatientService patientService;

    @Test
    void createPatient() {
        PatientRequestDto patientRequestDto = PatientRequestDto.builder()
                .lastname("Papaluta")
                .firstname("Vasile")
                .dateOfBirth(LocalDate.of(2000, 3, 13))
                .gender(Gender.FEMALE)
                .medications(List.of(
                        MedicationRequestDto.builder()
                                .description("HeadLess")
                                .unit(Unit.TABLET)
                                .time(LocalTime.of(17, 0, 0, 0))
                                .build(),
                        MedicationRequestDto.builder()
                                .description("Useless")
                                .unit(Unit.GRAMS)
                                .time(LocalTime.of(15, 30, 0, 0))
                                .build()))
                .build();
        patientService.createPatient(patientRequestDto);
    }

    @Test
    void updatePatient() {
    }

    @Test
    void removePatient() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getPatientGetById() {
    }

    @Test
    void addMedicationsToPatient() {
    }

    @Test
    void updateMedication() {
    }

    @Test
    void deleteMedication() {
    }

    @Test
    void getMedicationByPatient() {
    }
}