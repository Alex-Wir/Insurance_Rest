package com.it.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "shift")
@Getter
@Setter
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{shift.opening.notNull")
    private LocalDateTime openingTime;

    @NotNull(message = "{shift.closing.notNull}")
    private LocalDateTime closingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "{shift.user.notNull}")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pos_id", nullable = false)
    @NotNull(message = "{shift.pos.notNull}")
    private Pos pos;

    @OneToMany(mappedBy = "shift")
    private Set<Insurance> insurances;

}
