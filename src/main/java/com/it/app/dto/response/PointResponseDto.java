package com.it.app.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO class for Point (without Users)
 */
@Getter
@Setter
public class PointResponseDto {

    private Long id;
    private String name;
    private AddressResponseDto address;

}
