package com.it.app.dto.response;

import java.util.Set;

public class PointWithUsersResponseDto {

    private Long id;
    private String name;
    private AddressResponseDto address;
    private Set<UserWithoutRoleResponseDto> users;

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

    public AddressResponseDto getAddress() {
        return address;
    }

    public void setAddress(AddressResponseDto address) {
        this.address = address;
    }

    public Set<UserWithoutRoleResponseDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserWithoutRoleResponseDto> users) {
        this.users = users;
    }
}
