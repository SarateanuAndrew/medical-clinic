package com.example.medicalclinic.model.dto.response;

import com.example.medicalclinic.model.enums.Gender;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class PatientGetAllResponseDto {
    private String firstname;
    private String lastname;
    private Gender gender;
    private LocalDate dateOfBirth;
}
