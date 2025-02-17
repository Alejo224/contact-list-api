package com.backend.ContactListApi.presentation.dto;

import com.backend.ContactListApi.persistence.entity.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
/**
 * DTO para representar una solicitud de creación o actualización de un usuario.
 */
public class UserRequestDTO {
    @NotBlank(message = "El campo 'username' es obligatorio")
    private String username;

    @NotBlank(message = "El campo 'password' es obligatorio")
    private String password;   // Solo para creación/actualización

    @NotNull(message = "El campo 'role' es obligatorio")
    private Role role;

    @Email(message = "El 'email' es invalido")
    @NotBlank(message = "El campo 'email' es obligatorio")
    private String email;


    //constructor
    public UserRequestDTO(){}

    public UserRequestDTO(String username, String password, Role role, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    //getters and setters
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
