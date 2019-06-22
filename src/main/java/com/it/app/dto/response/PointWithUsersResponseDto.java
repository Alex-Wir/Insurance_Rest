package com.it.app.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Response DTO class for Point (with Users)
 */
@Getter
@Setter
public class PointWithUsersResponseDto {

    private Long id;
    private String name;
    private AddressResponseDto address;
    private Set<UserWithoutRoleResponseDto> users;

}
