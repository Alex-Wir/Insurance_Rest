package com.it.app.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CarRequestDto {

    private Long id;

    @NotNull(message = "{car.number.notNull}")
    @NotEmpty(message = "{car.number.notEmpty}")
    @Size(min = 4, max = 10, message = "{car.number.size}")
    private String number;

    @NotNull(message = "{car.country.notNull}")
    @NotEmpty(message = "{car.country.notEmpty}")
    @Pattern(regexp = "^[A-Z]{2}$", message = "{car.country.pattern}")
    private String country;
}
