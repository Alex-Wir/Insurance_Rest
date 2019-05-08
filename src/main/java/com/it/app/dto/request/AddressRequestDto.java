package com.it.app.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddressRequestDto {

    private Long id;

    @NotNull(message = "{address.city.notNull}")
    @NotEmpty(message = "{address.city.notEmpty}")
    private String city;

    private String street;
    private String building;

    @NotNull(message = "{address.zipcode.notNull}")
    // @Pattern(regexp = "^2[1-4][0-7]\\d{3}$", message = "{address.zipcode.pattern}")
    @Min(value = 210000, message = "{address.zipcode.pattern}")
    @Max(value = 247999, message = "{address.zipcode.pattern}")
    private Integer zipcode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }
}