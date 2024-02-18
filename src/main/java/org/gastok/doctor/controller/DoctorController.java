package org.gastok.doctor.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gastok.doctor.model.dto.DoctorDto;
import org.gastok.doctor.model.request.DoctorRequest;
import org.gastok.doctor.model.response.DoctorDeleteResponse;
import org.gastok.doctor.model.response.DoctorListResponse;
import org.gastok.doctor.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    private static final Logger logger = LogManager.getLogger(DoctorController.class);

    @GetMapping("/doctor/{id}")
    public ResponseEntity<DoctorListResponse> getDoctor(@PathVariable("id") Long id) {
        DoctorListResponse response = doctorService.getDoctor(id);
        if (response.getDoctors().isEmpty()) {
            logger.warn("doctor not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("doctor found");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/doctors")
    public ResponseEntity<DoctorListResponse> getAllDoctors() {
        DoctorListResponse response = doctorService.getAllDoctors();

        logger.info("doctors found");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/doctor")
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorRequest request) {
        DoctorDto doctor = doctorService.createDoctor(request);

        logger.info("doctor created");
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }

    @PostMapping("/doctors")
    public ResponseEntity<List<DoctorDto>> createDoctors(@RequestBody List<DoctorRequest> request) {
        List<DoctorDto> doctors = request.stream().map(doctorService::createDoctor).toList();

        logger.info("doctors created");
        return new ResponseEntity<>(doctors, HttpStatus.CREATED);
    }

    @PutMapping("/doctor/{id}")
    public ResponseEntity<DoctorDto> updateDoctor(@PathVariable("id") Long id, @RequestBody DoctorRequest request) {
        DoctorDto doctorDto = doctorService.updateDoctor(id, request);
        if (doctorDto == null) {
            logger.warn("doctor not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("doctor updated");
        return new ResponseEntity<>(doctorDto, HttpStatus.OK);
    }

    @DeleteMapping("/doctor/{id}")
    public ResponseEntity<DoctorDeleteResponse> deleteDoctor(@PathVariable("id") Long id) {
        DoctorDeleteResponse response = doctorService.deleteDoctor(id);
        if (response.getDeletedDoctorCount() == 0L) {
            logger.warn("doctor not deleted");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        logger.info("doctor deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/doctors")
    public ResponseEntity<DoctorDeleteResponse> deleteAllDoctors() {
        DoctorDeleteResponse response = doctorService.deleteAllDoctors();
        if (response.getDeletedDoctorCount() == 0L) {
            logger.warn("doctors not deleted");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        logger.info("doctors deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
