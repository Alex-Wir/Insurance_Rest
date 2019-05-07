package com.it.app.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
