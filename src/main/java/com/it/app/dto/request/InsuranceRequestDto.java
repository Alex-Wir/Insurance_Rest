package com.it.app.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Request DTO class for Insurance
 */
@Getter
@NoArgsConstructor
public class InsuranceRequestDto {

    private Long id;

    @NotNull(message = "{insurance.payment.notNull}")
    @Positive(message = "{insurance.payment.positive}")
    private Float payment;

    @NotNull(message = "{insurance.amount.notNull}")
    @Positive(message = "{insurance.amount.positive}")
    private Float amount;

    @NotNull(message = "{insurance.periodFrom.notNull}")
    private LocalDate periodFrom;

    @NotNull(message = "{insurance.periodTo.notNull}")
    private LocalDate periodTo;

    @NotNull(message = "{insurance.date.notNull}")
    private LocalDate date;

    @NotNull(message = "{insurance.car.notNull}")
    private Long carId;

    @NotNull(message = "{insurance.shift.notNull}")
    private Long shiftId;

    @NotNull(message = "{insurance.user.notNull}")
    private Long userId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setPayment(Float payment) {
        this.payment = payment;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setPeriodFrom(String periodFrom) {
        LocalDate periodFromLD = LocalDate.parse(periodFrom, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.periodFrom = periodFromLD;
    }

    public void setPeriodTo(String periodTo) {
        LocalDate periodToLD = LocalDate.parse(periodTo, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.periodTo = periodToLD;
    }

    public void setDate(String date) {
        LocalDate dateLD = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.date = dateLD;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
