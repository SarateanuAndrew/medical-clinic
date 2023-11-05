package com.example.medicalclinic.model.dto.response;

import com.example.medicalclinic.model.enums.Unit;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class MedicationResponseDto {
    private String description;
    private BigDecimal dosage;
    private Unit unit;
    private LocalTime time;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}
