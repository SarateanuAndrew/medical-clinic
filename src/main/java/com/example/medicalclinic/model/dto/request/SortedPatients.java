package com.example.medicalclinic.model.dto.request;

import lombok.*;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SortedPatients {
    @Builder.Default
    private String search = "";
    @Builder.Default
    private Boolean sortByFirstName = false;
    @Builder.Default
    private Boolean sortByLastName = false;
}
