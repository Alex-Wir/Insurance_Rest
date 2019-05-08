package com.it.app.dto.request;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShiftRequestDto {

    private Long id;

    @NotNull(message = "{shift.opening.notNull}")
    private LocalDateTime openingTime;

    @NotNull(message = "{shift.closing.notNull}")
    private LocalDateTime closingTime;

    @NotNull(message = "{shift.user.notNull}")
    private Long userId;

    @NotNull(message = "{shift.pos.notNull}")
    private Long posId;

    public ShiftRequestDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        LocalDateTime openingTimeLdt = LocalDateTime.parse(openingTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.openingTime = openingTimeLdt;
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

    public void setClosingTime(String closingTime) {
        LocalDateTime closingTimeLdt = LocalDateTime.parse(closingTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.closingTime = closingTimeLdt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPosId() {
        return posId;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }
}
