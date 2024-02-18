package org.gastok.doctor.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String refCode;
}
