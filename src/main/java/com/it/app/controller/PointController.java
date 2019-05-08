package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.PointRequestDto;
import com.it.app.dto.response.PointResponseDto;
import com.it.app.model.Address;
import com.it.app.model.Point;
import com.it.app.service.PointService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/points")
public class PointController {

    private final Mapper mapper;
    private final PointService pointService;
    private final LocalizedMessageSource localizedMessageSource;

    public PointController(Mapper mapper, PointService pointService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.pointService = pointService;
        this.localizedMessageSource = localizedMessageSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PointResponseDto>> getAll() {
        final List<Point> points = pointService.findAll();
        final List<PointResponseDto> pointResponseDtoList = points.stream()
                .map((point) -> mapper.map(point, PointResponseDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(pointResponseDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PointResponseDto> getOne(@PathVariable Long id) {
        final PointResponseDto pointResponseDto = mapper.map(pointService.findById(id), PointResponseDto.class);
        return new ResponseEntity<>(pointResponseDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PointResponseDto> save(@Valid @RequestBody PointRequestDto pointRequestDto) {
        pointRequestDto.setId(null);
        final PointResponseDto pointResponseDto = mapper.map(pointService.save(getPoint(pointRequestDto)), PointResponseDto.class);
        return new ResponseEntity<>(pointResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PointResponseDto> update(@Valid @RequestBody PointRequestDto pointRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, pointRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.point.unexpectedId", new Object[]{}));
        }
        final PointResponseDto pointResponseDto = mapper.map(pointService.update(getPoint(pointRequestDto)), PointResponseDto.class);
        return new ResponseEntity<>(pointResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        pointService.deleteById(id);
    }

    private Point getPoint(PointRequestDto pointRequestDto) {
        final Address address = new Address();
        address.setId(pointRequestDto.getAddressId());
        final Point point = mapper.map(pointRequestDto, Point.class);
        point.setAddress(address);
        return point;
    }

}
