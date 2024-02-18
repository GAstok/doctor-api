package org.gastok.doctor.controller.controller;

import org.gastok.doctor.controller.DoctorController;
import org.gastok.doctor.controller.base.BaseControllerTest;
import org.gastok.doctor.model.dto.DoctorDto;
import org.gastok.doctor.model.request.DoctorRequest;
import org.gastok.doctor.model.response.DoctorDeleteResponse;
import org.gastok.doctor.model.response.DoctorListResponse;
import org.gastok.doctor.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.gastok.doctor.controller.builder.DoctorMockDataBuilder.generateDoctorDeleteResponse;
import static org.gastok.doctor.controller.builder.DoctorMockDataBuilder.generateDoctorDto;
import static org.gastok.doctor.controller.builder.DoctorMockDataBuilder.generateDoctorListResponse;
import static org.gastok.doctor.controller.builder.DoctorMockDataBuilder.generateDoctorRequest;
import static org.junit.jupiter.api.Assertions.fail;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebMvcTest(value = DoctorController.class)
class DoctorControllerTest extends BaseControllerTest {

    private static final long DOCTOR_ID = 1;
    private DoctorListResponse doctorListResponse;
    private DoctorRequest doctorRequest;
    private DoctorDto doctorDto;
    private DoctorDeleteResponse doctorDeleteResponse;

    @MockBean
    private DoctorService doctorService;

    @BeforeEach
    void setUp() {
        doctorListResponse = generateDoctorListResponse(DOCTOR_ID);
        doctorRequest = generateDoctorRequest();
        doctorDto = generateDoctorDto(DOCTOR_ID);
        doctorDeleteResponse = generateDoctorDeleteResponse();
        this.mockMvc = webAppContextSetup(wac)
                .build();
    }

    //TODO: write authorization tests for non-get requests
    @Test
    void getDoctor() {
        when(doctorService.getDoctor(DOCTOR_ID)).thenReturn(doctorListResponse);
        try {
            mockMvc.perform(get("/doctor/{id}", DOCTOR_ID))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail(e);
        }

        verify(doctorService, times(1)).getDoctor(DOCTOR_ID);
        verifyNoMoreInteractions(doctorService);
    }


    @Test
    void getAllDoctors() {
        when(doctorService.getAllDoctors()).thenReturn(doctorListResponse);
        try {
            mockMvc.perform(get("/doctors"))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail(e);
        }

        verify(doctorService, times(1)).getAllDoctors();
        verifyNoMoreInteractions(doctorService);
    }

    @Test
    void createDoctor() {
        when(doctorService.createDoctor(doctorRequest)).thenReturn(doctorDto);

        try {
            mockMvc.perform(post("/doctor")
                            .contentType(contentType)
                            .content(json(doctorRequest))
                    )
                    .andDo(print())
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            fail(e);
        }

        verify(doctorService, times(1)).createDoctor(doctorRequest);
        verifyNoMoreInteractions(doctorService);
    }

    @Test
    void updateDoctor() {
        when(doctorService.updateDoctor(DOCTOR_ID, doctorRequest)).thenReturn(doctorDto);
        try {
            mockMvc.perform(put("/doctor/{id}", DOCTOR_ID)
                            .contentType(contentType)
                            .content(json(doctorRequest))
                    )
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail(e);
        }

        verify(doctorService, times(1)).updateDoctor(DOCTOR_ID, doctorRequest);
        verifyNoMoreInteractions(doctorService);
    }

    @Test
    void updateDoctor_notFound() {
        when(doctorService.updateDoctor(DOCTOR_ID, doctorRequest)).thenReturn(null);
        try {
            mockMvc.perform(put("/doctor/{id}", DOCTOR_ID)
                            .contentType(contentType)
                            .content(json(doctorRequest))
                    )
                    .andDo(print())
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            fail(e);
        }

        verify(doctorService, times(1)).updateDoctor(DOCTOR_ID, doctorRequest);
        verifyNoMoreInteractions(doctorService);
    }

    @Test
    void deleteDoctor() {
        when(doctorService.deleteDoctor(DOCTOR_ID)).thenReturn(doctorDeleteResponse);
        try {
            mockMvc.perform(delete("/doctor/{id}", DOCTOR_ID))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail(e);
        }

        verify(doctorService, times(1)).deleteDoctor(DOCTOR_ID);
        verifyNoMoreInteractions(doctorService);
    }

    @Test
    void deleteDoctor_noContent() {
        when(doctorService.deleteDoctor(DOCTOR_ID)).thenReturn(DoctorDeleteResponse.builder().deletedDoctorCount(0L).build());
        try {
            mockMvc.perform(delete("/doctor/{id}", DOCTOR_ID))
                    .andDo(print())
                    .andExpect(status().isNoContent());
        } catch (Exception e) {
            fail(e);
        }

        verify(doctorService, times(1)).deleteDoctor(DOCTOR_ID);
        verifyNoMoreInteractions(doctorService);
    }

    @Test
    void deleteAllDoctors() {
        when(doctorService.deleteAllDoctors()).thenReturn(doctorDeleteResponse);
        try {
            mockMvc.perform(delete("/doctors"))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail(e);
        }

        verify(doctorService, times(1)).deleteAllDoctors();
        verifyNoMoreInteractions(doctorService);
    }
}




