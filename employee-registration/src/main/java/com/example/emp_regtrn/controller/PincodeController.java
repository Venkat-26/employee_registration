package com.example.emp_regtrn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emp_regtrn.dto.PincodeFinalResponse;
import com.example.emp_regtrn.service.PincodeService;

@RestController
@RequestMapping("/api/pincode")
public class PincodeController {

    private final PincodeService pincodeService;

    public PincodeController(PincodeService pincodeService) {
        this.pincodeService = pincodeService;
    }

    @GetMapping("/{pincode}")
    public ResponseEntity<PincodeFinalResponse> getPincodeDetails(
            @PathVariable String pincode) {

        PincodeFinalResponse response =
                pincodeService.getPincodeDetails(pincode);

        return ResponseEntity.ok(response);
    }
}