package com.it.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Point entity
 */
@Entity
@Table(name = "point")
@Getter
@Setter
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{point.name.notNull}")
    @NotEmpty(message = "{point.name.notEmpty}")
    @Size(min = 3, max = 20, message = "{point.name.size}")
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    @NotNull(message = "{point.address.notNull}")
    private Address address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "point_user",
            joinColumns = @JoinColumn(name = "point_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @NotEmpty(message = "{point.users.notNull}")
    private Set<User> users;

    @OneToMany(mappedBy = "point")
    private Set<Pos> poses;

}
