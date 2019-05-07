package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.InsuranceRequestDto;
import com.it.app.dto.response.InsuranceResponseDto;
import com.it.app.model.Car;
import com.it.app.model.Insurance;
import com.it.app.model.Shift;
import com.it.app.model.User;
import com.it.app.service.InsuranceService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<InsuranceResponseDto> getOne(@PathVariable Long id) {
        final InsuranceResponseDto insuranceResponseDto = mapper.map(insuranceService.findById(id), InsuranceResponseDto.class);
        return new ResponseEntity<>(insuranceResponseDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<InsuranceResponseDto> save(@Valid @RequestBody InsuranceRequestDto insuranceRequestDto) {
        insuranceRequestDto.setId(null);
        final InsuranceResponseDto insuranceResponseDto = mapper.map(insuranceService.save(getInsurance(insuranceRequestDto)), InsuranceResponseDto.class);
        return new ResponseEntity<>(insuranceResponseDto, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<InsuranceResponseDto> update(@Valid @RequestBody InsuranceRequestDto insuranceRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, insuranceRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.insurance.unexpectedId", new Object[]{}));
        }
        final InsuranceResponseDto insuranceResponseDto = mapper.map(insuranceService.update(getInsurance(insuranceRequestDto)), InsuranceResponseDto.class);
        return new ResponseEntity<>(insuranceResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        insuranceService.deleteById(id);
    }

    private Insurance getInsurance(InsuranceRequestDto insuranceRequestDto) {
        final Car car = new Car();
        final Shift shift = new Shift();
        final User user = new User();
        car.setId(insuranceRequestDto.getCarId());
        shift.setId(insuranceRequestDto.getShiftId());
        user.setId(insuranceRequestDto.getUserId());
        final Insurance insurance = mapper.map(insuranceRequestDto, Insurance.class);
        insurance.setCar(car);
        insurance.setShift(shift);
        insurance.setUser(user);
        return insurance;
    }
}
