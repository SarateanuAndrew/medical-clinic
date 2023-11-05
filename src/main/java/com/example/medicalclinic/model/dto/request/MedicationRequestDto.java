package com.example.medicalclinic.model.dto.request;

import com.example.medicalclinic.model.enums.Unit;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class MedicationRequestDto {
    private String description;
    private BigDecimal dosage;
    private Unit unit;
    private LocalTime time;
}
