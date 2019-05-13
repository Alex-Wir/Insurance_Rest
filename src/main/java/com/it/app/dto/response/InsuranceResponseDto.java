package com.it.app.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InsuranceResponseDto {

    private Long id;
    private Float payment;
    private Float amount;
    private String periodFrom;
    private String periodTo;
    private String date;
    private CarResponseDto car;
    private UserWithoutRoleResponseDto user;

}
