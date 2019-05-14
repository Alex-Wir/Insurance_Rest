package com.it.app.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Request DTO class for Address
 */
@Getter
@Setter
public class AddressRequestDto {

    private Long id;

    @NotNull(message = "{address.city.notNull}")
    @NotEmpty(message = "{address.city.notEmpty}")
    private String city;

    private String street;
    private String building;

    @NotNull(message = "{address.zipcode.notNull}")
    @Min(value = 210000, message = "{address.zipcode.pattern}")
    @Max(value = 247999, message = "{address.zipcode.pattern}")
    private Integer zipcode;
}