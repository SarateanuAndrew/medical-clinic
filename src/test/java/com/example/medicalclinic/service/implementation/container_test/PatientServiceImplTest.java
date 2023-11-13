package com.example.medicalclinic.service.implementation.container_test;

import com.example.medicalclinic.model.dbo.Medication;
import com.example.medicalclinic.model.dbo.Patient;
import com.example.medicalclinic.model.dto.request.MedicationRequestDto;
import com.example.medicalclinic.model.dto.request.PatientRequestDto;
import com.example.medicalclinic.model.dto.request.PatientUpdateRequestDto;
import com.example.medicalclinic.model.dto.request.SortedPatients;
import com.example.medicalclinic.model.dto.response.PatientGetAllResponseDto;
import com.example.medicalclinic.model.projection.PatientGetByIdResponseDtoP;
import com.example.medicalclinic.model.enums.Gender;
import com.example.medicalclinic.model.enums.Unit;
import com.example.medicalclinic.repository.PatientRepository;
import com.example.medicalclinic.service.implementation.config.PgTestContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PatientServiceImplTest extends PgTestContainer {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void createPatient() {
        PatientRequestDto patientRequestDto = PatientRequestDto.builder()
                .lastname("Ghisy")
                .firstname("Valera")
                .dateOfBirth(LocalDate.of(2000, 3, 13))
                .gender(Gender.FEMALE)
                .medications(List.of(
                        MedicationRequestDto.builder()
                                .description("HeadLess")
                                .unit(Unit.TABLET)
                                .dosage(BigDecimal.valueOf(10.2))
                                .time(LocalTime.of(17, 0, 0, 0))
                                .build(),
                        MedicationRequestDto.builder()
                                .description("Useless")
                                .unit(Unit.GRAMS)
                                .dosage(BigDecimal.valueOf(15.2))
                                .time(LocalTime.of(15, 30, 0, 0))
                                .build()))
                .build();

        mockMvc.perform(post("https://localhost:8080/api/patient")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patientRequestDto)))
                .andExpect(status().isCreated());
        assertThat(patientRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    @SneakyThrows
    void updatePatient() {
        PatientUpdateRequestDto patientRequestDto = PatientUpdateRequestDto.builder()
                .lastname("Ghisy")
                .firstname("Valera")
                .dateOfBirth(LocalDate.of(2000, 3, 13))
                .gender(Gender.FEMALE)
                .build();

        mockMvc.perform(put("https://localhost:8080/api/patient/update-patient/{id}",
                        "5ec7f26e-7371-42c2-936b-9fd0b2f9610f")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patientRequestDto)))
                .andExpect(status().isOk());
        assertThat(patientRepository.findById(UUID.fromString("5ec7f26e-7371-42c2-936b-9fd0b2f9610f"))
                .orElseThrow().getFirstname()).isEqualTo("Valera");
    }

    @Test
    @SneakyThrows
    void removePatient() {
        mockMvc.perform(delete("https://localhost:8080/api/patient/{id}",
                        "5ec7f26e-7371-42c2-936b-9fd0b2f9610f")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
        assertThat(patientRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    @SneakyThrows
    void getAll() {
        Patient patientRequestDto1 = Patient.builder()
                .lastname("Ghisy")
                .firstname("Valera")
                .dateOfBirth(LocalDate.of(2000, 3, 13))
                .gender(Gender.FEMALE)
                .medications(List.of(
                        Medication.builder()
                                .description("HeadLess")
                                .unit(Unit.TABLET)
                                .dosage(BigDecimal.valueOf(10.2))
                                .time(LocalTime.of(17, 0, 0, 0))
                                .build(),
                        Medication.builder()
                                .description("Useless")
                                .unit(Unit.GRAMS)
                                .dosage(BigDecimal.valueOf(15.2))
                                .time(LocalTime.of(15, 30, 0, 0))
                                .build()))
                .build();

        Patient patientRequestDto2 = Patient.builder()
                .lastname("Dasu")
                .firstname("Clava")
                .dateOfBirth(LocalDate.of(2000, 3, 13))
                .gender(Gender.FEMALE)
                .medications(List.of(
                        Medication.builder()
                                .description("HeadLess")
                                .unit(Unit.TABLET)
                                .dosage(BigDecimal.valueOf(10.2))
                                .time(LocalTime.of(17, 0, 0, 0))
                                .build(),
                        Medication.builder()
                                .description("Useless")
                                .unit(Unit.GRAMS)
                                .dosage(BigDecimal.valueOf(15.2))
                                .time(LocalTime.of(15, 30, 0, 0))
                                .build()))
                .build();

        Patient patientRequestDto3 = Patient.builder()
                .lastname("Aras")
                .firstname("Acsa")
                .dateOfBirth(LocalDate.of(2000, 3, 13))
                .gender(Gender.FEMALE)
                .medications(List.of(
                        Medication.builder()
                                .description("HeadLess")
                                .unit(Unit.TABLET)
                                .dosage(BigDecimal.valueOf(10.2))
                                .time(LocalTime.of(17, 0, 0, 0))
                                .build(),
                        Medication.builder()
                                .description("Useless")
                                .unit(Unit.GRAMS)
                                .dosage(BigDecimal.valueOf(15.2))
                                .time(LocalTime.of(15, 30, 0, 0))
                                .build()))
                .build();

        Patient patientRequestDto4 = Patient.builder()
                .lastname("Aras")
                .firstname("Acsand")
                .dateOfBirth(LocalDate.of(2000, 3, 13))
                .gender(Gender.FEMALE)
                .medications(List.of(
                        Medication.builder()
                                .description("HeadLess")
                                .unit(Unit.TABLET)
                                .dosage(BigDecimal.valueOf(10.2))
                                .time(LocalTime.of(17, 0, 0, 0))
                                .build(),
                        Medication.builder()
                                .description("Useless")
                                .unit(Unit.GRAMS)
                                .dosage(BigDecimal.valueOf(15.2))
                                .time(LocalTime.of(15, 30, 0, 0))
                                .build()))
                .build();
        patientRepository.save(patientRequestDto1);
        patientRepository.save(patientRequestDto2);
        patientRepository.save(patientRequestDto3);
        patientRepository.save(patientRequestDto4);
        System.out.println();
        SortedPatients sortedPatients = SortedPatients.builder()
                .sortByLastName(true)
                .sortByFirstName(true)
                .build();
        String contentAsString = mockMvc.perform(get("https://localhost:8080/api/patient/get-all")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sortedPatients)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<PatientGetAllResponseDto> all = List.of(objectMapper
                .readValue(contentAsString, PatientGetAllResponseDto[].class));
    }

    @Test
    @SneakyThrows
    void getPatientGetById() {
        String content = mockMvc.perform(get("https://localhost:8080/api/patient/get-patient/{id}",
                        "5ec7f26e-7371-42c2-936b-9fd0b2f9610f")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println();
        PatientGetByIdResponseDtoP patientGetByIdResponseDtoP = objectMapper.readValue(content,
                PatientGetByIdResponseDtoP.class);
        System.out.println();
    }

    @Test
    @SneakyThrows
    void addMedicationsToPatient() {
        MedicationRequestDto medication1 = MedicationRequestDto.builder()
                .dosage(BigDecimal.valueOf(14.5))
                .description("Treat")
                .unit(Unit.TABLET)
                .time(LocalTime.of(15, 30, 0, 0))
                .build();

        MedicationRequestDto medication2 = MedicationRequestDto.builder()
                .dosage(BigDecimal.valueOf(17.5))
                .description("Heal")
                .unit(Unit.TABLET)
                .time(LocalTime.of(15, 30, 0, 0))
                .build();

        mockMvc.perform(put("https://localhost:8080/api/patient/add-patient-medication/{id}",
                        "5ec7f26e-7371-42c2-936b-9fd0b2f9610f")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(List.of(medication1, medication2))))
                .andExpect(status().isOk());
        assertThat(patientRepository.getPatientById(
                UUID.fromString("5ec7f26e-7371-42c2-936b-9fd0b2f9610f")).getMedications().size())
                .isEqualTo(4);
    }

    @Test
    @SneakyThrows
    void updateMedication() {
        MedicationRequestDto medication = MedicationRequestDto.builder()
                .dosage(BigDecimal.valueOf(17.5))
                .description("Heal")
                .unit(Unit.TABLET)
                .time(LocalTime.of(15, 30, 0, 0))
                .build();

        mockMvc.perform(put("https://localhost:8080/api/patient/update-patient-medication/{medication-id}",
                        "6b38f598-1741-4900-a1b2-63b246073e36")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medication)))
                .andExpect(status().isOk());
        Patient patientById = patientRepository.getPatientById(UUID.fromString("5ec7f26e-7371-42c2-936b-9fd0b2f9610f"));
        System.out.println();
    }

    @Test
    @SneakyThrows
    void deleteMedication() {
        mockMvc.perform(delete("https://localhost:8080/api/patient/delete-medication/{medicationId}",
                        "6b38f598-1741-4900-a1b2-63b246073e36")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
        Patient patientById = patientRepository.getPatientById(UUID.fromString("5ec7f26e-7371-42c2-936b-9fd0b2f9610f"));
        System.out.println();
    }

    @Test
    @SneakyThrows
    void getMedicationByPatient() {
        String contentAsString = mockMvc.perform(get("https://localhost:8080/api/patient/get-medication-by-patient/{patientId}",
                        "5ec7f26e-7371-42c2-936b-9fd0b2f9610f")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<MedicationRequestDto> medicationRequestDto = List.of(objectMapper.readValue(contentAsString, MedicationRequestDto[].class));
        System.out.println();
    }
}