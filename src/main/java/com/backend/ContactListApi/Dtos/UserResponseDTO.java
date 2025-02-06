package com.backend.ContactListApi.Dtos;

import com.backend.ContactListApi.Entities.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserResponseDTO {
    private Long id;
    private String username;
    private Role role;
    private String email;

    //contructor
    public UserResponseDTO(){}

    public UserResponseDTO(Long id, String username, Role role, String email) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.email = email;
    }

    //getters nad setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
