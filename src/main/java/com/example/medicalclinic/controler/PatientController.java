package com.example.medicalclinic.controler;

import com.example.medicalclinic.model.dto.request.PatientRequestDto;
import com.example.medicalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/patient")
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public void savePatient(@RequestBody PatientRequestDto patientRequestDto){
        patientService.createPatient(patientRequestDto);
    }

    @PutMapping("/update-patient/{id}")
    public void updatePatient(@PathVariable("id") UUID id, @RequestBody PatientRequestDto patientRequestDto){
        patientService.updatePatient(id, patientRequestDto);
    }

    @DeleteMapping("{id}")
    public void removePatient(@PathVariable("id") UUID id){
        patientService.removePatient(id);
    }
}
