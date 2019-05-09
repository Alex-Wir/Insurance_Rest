package com.it.app.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointResponseDto {

    private Long id;
    private String name;
    private AddressResponseDto address;

}
