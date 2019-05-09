package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.PointRequestDto;
import com.it.app.dto.response.PointResponseDto;
import com.it.app.dto.response.PointWithUsersResponseDto;
import com.it.app.model.Address;
import com.it.app.model.Point;
import com.it.app.model.User;
import com.it.app.service.PointService;
import com.it.app.service.UserService;
import lombok.AllArgsConstructor;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/points")
@AllArgsConstructor
public class PointController {

    private final Mapper mapper;
    private final PointService pointService;
    private final UserService userService;
    private final LocalizedMessageSource localizedMessageSource;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PointResponseDto>> getAll() {
        final List<Point> points = pointService.findAll();
        final List<PointResponseDto> pointResponseDtoList = points.stream()
                .map((point) -> mapper.map(point, PointResponseDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(pointResponseDtoList, HttpStatus.OK);
    }

    /**
     * find all points by user id
     *
     * @param id - user id
     * @return
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<PointResponseDto>> getAllByUserId(@PathVariable Long id) {
        userService.findById(id);
        final List<Point> points = pointService.findAllByUserId(id);
        final List<PointResponseDto> pointResponseDtoList = points.stream()
                .map((point) -> mapper.map(point, PointResponseDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(pointResponseDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PointResponseDto> getOne(@PathVariable Long id) {
        final PointResponseDto pointResponseDto = mapper.map(pointService.findById(id), PointResponseDto.class);
        return new ResponseEntity<>(pointResponseDto, HttpStatus.OK);
    }

    /**
     * Return info with users by point id
     *
     * @param id - point id
     * @return
     */
    @RequestMapping(value = "/{id}/users", method = RequestMethod.GET)
    public ResponseEntity<PointWithUsersResponseDto> getOneWithUsers(@PathVariable Long id) {
        final PointWithUsersResponseDto pointResponseDto = mapper.map(pointService.findById(id), PointWithUsersResponseDto.class);
        return new ResponseEntity<>(pointResponseDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PointWithUsersResponseDto> save(@Valid @RequestBody PointRequestDto pointRequestDto) {
        pointRequestDto.setId(null);
        final PointWithUsersResponseDto pointResponseDto = mapper.map(pointService.save(getPoint(pointRequestDto)), PointWithUsersResponseDto.class);
        return new ResponseEntity<>(pointResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PointWithUsersResponseDto> update(@Valid @RequestBody PointRequestDto pointRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, pointRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.point.unexpectedId", new Object[]{}));
        }
        final PointWithUsersResponseDto pointResponseDto = mapper.map(pointService.update(getPoint(pointRequestDto)), PointWithUsersResponseDto.class);
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
        Set<User> users = pointRequestDto.getUserIds().stream()
                .map(userService::findById)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        final Point point = mapper.map(pointRequestDto, Point.class);
        point.setUsers(users);
        point.setAddress(address);
        return point;
    }

/*    TODO delete before Pull request
        getPoint - without Users

        private Point getPoint(PointRequestDto pointRequestDto) {
        final Address address = new Address();
        address.setId(pointRequestDto.getAddressId());
        final Point point = mapper.map(pointRequestDto, Point.class);
        point.setAddress(address);
        return point;
    }*/

}
