package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.PosRequestDto;
import com.it.app.dto.response.PosResponseDto;
import com.it.app.model.Point;
import com.it.app.model.Pos;
import com.it.app.service.PosService;
import lombok.AllArgsConstructor;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/poses")
@AllArgsConstructor
public class PosController {

    private final Mapper mapper;
    private final PosService posService;
    private final LocalizedMessageSource localizedMessageSource;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PosResponseDto>> getAll() {
        final List<Pos> poses = posService.findAll();
        final List<PosResponseDto> posResponseDtoList = poses.stream()
                .map((pos) -> mapper.map(pos, PosResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(posResponseDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PosResponseDto> getOne(@PathVariable Long id) {
        final PosResponseDto posResponseDto = mapper.map(posService.findById(id), PosResponseDto.class);
        return new ResponseEntity<>(posResponseDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PosResponseDto> save(@Valid @RequestBody PosRequestDto posRequestDto) {
        posRequestDto.setId(null);
        final PosResponseDto posResponseDto = mapper.map(posService.save(getPos(posRequestDto)), PosResponseDto.class);
        return new ResponseEntity<>(posResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PosResponseDto> update(@Valid @RequestBody PosRequestDto posRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, posRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.pos.unexpectedId", new Object[]{}));
        }
        final PosResponseDto posResponseDto = mapper.map(posService.update(getPos(posRequestDto)), PosResponseDto.class);
        return new ResponseEntity<>(posResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        posService.deleteById(id);
    }

    private Pos getPos(PosRequestDto posRequestDto) {
        final Pos pos = mapper.map(posRequestDto, Pos.class);
        final Point point = new Point();
        point.setId(posRequestDto.getPointId());
        pos.setPoint(point);
        return pos;
    }
}
