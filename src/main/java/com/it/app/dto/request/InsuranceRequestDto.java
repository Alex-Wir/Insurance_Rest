package com.it.app.dto.request;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class InsuranceRequestDto {

    private Long id;

    @Column(nullable = false)
    @NotNull(message = "insurance.payment.notNull")
    @Positive(message = "insurance.payment.positive")
    private Float payment;

    @Column(nullable = false)
    @NotNull(message = "insurance.amount.notNull")
    @Positive(message = "insurance.amount.positive")
    private Float amount;

    @NotNull(message = "insurance.car.notNull")
    private Long carId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPayment() {
        return payment;
    }

    public void setPayment(Float payment) {
        this.payment = payment;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
