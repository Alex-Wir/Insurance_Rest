package com.it.app.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShiftResponseDto {

    private Long id;
    private String openingTime;
    private String closingTime;
    private UserWithoutRoleResponseDto user;
    private PosResponseDto pos;

}
