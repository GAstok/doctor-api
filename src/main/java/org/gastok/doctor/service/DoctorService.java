package org.gastok.doctor.service;

import org.gastok.doctor.model.dto.DoctorDto;
import org.gastok.doctor.model.request.DoctorRequest;
import org.gastok.doctor.model.response.DoctorDeleteResponse;
import org.gastok.doctor.model.response.DoctorListResponse;

public interface DoctorService {
    DoctorListResponse getDoctor(Long id);

    DoctorListResponse getAllDoctors();

    DoctorDto createDoctor(DoctorRequest request);

    DoctorDto updateDoctor(Long id, DoctorRequest request);

    DoctorDeleteResponse deleteDoctor(Long id);

    DoctorDeleteResponse deleteAllDoctors();

}
