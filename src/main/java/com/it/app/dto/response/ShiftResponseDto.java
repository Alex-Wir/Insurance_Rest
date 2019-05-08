package com.it.app.dto.response;

public class ShiftResponseDto {

    private Long id;
    private String openingTime;
    private String closingTime;
    private UserWithoutRoleResponseDto user;
    private PosResponseDto pos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public UserWithoutRoleResponseDto getUser() {
        return user;
    }

    public void setUser(UserWithoutRoleResponseDto user) {
        this.user = user;
    }

    public PosResponseDto getPos() {
        return pos;
    }

    public void setPos(PosResponseDto pos) {
        this.pos = pos;
    }
}
