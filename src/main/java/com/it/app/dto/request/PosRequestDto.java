package com.it.app.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PosRequestDto {

    private Long id;

    @NotNull(message = "{pos.name.notNull}")
    @NotEmpty(message = "{pos.name.notEmpty}")
    @Size(min = 3, max = 20, message = "{pos.name.size}")
    private String name;

/*    TODO add Point-entity
    @NotNull(message = "{pos.point.notNull}")
    private Long pointId;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*    public Long getPointId() {
        return pointId;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }*/
}
