package com.it.app.dto.request;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ShiftRequestDto {

    private Long id;

    @NotNull(message = "{shift.opening.notNull}")
    private LocalDateTime openingTime;

    @NotNull(message = "{shift.closing.notNull}")
    private LocalDateTime closingTime;

    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
