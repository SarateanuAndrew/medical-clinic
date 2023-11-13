package com.example.medicalclinic.model.dbo;

import com.example.medicalclinic.model.enums.Unit;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@With
@Table(name = "medication")
public class Medication {
    @Id
    @GeneratedValue
    public UUID id;
    @Size(min = 3)
    private String description;
    @Positive
    private BigDecimal dosage;
    @Enumerated(STRING)
    private Unit unit;
    private LocalTime time;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime modificationDate;
}
