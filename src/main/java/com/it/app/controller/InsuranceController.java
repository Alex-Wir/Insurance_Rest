package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.response.InsuranceResponseDto;
import com.it.app.dto.response.UserResponseDto;
import com.it.app.model.Insurance;
import com.it.app.service.InsuranceService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/insurances")
@RestController
public class InsuranceController {

    private final Mapper mapper;

    private final InsuranceService insuranceService;

    private final LocalizedMessageSource localizedMessageSource;

    public InsuranceController(Mapper mapper, InsuranceService insuranceService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.insuranceService = insuranceService;
        this.localizedMessageSource = localizedMessageSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<InsuranceResponseDto>> getAll() {
        final List<Insurance> insurances = insuranceService.findAll();
        final List<InsuranceResponseDto> insuranceResponseDtoList = insurances.stream()
                .map((insurance) -> mapper.map(insurance, InsuranceResponseDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(insuranceResponseDtoList, HttpStatus.OK);
    }


    //waiting for other methods
}
