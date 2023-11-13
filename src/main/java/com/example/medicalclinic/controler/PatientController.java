package com.example.medicalclinic.controler;

import com.example.medicalclinic.model.dto.request.MedicationRequestDto;
import com.example.medicalclinic.model.dto.request.PatientRequestDto;
import com.example.medicalclinic.model.dto.request.PatientUpdateRequestDto;
import com.example.medicalclinic.model.dto.request.SortedPatients;
import com.example.medicalclinic.model.dto.response.MedicationResponseDto;
import com.example.medicalclinic.model.dto.response.PatientGetAllResponseDto;
import com.example.medicalclinic.model.projection.PatientGetByIdResponseDtoP;
import com.example.medicalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/patient")
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void savePatient(@RequestBody PatientRequestDto patientRequestDto) {
        patientService.createPatient(patientRequestDto);
    }

    @PutMapping("/update-patient/{id}")
    public void updatePatient(@PathVariable("id") UUID id, @RequestBody PatientUpdateRequestDto patientRequestDto) {
        patientService.updatePatient(id, patientRequestDto);
    }

    @DeleteMapping("/{id}")
    public void removePatient(@PathVariable("id") UUID id) {
        patientService.removePatient(id);
    }

    @GetMapping("/get-all")
    public List<PatientGetAllResponseDto> findAllPatients(@RequestBody SortedPatients sortedPatients) {
        return patientService.getAll(sortedPatients);
    }

    @GetMapping("/get-patient/{id}")
    public PatientGetByIdResponseDtoP getPatientById(@PathVariable("id") UUID id) {
        return patientService.getPatientGetById(id);
    }

    @PutMapping("/add-patient-medication/{id}")
    public void addMedicationsToPatient(@PathVariable("id") UUID id, @RequestBody List<MedicationRequestDto> medications) {
        patientService.addMedicationsToPatient(id, medications);
    }

    @PutMapping("/update-patient-medication/{medication-id}")
    public void updateMedicationsToPatient(@PathVariable("medication-id") UUID medicationId,
                                           @RequestBody MedicationRequestDto medications) {
        patientService.updateMedication(medicationId, medications);
    }

    @DeleteMapping("/delete-medication/{medicationId}")
    public void deleteMedication(@PathVariable("medicationId") UUID medicationId) {
        patientService.deleteMedication(medicationId);
    }

    @GetMapping("/get-medication-by-patient/{patientId}")
    public List<MedicationResponseDto> getMedicationByPatient(@PathVariable("patientId") UUID patientId) {
        return patientService.getMedicationByPatient(patientId);
    }
}
