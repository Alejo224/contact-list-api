package com.backend.ContactListApi.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ContactDTO {
    //validaciones
    @NotBlank(message = "El campo 'name' es obligatorio.")
    private String name;

    @Email(message = "El email es invalido")
    @NotBlank(message = "El campo 'email' es obligatorio.")
    private String email;


    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
