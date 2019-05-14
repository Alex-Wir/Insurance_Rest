package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.InsuranceRequestDto;
import com.it.app.dto.response.InsuranceResponseDto;
import com.it.app.model.Car;
import com.it.app.model.Insurance;
import com.it.app.model.Shift;
import com.it.app.model.User;
import com.it.app.service.CarService;
import com.it.app.service.InsuranceService;
import com.it.app.service.UserService;
import lombok.AllArgsConstructor;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Insurance controller
 */
@RequestMapping("/insurances")
@RestController
@AllArgsConstructor
public class InsuranceController {

    private final Mapper mapper;

    private final InsuranceService insuranceService;
    private final UserService userService;
    private final CarService carService;
    private final LocalizedMessageSource localizedMessageSource;

    /**
     * Find all Insurances
     *
     * @return - ResponseEntity with List<InsuranceResponseDto> and HttpStatus
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<InsuranceResponseDto>> getAll() {
        final List<Insurance> insurances = insuranceService.findAll();
        final List<InsuranceResponseDto> insuranceResponseDtoList = insurances.stream()
                .map((insurance) -> mapper.map(insurance, InsuranceResponseDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(insuranceResponseDtoList, HttpStatus.OK);
    }

    /**
     * Find Insurance by id
     *
     * @param id - Insurance id
     * @return - ResponseEntity with InsuranceResponseDto and HttpStatus
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<InsuranceResponseDto> getOne(@PathVariable Long id) {
        final InsuranceResponseDto insuranceResponseDto = mapper.map(insuranceService.findById(id), InsuranceResponseDto.class);
        return new ResponseEntity<>(insuranceResponseDto, HttpStatus.OK);
    }

    /**
     * Find all Insurances by User id
     *
     * @param id - User id
     * @return -  ResponseEntity with List<InsuranceResponseDto> and HttpStatus
     */
    @PreAuthorize("#id == authentication.principal.id or hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<InsuranceResponseDto>> getAllByUserId(@PathVariable Long id) {
        if (userService.findById(id) == null) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.user.unexpectedId", new Object[]{}));
        }
        final List<Insurance> insurances = insuranceService.findAllByUserId(id);
        final List<InsuranceResponseDto> insuranceResponseDtoList = insurances.stream()
                .map((insurance) -> mapper.map(insurance, InsuranceResponseDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(insuranceResponseDtoList, HttpStatus.OK);
    }

    /**
     * Find all insurances by Car number
     *
     * @param carNumber - Car number
     * @return - ResponseEntity with List<InsuranceResponseDto> and HttpStatus
     */
    @RequestMapping(value = "/cars/{carNumber}", method = RequestMethod.GET)
    public ResponseEntity<List<InsuranceResponseDto>> getAllByCarNumber(@PathVariable String carNumber) {
        if (carService.findAllByNumber(carNumber).isEmpty()) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.car.unexpectedNumber", new Object[]{}));
        }
        final List<Insurance> insurances = insuranceService.findAllByCarNumber(carNumber);
        final List<InsuranceResponseDto> insuranceResponseDtoList = insurances.stream()
                .map((insurance) -> mapper.map(insurance, InsuranceResponseDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(insuranceResponseDtoList, HttpStatus.OK);
    }

    /**
     * Save transient Insurance
     *
     * @param insuranceRequestDto - transient Insurance
     * @return - ResponseEntity with InsuranceResponseDto and HttpStatus
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<InsuranceResponseDto> save(@Valid @RequestBody InsuranceRequestDto insuranceRequestDto) {
        insuranceRequestDto.setId(null);
        final InsuranceResponseDto insuranceResponseDto = mapper.map(insuranceService.save(getInsurance(insuranceRequestDto)), InsuranceResponseDto.class);
        return new ResponseEntity<>(insuranceResponseDto, HttpStatus.OK);
    }

    /**
     * Update persistent Insurance by id
     *
     * @param insuranceRequestDto, - request with updated Insurance
     * @param id                   - Insurance id
     * @return - ResponseEntity with InsuranceResponseDto and HttpStatus
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<InsuranceResponseDto> update(@Valid @RequestBody InsuranceRequestDto insuranceRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, insuranceRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.insurance.unexpectedId", new Object[]{}));
        }
        final InsuranceResponseDto insuranceResponseDto = mapper.map(insuranceService.update(getInsurance(insuranceRequestDto)), InsuranceResponseDto.class);
        return new ResponseEntity<>(insuranceResponseDto, HttpStatus.OK);
    }

    /**
     * Delete Insurance by id
     *
     * @param id - Insurance id
     */
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
