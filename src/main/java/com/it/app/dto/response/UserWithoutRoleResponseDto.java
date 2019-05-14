package com.it.app.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO class for User (without Roles)
 */
@Getter
@Setter
public class UserWithoutRoleResponseDto {

    private Long id;

    private String name;

}
