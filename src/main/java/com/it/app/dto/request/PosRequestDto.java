package com.it.app.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PosRequestDto {

    private Long id;

    @NotNull(message = "{pos.name.notNull}")
    @NotEmpty(message = "{pos.name.notEmpty}")
    @Size(min = 3, max = 20, message = "{pos.name.size}")
    private String name;

    @NotNull(message = "{pos.point.notNull}")
    private Long pointId;

}
