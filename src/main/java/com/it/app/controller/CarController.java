package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.response.CarResponseDto;
import com.it.app.model.Car;
import com.it.app.service.CarService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    //waiting for other methods
}
