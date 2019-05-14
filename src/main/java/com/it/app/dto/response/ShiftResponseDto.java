package com.it.app.dto.response;

import lombok.Getter;

/**
 * Response DTO class for Shift
 */
@Getter
public class ShiftResponseDto {

    private Long id;
    private String openingTime;
    private String closingTime;
    private UserWithoutRoleResponseDto user;
    private PosResponseDto pos;

    public void setId(Long id) {
        this.id = id;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime.replace('T', ' ');
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime.replace('T', ' ');
    }

    public void setUser(UserWithoutRoleResponseDto user) {
        this.user = user;
    }

    public void setPos(PosResponseDto pos) {
        this.pos = pos;
    }
}
