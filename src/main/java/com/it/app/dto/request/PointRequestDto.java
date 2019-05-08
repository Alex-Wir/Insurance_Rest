package com.it.app.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

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

    public Set<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<Long> userIds) {
        this.userIds = userIds;
    }

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

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
