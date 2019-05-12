package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.UserRequestDto;
import com.it.app.dto.response.UserResponseDto;
import com.it.app.model.Role;
import com.it.app.model.User;
import com.it.app.service.InsuranceService;
import com.it.app.service.ShiftService;
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
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final Mapper mapper;
    private final UserService userService;
    private final ShiftService shiftService;
    private final InsuranceService insuranceService;
    private final LocalizedMessageSource localizedMessageSource;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserResponseDto>> getAll() {
        final List<User> users = userService.findAll();
        final List<UserResponseDto> userResponseDtoList = users.stream()
                .map((user) -> mapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserResponseDto> getOne(@PathVariable Long id) {
        final UserResponseDto userResponseDto = mapper.map(userService.findById(id), UserResponseDto.class);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResponseDto> save(@Valid @RequestBody UserRequestDto userRequestDto) {
        userRequestDto.setId(null);
        final UserResponseDto userResponseDto = mapper.map(userService.save(getUser(userRequestDto)), UserResponseDto.class);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserResponseDto> update(@Valid @RequestBody UserRequestDto userRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, userRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.user.unexpectedId", new Object[]{}));
        }
        final UserResponseDto userResponseDto = mapper.map(userService.update(getUser(userRequestDto)), UserResponseDto.class);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        //TODO delete point_user
        if (!insuranceService.findAllByUserId(id).isEmpty()) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.user.hasInsurance", new Object[]{}));
        }
        if (!shiftService.findShiftsByUserId(id).isEmpty()) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.user.hasShift", new Object[]{}));
        }
        userService.deleteById(id);
    }

    private User getUser(UserRequestDto userRequestDto) {
        final User user = mapper.map(userRequestDto, User.class);
        final Set<Role> roleSet = userRequestDto.getRoleIds().stream().map(roleId -> {
            Role role = new Role();
            role.setId(roleId);
            return role;
        }).collect(Collectors.toSet());
        user.setRoles(roleSet);
        return user;
    }

}
