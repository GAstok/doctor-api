package org.gastok.doctor.service.impl;

import org.gastok.doctor.model.converter.DoctorConverter;
import org.gastok.doctor.model.dto.DoctorDto;
import org.gastok.doctor.model.entity.DoctorEntity;
import org.gastok.doctor.model.request.DoctorRequest;
import org.gastok.doctor.model.response.DoctorDeleteResponse;
import org.gastok.doctor.model.response.DoctorListResponse;
import org.gastok.doctor.service.DoctorService;
import org.gastok.doctor.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository repository;
    private final DoctorConverter doctorConverter;

    public DoctorServiceImpl(DoctorRepository repository, DoctorConverter doctorConverter) {
        this.repository = repository;
        this.doctorConverter = doctorConverter;
    }

    @Override
    public DoctorListResponse getDoctor(Long id) {
        final DoctorListResponse response = new DoctorListResponse();

        return repository.findById(id)
                .map(entity -> DoctorListResponse.builder().doctors(Collections.singletonList(doctorConverter.toDto(entity))).build())
                .orElse(response);
    }

    @Override
    public DoctorListResponse getAllDoctors() {
        final List<DoctorEntity> entities = repository.findAll();

        final List<DoctorDto> converted = entities
                .stream()
                .map(doctorConverter::toDto)
                .toList();

        return DoctorListResponse.builder().doctors(converted).build();

    }

    @Override
    public DoctorDto createDoctor(DoctorRequest request) {
        final DoctorEntity saved = repository.save(doctorConverter.toEntity(request));
        return doctorConverter.toDto(saved);
    }

    @Override
    public DoctorDto updateDoctor(Long id, DoctorRequest request) {
        final Optional<DoctorEntity> optionalDoctorEntity = repository.findById(id);
        if (optionalDoctorEntity.isEmpty()) {
            return null;
        }

        final DoctorEntity toBeUpdated = doctorConverter.toEntity(request);
        toBeUpdated.setId(optionalDoctorEntity.get().getId());
        final DoctorEntity saved = repository.save(toBeUpdated);
        return doctorConverter.toDto(saved);
    }

    @Override
    public DoctorDeleteResponse deleteDoctor(Long id) {
        if (!repository.existsById(id)) {
            return DoctorDeleteResponse.builder().deletedDoctorCount(0L).build();
        }

        repository.deleteById(id);
        return DoctorDeleteResponse.builder().deletedDoctorCount(1L).build();
    }

    @Override
    public DoctorDeleteResponse deleteAllDoctors() {
        final long count = repository.count();
        if (count == 0) {
            return DoctorDeleteResponse.builder().deletedDoctorCount(0L).build();
        }

        repository.deleteAll();
        return DoctorDeleteResponse.builder().deletedDoctorCount(count).build();
    }

}

