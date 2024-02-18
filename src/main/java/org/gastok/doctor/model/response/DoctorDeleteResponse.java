package org.gastok.doctor.model.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class DoctorDeleteResponse implements Serializable {
    private Long deletedDoctorCount;
}
