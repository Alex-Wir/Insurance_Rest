package com.it.app.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class InsuranceRequestDto {

    private Long id;

    @Column(nullable = false)
    @NotNull(message = "{insurance.payment.notNull}")
    @Positive(message = "{insurance.payment.positive}")
    private Float payment;

    @Column(nullable = false)
    @NotNull(message = "{insurance.amount.notNull}")
    @Positive(message = "{insurance.amount.positive}")
    private Float amount;

    @NotNull(message = "{insurance.car.notNull}")
    private Long carId;

    @NotNull(message = "{insurance.shift.notNull}")
    private Long shiftId;

    @NotNull(message = "{insurance.user.notNull}")
    private Long userId;

}
