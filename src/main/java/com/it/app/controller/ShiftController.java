package com.it.app.controller;


import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.ShiftRequestDto;
import com.it.app.dto.response.ShiftResponseDto;
import com.it.app.model.Pos;
import com.it.app.model.Shift;
import com.it.app.model.User;
import com.it.app.service.ShiftService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shifts")
public class ShiftController {

    private final Mapper mapper;

    private final ShiftService shiftService;

    private final LocalizedMessageSource localizedMessageSource;

    public ShiftController(Mapper mapper, ShiftService shiftService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.shiftService = shiftService;
        this.localizedMessageSource = localizedMessageSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ShiftResponseDto>> getAll() {
        final List<Shift> shifts = shiftService.findAll();
        final List<ShiftResponseDto> shiftResponseDtoList = shifts.stream()
                .map((shift) -> mapper.map(shift, ShiftResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(shiftResponseDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ShiftResponseDto> getOne(@PathVariable Long id) {
        final ShiftResponseDto shiftResponseDto = mapper.map(shiftService.findById(id), ShiftResponseDto.class);
        return new ResponseEntity<>(shiftResponseDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ShiftResponseDto> save(@Valid @RequestBody ShiftRequestDto shiftRequestDto) {
        shiftRequestDto.setId(null);
        final ShiftResponseDto shiftResponseDto = mapper.map(shiftService.save(getShift(shiftRequestDto)), ShiftResponseDto.class);
        return new ResponseEntity<>(shiftResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ShiftResponseDto> update(@Valid @RequestBody ShiftRequestDto shiftRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, shiftRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.shift.unexpectedId", new Object[]{}));
        }
        final ShiftResponseDto shiftResponseDto = mapper.map(shiftService.update(getShift(shiftRequestDto)), ShiftResponseDto.class);
        return new ResponseEntity<>(shiftResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        shiftService.deleteById(id);
    }

    private Shift getShift(ShiftRequestDto shiftRequestDto) {
        final Shift shift = mapper.map(shiftRequestDto, Shift.class);
        final User user = new User();
        final Pos pos = new Pos();
        user.setId(shiftRequestDto.getUserId());
        pos.setId(shiftRequestDto.getPosId());
        shift.setUser(user);
        shift.setPos(pos);
        return shift;
    }
}
