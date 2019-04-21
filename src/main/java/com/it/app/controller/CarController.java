package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.CarRequestDto;
import com.it.app.dto.response.CarResponseDto;
import com.it.app.model.Car;
import com.it.app.service.CarService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final Mapper mapper;

    private final CarService carService;

    private final LocalizedMessageSource localizedMessageSource;

    public CarController(Mapper mapper, CarService carService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.carService = carService;
        this.localizedMessageSource = localizedMessageSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CarResponseDto>> getAll() {
        final List<Car> cars = carService.findAll();
        final List<CarResponseDto> carResponseDtoList = cars.stream()
                .map((car) -> mapper.map(car, CarResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(carResponseDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CarResponseDto> getOne(@PathVariable Long id) {
        final CarResponseDto carDto = mapper.map(carService.findById(id), CarResponseDto.class);
        return new ResponseEntity<>(carDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CarResponseDto> save(@Valid @RequestBody CarRequestDto carRequestDto) {
        carRequestDto.setId(null);
        final CarResponseDto carResponseDto = mapper.map(carService.save(getCar(carRequestDto)), CarResponseDto.class);
        return new ResponseEntity<>(carResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CarResponseDto> update(@Valid @RequestBody CarRequestDto userRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, userRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.car.unexpectedId", new Object[]{}));
        }
        final CarResponseDto carResponseDto = mapper.map(carService.update(getCar(userRequestDto)), CarResponseDto.class);
        return new ResponseEntity<>(carResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        if (carService.findById(id) != null) {
            carService.deleteById(id);
        }
    }

    private Car getCar(CarRequestDto carRequestDto) {
        final Car car = mapper.map(carRequestDto, Car.class);

        /* ===here one-to-many and other relations===

        final Insurance insurance = new Insurance();
        insurance.setId(carRequestDto.getInsuranceId());
        car.setInsurance(insurance);*/
        return car;
    }
}
