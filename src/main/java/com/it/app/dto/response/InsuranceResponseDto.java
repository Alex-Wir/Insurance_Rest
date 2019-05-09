package com.it.app.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsuranceResponseDto {

    private Long id;
    private Float payment;
    private Float amount;
    private CarResponseDto car;
    private ShiftIdResponseDto shift;
    private UserWithoutRoleResponseDto user;

}
