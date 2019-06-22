package com.it.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

/**
 * Insurance entity
 */
@Entity
@Table(name = "insurance")
@Getter
@Setter
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "{insurance.payment.notNull}")
    @Positive(message = "{insurance.payment.positive}")
    private Float payment;

    @Column(nullable = false)
    @NotNull(message = "{insurance.amount.notNull}")
    @Positive(message = "{insurance.amount.positive}")
    private Float amount;

    @Column(nullable = false)
    @NotNull(message = "{insurance.periodFrom.notNull}")
    private LocalDate periodFrom;

    @Column(nullable = false)
    @NotNull(message = "{insurance.periodTo.notNull}")
    private LocalDate periodTo;

    @Column(nullable = false)
    @NotNull(message = "{insurance.date.notNull}")
    private LocalDate date;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "car_id", nullable = false)
    @NotNull(message = "{insurance.car.notNull}")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shift_id", nullable = false)
    @NotNull(message = "{insurance.shift.notNull}")
    private Shift shift;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "{insurance.user.notNull}")
    private User user;

}
