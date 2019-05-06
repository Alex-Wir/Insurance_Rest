package com.it.app.dto.response;

import java.time.LocalDateTime;

public class ShiftResponseDto {

    private Long id;

    /*  TODO fix Mapper for LocalDateTime*/

    private LocalDateTime openingTime;
    private LocalDateTime closingTime;
    private UserResponseDto user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalDateTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalDateTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalDateTime closingTime) {
        this.closingTime = closingTime;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }
}
