package com.example.medicalclinic.model.dto.request;

import com.example.medicalclinic.model.enums.Gender;
import com.example.medicalclinic.model.dbo.Medication;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class PatientRequestDto {
    private String firstname;
    private String lastname;
    private Gender gender;
    private LocalDate dateOfBirth;
    private List<MedicationRequestDto> medications;
}
