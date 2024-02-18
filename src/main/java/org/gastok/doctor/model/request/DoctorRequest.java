package org.gastok.doctor.model.request;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class DoctorRequest implements Serializable {
    private String firstName;
    private String lastName;
    private Integer age;
    private String refCode;
}
