package com.it.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Pos entity
 */
@Entity
@Table(name = "pos")
@Getter
@Setter
public class Pos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull(message = "{pos.name.notNull}")
    @NotEmpty(message = "{pos.name.notEmpty}")
    @Size(min = 3, max = 20, message = "{pos.name.size}")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_id", nullable = false)
    @NotNull(message = "{pos.point.notNull}")
    private Point point;

    @OneToMany(mappedBy = "pos")
    private Set<Shift> shifts;

}
