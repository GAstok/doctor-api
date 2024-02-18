package org.gastok.doctor.controller;

import org.gastok.doctor.model.response.BooleanResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/status")
public class StatusController {
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<BooleanResponse> getStatus() {
        return new ResponseEntity<>(new BooleanResponse(true), HttpStatus.OK);
    }
}
