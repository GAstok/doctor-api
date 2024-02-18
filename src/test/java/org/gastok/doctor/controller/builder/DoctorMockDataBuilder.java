package org.gastok.doctor.controller.builder;

import org.gastok.doctor.model.dto.DoctorDto;
import org.gastok.doctor.model.request.DoctorRequest;
import org.gastok.doctor.model.response.DoctorDeleteResponse;
import org.gastok.doctor.model.response.DoctorListResponse;

import java.util.Collections;

public class DoctorMockDataBuilder {

    private DoctorMockDataBuilder() {
    }

    public static DoctorListResponse generateDoctorListResponse(long doctorId) {
        return DoctorListResponse.builder()
                .doctors(Collections.singletonList(generateDoctorDto(doctorId)))
                .build();
    }

    public static DoctorRequest generateDoctorRequest() {
        return GenericMockDataBuilder.of(DoctorRequest.class).build();
    }

    public static DoctorDeleteResponse generateDoctorDeleteResponse() {
        return DoctorDeleteResponse.builder()
                .deletedDoctorCount(1L)
                .build();
    }

    public static DoctorDto generateDoctorDto(long doctorId) {
        final DoctorDto dto = GenericMockDataBuilder.of(DoctorDto.class)
                .excludeField("id")
                .build();
        dto.setId(doctorId);
        return dto;
    }

}
