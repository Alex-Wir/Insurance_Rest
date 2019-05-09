package com.it.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "car")
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "{car.country.notNull}")
    @Pattern(regexp = "^[A-Z]{2}$", message = "{car.country.pattern}")
    private String country;

    @Column(nullable = false)
    @NotNull(message = "{car.number.notNull}")
    @Size(min = 4, max = 10, message = "{car.number.size}")
    private String number;

}
