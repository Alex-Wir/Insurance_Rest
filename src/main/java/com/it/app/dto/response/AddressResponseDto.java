package com.it.app.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponseDto {

    private Long id;
    private String city;
    private String street;
    private String building;
    private Integer zipcode;

}
