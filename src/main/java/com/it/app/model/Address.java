package com.it.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{address.city.notNull}")
    @NotEmpty(message = "{address.city.notEmpty}")
    private String city;

    private String street;
    private String building;

    @NotNull(message = "{address.zipcode.notNull}")
    @Min(value = 210000, message = "{address.zipcode.pattern}")
    @Max(value = 247999, message = "{address.zipcode.pattern}")
    private Integer zipcode;

}
