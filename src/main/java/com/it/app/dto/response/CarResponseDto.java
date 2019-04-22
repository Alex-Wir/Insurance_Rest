package com.it.app.dto.response;

public class CarResponseDto {

    private Long id;
    private String number;
    private String country;
    private CarResponseDto car;

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

    public CarResponseDto getCar() {
        return car;
    }

    public void setCar(CarResponseDto car) {
        this.car = car;
    }
}
