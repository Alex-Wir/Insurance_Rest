package com.it.app.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO class for Insurance
 */
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
