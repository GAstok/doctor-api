package org.gastok.doctor.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gastok.doctor.model.dto.DoctorDto;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorListResponse implements Serializable {
    private List<DoctorDto> doctors;
}
