package com.example.medicalclinic.model.dto.request;

import com.example.medicalclinic.model.enums.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class PatientUpdateRequestDto {
    private String firstname;
    private String lastname;
    private Gender gender;
    private LocalDate dateOfBirth;
}
