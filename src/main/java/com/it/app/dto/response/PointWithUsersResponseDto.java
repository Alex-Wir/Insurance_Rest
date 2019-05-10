package com.it.app.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PointWithUsersResponseDto {

    private Long id;
    private String name;
    private AddressResponseDto address;
    private Set<UserWithoutRoleResponseDto> users;

}
