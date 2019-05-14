package com.it.app.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Request DTO class for Point
 */
@Getter
@Setter
public class PointRequestDto {

    private Long id;

    @NotNull(message = "{point.name.notNull}")
    @NotEmpty(message = "{point.name.notEmpty}")
    @Size(min = 3, max = 20, message = "{point.name.size}")
    private String name;

    @NotNull(message = "{point.address.notNull}")
    private Long addressId;

    @NotNull(message = "{point.users.notNull}")
    private Set<Long> userIds;

}
