package org.gastok.doctor.model.converter;

import org.gastok.doctor.model.dto.DoctorDto;
import org.gastok.doctor.model.entity.DoctorEntity;
import org.gastok.doctor.model.request.DoctorRequest;
import org.springframework.stereotype.Component;

@Component
public class DoctorConverter {
    public DoctorDto toDto(DoctorEntity entity) {
        if (entity == null) {
            return null;
        } else {
            return DoctorDto.builder()
                    .id(entity.getId())
                    .firstName(entity.getFirstName())
                    .lastName(entity.getLastName())
                    .age(entity.getAge())
                    .refCode(entity.getRefCode())
                    .build();
        }
    }

    public DoctorEntity toEntity(DoctorRequest request) {
        if (request == null) {
            return null;
        } else {
            return DoctorEntity.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .age(request.getAge())
                    .refCode(request.getRefCode())
                    .build();
        }
    }

}
